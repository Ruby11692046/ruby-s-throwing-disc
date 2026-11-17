package com.ruby.throwingdisc.registry;

import com.ruby.throwingdisc.ThrowingDiscMod;
import com.ruby.throwingdisc.entity.DiscProjectileEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(Registries.ENTITY_TYPE, ThrowingDiscMod.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<DiscProjectileEntity>> DISC_PROJECTILE =
            ENTITIES.register("disc_projectile",
                    () -> EntityType.Builder.<DiscProjectileEntity>of(DiscProjectileEntity::new, MobCategory.MISC)
                            .sized(0.5f, 0.25f)
                            .clientTrackingRange(8)
                            .updateInterval(1)
                            .build("disc_projectile"));
}
