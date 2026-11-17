package com.ruby.throwingdisc.item;

/**
 * One disc material tier. All numbers are this mod's own design values:
 * Mojang has not published damage / durability for the vanilla Throwing Disc,
 * so the iron tier (7 damage, from the community wiki draft) is the baseline
 * and everything else is interpolated against the vanilla material ladder.
 * Stats live here only, so retuning is a one-file change.
 */
public enum DiscTier {
    // registry name, damage, durability, enchantability, speed factor,
    // knockback multiplier, ignite seconds, ricochet range bonus, fire resistant
    COPPER            ("copper_throwing_disc",             6.0f,  190, 13, 1.00f, 1.0, 0,  10.0, false),
    IRON              ("iron_throwing_disc",               7.0f,  250, 14, 1.00f, 1.0, 0,  10.0, false),
    GOLDEN            ("golden_throwing_disc",             6.0f,  128, 25, 1.15f, 1.0, 0,  10.0, false),
    DIAMOND           ("diamond_throwing_disc",            9.0f, 1200, 10, 1.00f, 1.0, 0,  10.0, false),
    NETHERITE         ("netherite_throwing_disc",         10.0f, 1800, 15, 1.00f, 1.0, 0,  10.0, true),
    // Twilight Forest
    IRONWOOD          ("ironwood_throwing_disc",           7.0f,  300, 15, 1.00f, 1.6, 0,  10.0, false),
    STEELEAF          ("steeleaf_throwing_disc",           8.0f,  350, 12, 1.00f, 1.0, 0,  10.0, false),
    KNIGHTMETAL       ("knightmetal_throwing_disc",        9.0f,  900, 10, 1.00f, 1.1, 0,  10.0, false),
    FIERY             ("fiery_throwing_disc",              9.0f, 1000, 10, 1.00f, 1.0, 3,  10.0, true),
    // The Aether
    AMBROSIUM         ("ambrosium_throwing_disc",          5.0f,  120, 12, 1.00f, 0.8, 0,  10.0, false),
    ZANITE            ("zanite_throwing_disc",             7.0f,  300, 12, 1.00f, 1.0, 0,  10.0, false),
    GRAVITITE         ("gravitite_throwing_disc",          9.0f, 1100, 10, 1.00f, 0.6, 0,  10.0, false),
    // Create
    ANDESITE_ALLOY    ("andesite_alloy_throwing_disc",     6.0f,  200,  8, 1.00f, 1.0, 0,  10.0, false),
    ZINC              ("zinc_throwing_disc",               6.0f,  220,  8, 1.00f, 1.0, 0,  10.0, false),
    BRASS             ("brass_throwing_disc",              7.0f,  320, 12, 1.05f, 1.0, 0, 16.0, false),
    // Mekanism
    OSMIUM            ("osmium_throwing_disc",             8.0f,  700, 10, 1.00f, 1.0, 0,  10.0, false),
    STEEL             ("steel_throwing_disc",              8.0f,  800, 10, 1.00f, 1.0, 0,  10.0, false),
    REFINED_GLOWSTONE ("refined_glowstone_throwing_disc",  8.0f,  650, 14, 1.00f, 1.0, 0,  10.0, false),
    REFINED_OBSIDIAN  ("refined_obsidian_throwing_disc",  10.0f, 1600,  8, 1.00f, 1.2, 0,  10.0, true),
    // Deeper and Darker
    REINFORCED_ECHO   ("reinforced_echo_throwing_disc",   10.0f, 2000, 12, 1.00f, 1.2, 0,  10.0, false),
    // L_Ender's Cataclysm
    BLACK_STEEL       ("black_steel_throwing_disc",        8.0f,  900, 10, 1.00f, 1.1, 0,  10.0, false),
    ANCIENT_METAL     ("ancient_metal_throwing_disc",      9.0f, 1300, 10, 1.00f, 1.1, 0,  10.0, false),
    ENDERITE          ("enderite_throwing_disc",           9.0f, 1500, 12, 1.05f, 1.0, 0,  10.0, false),
    IGNITIUM          ("ignitium_throwing_disc",          10.0f, 1900, 12, 1.00f, 1.0, 3,  10.0, true),
    CURSIUM           ("cursium_throwing_disc",           10.0f, 1700, 14, 1.10f, 1.0, 0,  10.0, false),
    WITHERITE         ("witherite_throwing_disc",         10.0f, 2000, 10, 1.00f, 1.2, 0,  10.0, false),
    // Ice and Fire
    SILVER            ("silver_throwing_disc",             6.0f,  250, 16, 1.00f, 1.0, 0,  10.0, false),
    DRAGONBONE        ("dragonbone_throwing_disc",         9.0f, 1300, 10, 1.00f, 1.1, 0,  10.0, false),
    PHANTASMAL        ("phantasmal_throwing_disc",         9.0f,  900, 14, 1.20f, 0.9, 0,  10.0, false),
    DRAGONSTEEL_FIRE  ("dragonsteel_fire_throwing_disc",  11.0f, 2200, 10, 1.00f, 1.1, 3,  10.0, true),
    DRAGONSTEEL_ICE   ("dragonsteel_ice_throwing_disc",   11.0f, 2200, 10, 1.00f, 1.1, 0,  10.0, false),
    DRAGONSTEEL_LIGHTNING ("dragonsteel_lightning_throwing_disc", 11.0f, 2200, 10, 1.15f, 1.1, 0, 10.0, false);

