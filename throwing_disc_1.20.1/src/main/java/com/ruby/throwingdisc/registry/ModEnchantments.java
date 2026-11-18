package com.ruby.throwingdisc.registry;

import com.ruby.throwingdisc.ThrowingDiscMod;
import com.ruby.throwingdisc.enchantment.DiscEnchantment;
import com.ruby.throwingdisc.item.DiscItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, ThrowingDiscMod.MOD_ID);

    /** Only throwing discs take these enchantments. */
    public static final EnchantmentCategory DISC_CATEGORY =
            EnchantmentCategory.create("throwing_disc", item -> item instanceof DiscItem);

    /** Delicate: the disc bounces off creatures instead of piercing them. */
    public static final RegistryObject<Enchantment> DELICATE = ENCHANTMENTS.register("delicate",
            () -> new DiscEnchantment(Enchantment.Rarity.RARE, DISC_CATEGORY, 1, 15, 0));

    /** Lingering: +2 seconds of flight and +13 blocks of range per level. */
    public static final RegistryObject<Enchantment> LINGERING = ENCHANTMENTS.register("lingering",
            () -> new DiscEnchantment(Enchantment.Rarity.RARE, DISC_CATEGORY, 3, 12, 10));

    /** Splitting: one extra disc per level, thrown in a fan. */
    public static final RegistryObject<Enchantment> SPLITTING = ENCHANTMENTS.register("splitting",
            () -> new DiscEnchantment(Enchantment.Rarity.VERY_RARE, DISC_CATEGORY, 4, 20, 12));
}
