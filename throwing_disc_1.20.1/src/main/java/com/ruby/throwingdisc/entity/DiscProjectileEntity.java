package com.ruby.throwingdisc.entity;

import com.ruby.throwingdisc.item.DiscItem;
import com.ruby.throwingdisc.config.DiscConfig;
import com.ruby.throwingdisc.item.DiscTier;
import com.ruby.throwingdisc.registry.ModEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * The flying disc. Behaviour follows the announced vanilla design:
 * straight flight with no gravity, pierces every entity in its path,
 * ricochets off blocks (each bounce extends its range and speeds it up),
 * then flies back to the thrower and is caught into the hand it left.
 * The return leg passes through blocks, like a Loyalty trident.
 */
public class DiscProjectileEntity extends Projectile {
    private static final EntityDataAccessor<ItemStack> DATA_ITEM =
            SynchedEntityData.defineId(DiscProjectileEntity.class, EntityDataSerializers.ITEM_STACK);
    private static final EntityDataAccessor<Boolean> DATA_RETURNING =
            SynchedEntityData.defineId(DiscProjectileEntity.class, EntityDataSerializers.BOOLEAN);

    private static final double BASE_RANGE = 26.0;

    /** Disc flight speed, from the config (discSpeed, default 1.0). */
    public static float baseSpeed() {
        return DiscConfig.speed();
    }

    private static double maxSpeed() {
        return baseSpeed() * 2.0;
    }
    private static final double CATCH_DISTANCE = 1.3;

    private boolean returning = false;
    private int flightTicks = 0;
    private boolean forcedReturn = false;
    private boolean delicate = false;
    private boolean splitCopy = false;
    private int maxFlightTicks = 60;
    private Vec3 lastTickPos = null;
    private int stuckTicks = 0;
    private BlockPos lastBlockPos = null;
    private int sameBlockTicks = 0;
    private double traveled = 0.0;
    private double budget = 0.0;
    private InteractionHand fromHand = InteractionHand.MAIN_HAND;
    private final Set<UUID> hitThisPhase = new HashSet<>();

    public DiscProjectileEntity(EntityType<? extends DiscProjectileEntity> type, Level level) {
        super(type, level);
        this.setNoGravity(true);
    }

    // ------------------------------------------------------------------ data

    @Override
    protected void defineSynchedData() {
        this.entityData.define(DATA_ITEM, ItemStack.EMPTY);
        this.entityData.define(DATA_RETURNING, false);
    }

    /** ItemSupplier-style accessor used by the renderer. */
    public ItemStack getItem() {
        return this.entityData.get(DATA_ITEM);
    }

    public ItemStack getDiscStack() {
        return getItem();
    }

    public void setDiscStack(ItemStack stack) {
        this.entityData.set(DATA_ITEM, stack);
        // The disc's own enchantments, read from the stack it carries:
        // Delicate bounces off creatures, Lingering stays out longer
        // (+40 ticks and +13 blocks of range per level).
        int lingering = EnchantmentHelper.getItemEnchantmentLevel(
                ModEnchantments.LINGERING.get(), stack);
        this.delicate = EnchantmentHelper.getItemEnchantmentLevel(
                ModEnchantments.DELICATE.get(), stack) > 0;
        this.maxFlightTicks = 60 + 40 * lingering;
        if (this.budget <= 0.0) {
            this.budget = BASE_RANGE + 13.0 * lingering + getTier().rangeBonus();
        }
    }

    /** Splitting copies are phantoms: they never yield the item itself. */
    public void setSplitCopy(boolean splitCopy) {
        this.splitCopy = splitCopy;
    }

    public void setFromHand(InteractionHand hand) {
        this.fromHand = hand;
    }

    public DiscTier getTier() {
        if (getItem().getItem() instanceof DiscItem disc) {
            return disc.getTier();
        }
        return DiscTier.IRON;
    }

    @Override
    public boolean fireImmune() {
        return getTier().fireResistant();
    }

