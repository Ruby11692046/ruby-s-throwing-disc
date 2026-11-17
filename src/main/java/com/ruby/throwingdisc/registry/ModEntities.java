package com.ruby.throwingdisc.registry;

import com.ruby.throwingdisc.ThrowingDiscMod;
import com.ruby.throwingdisc.entity.DiscProjectileEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ThrowingDiscMod.MOD_ID);

    public static final RegistryObject<EntityType<DiscProjectileEntity>> DISC_PROJECTILE = ENTITIES.register(
            "disc_projectile",
            () -> EntityType.Builder.<DiscProjectileEntity>of(DiscProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.25f)
                    .clientTrackingRange(8)
                    .updateInterval(1)
                    .build("disc_projectile"));
}