    private final String registryName;
    private final float damage;
    private final int durability;
    private final int enchantability;
    private final float speedFactor;
    private final double knockbackMultiplier;
    private final int igniteSeconds;
    private final double ricochetBonus;
    private final boolean fireResistant;

    DiscTier(String registryName, float damage, int durability, int enchantability,
             float speedFactor, double knockbackMultiplier, int igniteSeconds,
             double ricochetBonus, boolean fireResistant) {
        this.registryName = registryName;
        this.damage = damage;
        this.durability = durability;
        this.enchantability = enchantability;
        this.speedFactor = speedFactor;
        this.knockbackMultiplier = knockbackMultiplier;
        this.igniteSeconds = igniteSeconds;
        this.ricochetBonus = ricochetBonus;
        this.fireResistant = fireResistant;
    }

    public String registryName() { return registryName; }
    public float damage() { return damage; }
    public int durability() { return durability; }
    public int enchantability() { return enchantability; }
    public float speedFactor() { return speedFactor; }
    public double knockbackMultiplier() { return knockbackMultiplier; }
    public int igniteSeconds() { return igniteSeconds; }
    public double ricochetBonus() { return ricochetBonus; }
    public boolean fireResistant() { return fireResistant; }

    /** Witherite discs wither what they cut. */
    public int witherSeconds() { return this == WITHERITE ? 4 : 0; }

    /** Ice dragonsteel chills what it cuts. */
    public int slowSeconds() { return this == DRAGONSTEEL_ICE ? 3 : 0; }

    /** Knightmetal bites armored targets harder (Twilight Forest sword trait). */
    public float bonusVsArmored() { return this == KNIGHTMETAL ? 2.0f : 0.0f; }

    /** Zanite grows stronger the more worn the disc is (The Aether trait). */
    public boolean scalesWithDamage() { return this == ZANITE; }

    /** Gravitite launches its target skyward (The Aether sword trait). */
    public double launchPower() { return this == GRAVITITE ? 0.9 : 0.0; }

    /** Silver bites the undead (Ice and Fire sword trait): damage multiplier bonus. */
    public float bonusVsUndead() { return this == SILVER ? 0.5f : 0.0f; }

    /** Dragonbone and dragonsteel bite dragons (Ice and Fire): multiplier bonus. */
    public float bonusVsDragon() {
        return switch (this) {
            case DRAGONBONE, DRAGONSTEEL_FIRE, DRAGONSTEEL_ICE, DRAGONSTEEL_LIGHTNING -> 0.5f;
            default -> 0.0f;
        };
    }

    /** Flat bonus against sculk creatures, after Deeper and Darker's Sculk Smite. */
    public float bonusVsSculk() { return this == REINFORCED_ECHO ? 5.0f : 0.0f; }

    /** Steeleaf kills pay bonus experience, after its sword's built-in Looting II. */
    public int xpOnKill() { return this == STEELEAF ? 3 : 0; }
}