    // ------------------------------------------------------------------ tick

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            tickClientFlight();
            return;
        }
        Entity owner = getOwner();
        if (owner == null || !owner.isAlive() || owner.level() != this.level()
                || getItem().isEmpty()) {
            dropAndDiscard();
            return;
        }
        if (!this.returning && checkStuck()) {
            relocateAndReturn();
        }
        if (!this.returning) {
            this.flightTicks++;
            if (this.flightTicks >= this.maxFlightTicks) { // 3 s after the throw, plus Lingering
                this.forcedReturn = true;
                startReturn();
            }
        }
        if (this.returning) {
            tickReturning(owner);
        } else {
            tickOutbound();
        }
    }

    /**
     * Client-side mirror of the flight, the way vanilla projectiles do it:
     * the client advances the disc along its velocity every tick and mirrors
     * block ricochets, so the motion renders continuously instead of
     * stepping from one server position update to the next. Damage, block
     * breaking and catching stay server-only; server updates still correct
     * the mirror whenever they disagree.
     */
    private void tickClientFlight() {
        if (getItem().isEmpty()) {
            return;
        }
        Vec3 pos = position();
        Vec3 mov = getDeltaMovement();
        if (mov.lengthSqr() < 1.0e-6) {
            return;
        }
        Vec3 next = pos.add(mov);
        if (this.entityData.get(DATA_RETURNING)) {
            // On the way home the server steers the velocity every tick;
            // just ride along it.
            setPos(next.x, next.y, next.z);
            return;
        }
        BlockHitResult blockHit = this.level().clip(new ClipContext(
                pos, next, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
        if (blockHit.getType() == HitResult.Type.BLOCK) {
            Direction direction = blockHit.getDirection();
            Vec3 reflected = switch (direction.getAxis()) {
                case X -> new Vec3(-mov.x, mov.y, mov.z);
                case Y -> new Vec3(mov.x, -mov.y, mov.z);
                case Z -> new Vec3(mov.x, mov.y, -mov.z);
            };
            double newLength = Math.min(reflected.length() * 1.15, maxSpeed());
            reflected = reflected.normalize().scale(newLength);
            setDeltaMovement(reflected);
            Vec3 at = blockHit.getLocation().add(reflected.normalize().scale(0.06));
            setPos(at.x, at.y, at.z);
        } else {
            setPos(next.x, next.y, next.z);
        }
    }

    private void tickOutbound() {
        Vec3 pos = position();
        Vec3 mov = getDeltaMovement();
        Vec3 next = pos.add(mov);

        EntityHitResult entityHit = ProjectileUtil.getEntityHitResult(
                this.level(), this, pos, next,
                getBoundingBox().expandTowards(mov).inflate(0.3), this::canHitEntity);
        // Officially the disc ricochets off "hard surfaces" (solid blocks).
        // COLLIDER clips against collision shapes, so the disc passes through
        // grass, flowers and torches but bounces off any solid block.
        BlockHitResult blockHit = this.level().clip(new ClipContext(
                pos, next, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));

        boolean blockCloser = blockHit.getType() == HitResult.Type.BLOCK
                && (entityHit == null
                    || blockHit.getLocation().distanceToSqr(pos) < entityHit.getLocation().distanceToSqr(pos));

        if (blockCloser) {
            if (smashThrough(blockHit)) {
                setPos(next.x, next.y, next.z);
            } else {
                ricochet(blockHit);
            }
        } else {
            boolean bouncedOffEntity = false;
            if (entityHit != null) {
                Entity target = entityHit.getEntity();
                onHitEntity(target);
                if (this.isRemoved()) {
                    return; // disc broke on this hit
                }
                if (this.delicate) {
                    reflectOffEntity(target);
                    bouncedOffEntity = true;
                }
            }
            if (!bouncedOffEntity) {
                setPos(next.x, next.y, next.z);
            }
        }

        this.traveled += mov.length();
        if (this.traveled >= this.budget) {
            startReturn();
        }
    }

    private void tickReturning(Entity owner) {
        if (!(owner instanceof Player player)) {
            dropAndDiscard();
            return;
        }
        Vec3 target = new Vec3(player.getX(), player.getY() + 1.2, player.getZ());
        Vec3 toTarget = target.subtract(position());
        double distance = toTarget.length();
        if (distance < CATCH_DISTANCE) {
            catchDisc(player);
            return;
        }
        Vec3 mov = toTarget.normalize().scale(
                baseSpeed() * getTier().speedFactor() * (this.forcedReturn ? 1.7 : 1.1));
        setDeltaMovement(mov);

        // The disc still cuts on the way home: outbound and return are
        // separate phases, so an enemy can be hit once in each direction.
        Vec3 pos = position();
        Vec3 next = pos.add(mov);
        EntityHitResult entityHit = ProjectileUtil.getEntityHitResult(
                this.level(), this, pos, next,
                getBoundingBox().expandTowards(mov).inflate(0.3), this::canHitEntity);
        if (entityHit != null) {
            onHitEntity(entityHit.getEntity());
            if (this.isRemoved()) {
                return;
            }
        }
        setPos(next.x, next.y, next.z);
    }

    // ------------------------------------------------------------------ hits

    @Override
    protected boolean canHitEntity(Entity entity) {
        if (entity == getOwner() || !entity.isAlive()) {
            return false;
        }
        if (this.hitThisPhase.contains(entity.getUUID())) {
            return false;
        }
        if (entity instanceof ItemEntity || entity instanceof DiscProjectileEntity) {
            return false;
        }
        return entity.canBeHitByProjectile();
    }

    private void onHitEntity(Entity target) {
        this.hitThisPhase.add(target.getUUID());
        DiscTier tier = getTier();
        float damage = tier.damage();
        // Zanite grows stronger as the disc itself wears down (The Aether).
        ItemStack stack = getDiscStack();
        if (tier.scalesWithDamage() && stack.getMaxDamage() > 0) {
            damage *= 1.0f + (float) stack.getDamageValue() / (float) stack.getMaxDamage();
        }
        // Skyjade is the inverse: strongest pristine, weaker as it wears (Deep Aether).
        if (tier.scalesWithPristine() && stack.getMaxDamage() > 0) {
            damage *= 1.0f - 0.55f * ((float) stack.getDamageValue() / (float) stack.getMaxDamage());
        }
        // Electrum deals kinetic damage: the faster the disc flies, the
        // harder it hits, up to +50% (Oreganized).
        if (tier.kineticDamage()) {
            double speed = this.getDeltaMovement().length();
            damage *= 1.0f + (float) Math.min(0.5, Math.max(0.0, speed - 1.0) * 0.5);
        }
        if (target instanceof LivingEntity living) {
            // Knightmetal cuts armored targets harder (Twilight Forest).
            if (living.getArmorValue() > 0) {
                damage += tier.bonusVsArmored();
            }
            // Silver bites the undead (Ice and Fire).
            if (living.getMobType() == MobType.UNDEAD) {
                damage *= 1.0f + tier.bonusVsUndead();
            }
            // Dragonbone and dragonsteel bite dragons (Ice and Fire).
            if (isDragon(target)) {
                damage *= 1.0f + tier.bonusVsDragon();
            }
            // The echo disc hums against sculk creatures, in the manner of
            // Deeper and Darker's Sculk Smite.
            if (isSculkMob(target)) {
                damage += tier.bonusVsSculk();
            }
            // Phoenix weapons hit already-burning foes harder (Lost Content).
            if (living.isOnFire()) {
                damage *= 1.0f + tier.bonusVsBurning();
            }
            // Forgotten metal bites the Undergarden's own creatures, and
            // utherium bites its Rotspawn (The Undergarden).
            if (isUndergardenMob(target) && living.getMaxHealth() < 200.0f) {
                damage *= 1.0f + tier.bonusVsUndergarden();
            }
            if (isRotspawn(target)) {
                damage *= 1.0f + tier.bonusVsRotspawn();
            }
            // Magnalyth and Mortyx bite the colossal Behemoths.
            if (isBehemothMob(target)) {
                damage *= 1.0f + tier.bonusVsBehemoth();
            }
        }
        DamageSource source = this.damageSources().thrown(this, getOwner());
        // Caverns and Chasms silver and Malum's soulstained steel convert
        // part of their damage to magic damage, which ignores armour.
        float magicPart = 0.0f;
        if (target instanceof LivingEntity) {
            magicPart = damage * tier.magicSplit();
            damage -= magicPart;
        }
        if (target.hurt(source, damage)) {
            if (magicPart > 0.0f && target instanceof LivingEntity magicTarget) {
                magicTarget.invulnerableTime = 0;
                magicTarget.hurt(this.damageSources().magic(), magicPart);
            }
            if (target instanceof LivingEntity living) {
                double knockback = 0.4 * tier.knockbackMultiplier();
                living.knockback(knockback,
                        Mth.sin(this.getYRot() * ((float) Math.PI / 180.0f)),
                        -Mth.cos(this.getYRot() * ((float) Math.PI / 180.0f)));
                if (tier.igniteSeconds() > 0) {
                    living.setSecondsOnFire(tier.igniteSeconds());
                }
                if (tier.witherSeconds() > 0) {
                    living.addEffect(new MobEffectInstance(
                            MobEffects.WITHER, tier.witherSeconds() * 20, 0));
                }
                if (tier.slowSeconds() > 0) {
                    living.addEffect(new MobEffectInstance(
                            MobEffects.MOVEMENT_SLOWDOWN, tier.slowSeconds() * 20, 1));
                }
                // Gravitite launches its target skyward (The Aether).
                if (tier.launchPower() > 0.0) {
                    living.setDeltaMovement(living.getDeltaMovement().add(0.0, tier.launchPower(), 0.0));
                    living.hurtMarked = true;
                }
                // A steeleaf kill pays out extra experience, after its sword's
                // built-in Looting II (Twilight Forest).
                if (tier.xpOnKill() > 0 && !living.isAlive()
                        && this.level() instanceof ServerLevel serverLevel) {
                    ExperienceOrb.award(serverLevel, living.position(), tier.xpOnKill());
                }
            }
            this.level().playSound(null, this.blockPosition(), SoundEvents.TRIDENT_HIT,
                    SoundSource.PLAYERS, 0.8f, 1.1f);
            damageDisc();
        }
    }

    private static boolean isUndergardenMob(Entity entity) {
        return BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).getNamespace().equals("undergarden");
    }

    private static boolean isRotspawn(Entity entity) {
        var key = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType());
        return key.getNamespace().equals("undergarden") && key.getPath().contains("rot");
    }

    private static boolean isBehemothMob(Entity entity) {
        return BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).getNamespace().equals("behemoths");
    }

    private static boolean isDragon(Entity entity) {
        return BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).getPath().contains("dragon");
    }

    private static boolean isSculkMob(Entity entity) {
        if (entity.getType() == EntityType.WARDEN || entity.getType() == EntityType.PHANTOM) {
            return true;
        }
        return BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).getNamespace().equals("deeperdarker");
    }

    /**
     * Vanilla projectile interactions with the block that was hit, exactly as
     * an arrow or trident would cause them: decorated pots shatter, chorus
     * flowers drop, target blocks trigger, bells ring. Returns true when the
     * block was smashed, in which case the disc flies on instead of bouncing.
     */
    private boolean smashThrough(BlockHitResult blockHit) {
        BlockPos pos = blockHit.getBlockPos();
        BlockState state = this.level().getBlockState(pos);
        state.onProjectileHit(this.level(), state, blockHit, this);
        if (this.level().getBlockState(pos).isAir()) {
            return true;
        }
        // Decorated pots only learned to shatter from projectiles in vanilla
        // 1.20.2; on versions whose pot does not react natively, smash it the
        // same way here.
        if (state.is(Blocks.DECORATED_POT) && this.mayInteract(this.level(), pos)) {
            this.level().destroyBlock(pos, true, this);
            return true;
        }
        return false;
    }

    private void ricochet(BlockHitResult blockHit) {
        Vec3 mov = getDeltaMovement();
        Direction direction = blockHit.getDirection();
        Vec3 reflected = switch (direction.getAxis()) {
            case X -> new Vec3(-mov.x, mov.y, mov.z);
            case Y -> new Vec3(mov.x, -mov.y, mov.z);
            case Z -> new Vec3(mov.x, mov.y, -mov.z);
        };
        double newLength = Math.min(reflected.length() * 1.15, maxSpeed());
        reflected = reflected.normalize().scale(newLength);
        setDeltaMovement(reflected);
        Vec3 at = blockHit.getLocation().add(reflected.normalize().scale(0.06));
        setPos(at.x, at.y, at.z);
        // A bounce is what lets the disc fly farther than its base range,
        // and it may cut enemies it already passed on the new leg.
        this.budget += getTier().ricochetBonus();
        this.hitThisPhase.clear();
        this.level().playSound(null, blockHit.getBlockPos(), SoundEvents.TRIDENT_HIT_GROUND,
                SoundSource.PLAYERS, 0.7f, 1.2f);
    }

    /**
     * Delicate: instead of piercing the creature it just hit, the disc
     * bounces off it like off a wall — same speed-up and range bonus as a
     * block ricochet. The creature stays in this phase's hit set, so the
     * bounce cannot instantly re-hit it, but everything else is fair game
     * again on the new leg.
     */
    private void reflectOffEntity(Entity target) {
        Vec3 mov = getDeltaMovement();
        Vec3 center = target.position().add(0.0, target.getBbHeight() * 0.5, 0.0);
        Vec3 normal = position().subtract(center);
        if (normal.lengthSqr() < 1.0e-4) {
            normal = mov.scale(-1.0);
        }
        normal = normal.normalize();
        Vec3 reflected = mov.subtract(normal.scale(2.0 * mov.dot(normal)));
        if (reflected.lengthSqr() < 1.0e-4) {
            reflected = mov.scale(-1.0);
        }
        double newLength = Math.min(reflected.length() * 1.15, maxSpeed());
        reflected = reflected.normalize().scale(newLength);
        setDeltaMovement(reflected);
        Vec3 at = position().add(reflected.normalize().scale(0.1));
        setPos(at.x, at.y, at.z);
        this.budget += getTier().ricochetBonus();
        this.hitThisPhase.clear();
        this.hitThisPhase.add(target.getUUID());
        this.level().playSound(null, this.blockPosition(), SoundEvents.TRIDENT_HIT_GROUND,
                SoundSource.PLAYERS, 0.7f, 1.2f);
    }

    /** Lose 1 durability per entity damaged; breaking mid-flight ends the flight. */
    private void damageDisc() {
        if (this.splitCopy) {
            return; // phantom copies have no real item to wear down
        }
        ItemStack stack = getDiscStack();
        if (stack.isEmpty()) {
            return;
        }
        if (stack.hurt(1, this.random, null)) {
            this.level().playSound(null, this.blockPosition(), SoundEvents.ITEM_BREAK,
                    SoundSource.PLAYERS, 0.8f, 1.0f);
            discard();
        } else {
            setDiscStack(stack);
        }
    }

    // ------------------------------------------------------------------ return

    /**
     * A disc that wedges itself into geometry barely moves: it covers less
     * than a few centimetres over several ticks, or never leaves the block
     * cell it is stuck in, while a healthy disc crosses a cell in one tick.
     */
    private boolean checkStuck() {
        Vec3 pos = position();
        if (this.lastTickPos != null && pos.distanceToSqr(this.lastTickPos) < 0.0009) {
            this.stuckTicks++;
        } else {
            this.stuckTicks = 0;
        }
        this.lastTickPos = pos;
        BlockPos cell = blockPosition();
        if (cell.equals(this.lastBlockPos)) {
            this.sameBlockTicks++;
        } else {
            this.sameBlockTicks = 0;
            this.lastBlockPos = cell;
        }
        return this.stuckTicks >= 4 || this.sameBlockTicks >= 6;
    }

    /**
     * Escape from a wall: move to the nearest open space — air gaps and
     * liquids both have no collision shape — then come home. If no open
     * space is nearby, the return flight alone is enough, since it passes
     * through blocks.
     */
    private void relocateAndReturn() {
        BlockPos origin = blockPosition();
        Vec3 best = null;
        double bestDist = Double.MAX_VALUE;
        for (int r = 1; r <= 3 && best == null; r++) {
            for (int dx = -r; dx <= r; dx++) {
                for (int dy = -r; dy <= r; dy++) {
                    for (int dz = -r; dz <= r; dz++) {
                        if (Math.max(Math.max(Math.abs(dx), Math.abs(dy)), Math.abs(dz)) != r) {
                            continue;
                        }
                        Vec3 center = Vec3.atCenterOf(origin.offset(dx, dy, dz));
                        if (this.level().noCollision(this,
                                this.getBoundingBox().move(center.subtract(position())))) {
                            double d = center.distanceToSqr(position());
                            if (d < bestDist) {
                                bestDist = d;
                                best = center;
                            }
                        }
                    }
                }
            }
        }
        if (best != null) {
            setPos(best.x, best.y, best.z);
        }
        this.stuckTicks = 0;
        this.sameBlockTicks = 0;
        startReturn();
    }

    private void startReturn() {
        this.returning = true;
        this.entityData.set(DATA_RETURNING, true);
        this.hitThisPhase.clear();
        this.level().playSound(null, this.blockPosition(), SoundEvents.TRIDENT_RETURN,
                SoundSource.PLAYERS, 0.6f, 1.2f);
    }

    private void catchDisc(Player player) {
        if (this.splitCopy) {
            discard(); // a Splitting copy simply rejoins the real disc
            return;
        }
        ItemStack stack = getDiscStack();
        if (!stack.isEmpty()) {
            if (player.getItemInHand(this.fromHand).isEmpty()) {
                player.setItemInHand(this.fromHand, stack);
            } else if (!player.getInventory().add(stack)) {
                player.drop(stack, false);
            }
            this.level().playSound(null, player.blockPosition(), SoundEvents.ITEM_PICKUP,
                    SoundSource.PLAYERS, 0.6f, 1.2f);
        }
        discard();
    }

    private void dropAndDiscard() {
        ItemStack stack = getDiscStack();
        if (!stack.isEmpty() && !this.splitCopy && !this.level().isClientSide) {
            ItemEntity drop = new ItemEntity(this.level(), getX(), getY(), getZ(), stack);
            drop.setDefaultPickUpDelay();
            this.level().addFreshEntity(drop);
        }
        discard();
    }

    // ------------------------------------------------------------------ nbt

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        ItemStack stack = getDiscStack();
        if (!stack.isEmpty()) {
            tag.put("DiscItem", stack.save(new CompoundTag()));
        }
        tag.putBoolean("Returning", this.returning);
        tag.putBoolean("SplitCopy", this.splitCopy);
        tag.putInt("FlightTicks", this.flightTicks);
        tag.putBoolean("ForcedReturn", this.forcedReturn);
        tag.putDouble("Traveled", this.traveled);
        tag.putDouble("Budget", this.budget);
        tag.putByte("FromHand", (byte) (this.fromHand == InteractionHand.OFF_HAND ? 1 : 0));
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("DiscItem")) {
            setDiscStack(ItemStack.of(tag.getCompound("DiscItem")));
        }
        this.returning = tag.getBoolean("Returning");
        this.entityData.set(DATA_RETURNING, this.returning);
        this.splitCopy = tag.getBoolean("SplitCopy");
        this.flightTicks = tag.getInt("FlightTicks");
        this.forcedReturn = tag.getBoolean("ForcedReturn");
        this.traveled = tag.getDouble("Traveled");
        this.budget = tag.getDouble("Budget");
        this.fromHand = tag.getByte("FromHand") == 1
                ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
    }
}
