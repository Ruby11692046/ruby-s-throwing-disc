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
    // Enderite Mod (its gear is upgraded from netherite, so is this disc)
    ENDERITE_MOD      ("enderite_mod_throwing_disc",      11.0f, 2400, 15, 1.00f, 1.0, 0,  10.0, true),
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
    DRAGONSTEEL_LIGHTNING ("dragonsteel_lightning_throwing_disc", 11.0f, 2200, 10, 1.15f, 1.1, 0, 10.0, false),
    // Caverns and Chasms
    CAC_SILVER        ("caverns_and_chasms_silver_throwing_disc", 6.5f, 300, 14, 1.00f, 1.0, 0, 10.0, false),
    NECROMIUM         ("necromium_throwing_disc",          9.5f, 1500, 12, 1.00f, 1.0, 0,  10.0, false),
    // Oreganized
    ELECTRUM          ("electrum_throwing_disc",           8.0f,  800, 18, 1.05f, 1.0, 0,  10.0, false),
    OREGANIZED_SILVER ("oreganized_silver_throwing_disc",  6.5f,  300, 14, 1.00f, 1.0, 0,  10.0, false),
    // Deep Aether
    SKYJADE           ("skyjade_throwing_disc",            9.0f,  700, 12, 1.00f, 1.0, 0,  10.0, false),
    STRATUS           ("stratus_throwing_disc",           10.5f, 2200, 14, 1.00f, 0.6, 0,  10.0, false),
    // Just Dire Things
    FERRICORE         ("ferricore_throwing_disc",          6.5f,  500, 15, 1.00f, 1.0, 0,  10.0, false),
    BLAZEGOLD         ("blazegold_throwing_disc",          7.5f, 1440, 22, 1.00f, 1.0, 0,  10.0, true),
    CELESTIGEM        ("celestigem_throwing_disc",         8.5f, 1561, 18, 1.00f, 1.0, 0,  10.0, false),
    ECLIPSE_ALLOY     ("eclipse_alloy_throwing_disc",     10.0f, 2561, 25, 1.00f, 1.0, 0,  10.0, true),
    // The Undergarden
    CLOGGRUM          ("cloggrum_throwing_disc",           7.0f,  550, 12, 1.00f, 1.0, 0,  10.0, false),
    FORGOTTEN         ("forgotten_throwing_disc",          9.0f, 1500, 14, 1.00f, 1.0, 0,  10.0, false),
    FROSTSTEEL        ("froststeel_throwing_disc",         8.5f, 1100, 12, 1.00f, 1.0, 0,  10.0, false),
    UTHERIUM          ("utherium_throwing_disc",           9.0f, 1300, 12, 1.00f, 1.0, 0,  10.0, false),
    // Unusual End
    PEARLESCENT       ("pearlescent_throwing_disc",        9.0f, 1024, 16, 1.00f, 1.0, 0,  10.0, false),
    // Eternal Starlight
    DEEPSILVER        ("deepsilver_throwing_disc",         7.0f,  450, 14, 1.00f, 1.0, 0,  10.0, false),
    THERMAL_SPRINGSTONE ("thermal_springstone_throwing_disc", 8.0f, 900, 12, 1.00f, 1.0, 2, 10.0, false),
    GLACITE           ("glacite_throwing_disc",            8.5f, 1000, 12, 1.00f, 1.0, 0,  10.0, false),
    STARLIT_DIAMOND   ("starlit_diamond_throwing_disc",    9.0f, 1400, 16, 1.00f, 1.0, 0,  10.0, false),
    AMARAMBER         ("amaramber_throwing_disc",          8.0f,  850, 10, 1.00f, 1.0, 0,  10.0, false),
    MALARITE          ("malarite_throwing_disc",           9.5f, 1600, 14, 1.00f, 1.0, 0,  10.0, false),
    UNREALIUM         ("unrealium_throwing_disc",         10.0f, 2000, 12, 1.00f, 1.0, 0,  10.0, false),
    // Malum
    SOULSTAINED_STEEL ("soulstained_steel_throwing_disc",  8.5f, 1000, 16, 1.00f, 1.0, 0,  10.0, false),
    // Occultism
    IESNIUM           ("iesnium_throwing_disc",            7.5f,  800, 10, 1.00f, 1.0, 0,  10.0, false),
    // Behemoths
    MAGNALYTH         ("magnalyth_throwing_disc",          9.5f, 1800, 12, 1.00f, 1.1, 0,  10.0, false),
    MORTYX            ("mortyx_throwing_disc",            10.5f, 2031, 14, 1.00f, 1.1, 0,  10.0, false),
    // Aether Redux
    VERIDIUM          ("veridium_throwing_disc",           8.0f,  900, 14, 1.00f, 1.0, 0,  10.0, false),
    // Mana and Artifice
    VINTEUM           ("vinteum_throwing_disc",            7.5f,  700, 18, 1.00f, 1.0, 0,  10.0, false),
    // Blaze Gear
    BRIMSTEEL         ("brimsteel_throwing_disc",          9.0f, 1200, 12, 1.00f, 1.0, 3,  10.0, true),
    // Blue Skies
    PYROPE            ("pyrope_throwing_disc",             6.0f,  200, 10, 1.15f, 0.9, 0,  10.0, false),
    AQUITE            ("aquite_throwing_disc",             7.0f,  250, 12, 1.00f, 1.0, 0,  10.0, false),
    DIOPSIDE          ("diopside_throwing_disc",           9.0f, 1400,  8, 0.95f, 1.2, 0,  10.0, false),
    CHAROITE          ("charoite_throwing_disc",           8.5f, 1100, 14, 1.00f, 1.0, 0,  10.0, false),
    HORIZONITE        ("horizonite_throwing_disc",         9.0f, 1300, 10, 1.00f, 1.0, 3,  10.0, true),
    // Aether Lost Content (no recipe: found in Platinum Dungeon treasure, like the Phoenix tools)
    PHOENIX           ("phoenix_throwing_disc",            9.5f, 1800, 14, 1.00f, 1.0, 4,  10.0, true),;

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

    /**
     * The mod that must be installed for this disc to exist at all, or null
     * for the vanilla tiers. Discs whose mod is absent are never registered:
     * they do not appear in the creative tab, in JEI, or anywhere else.
     */
    public String requiredMod() {
        return switch (this) {
            case IRONWOOD, STEELEAF, KNIGHTMETAL, FIERY -> "twilightforest";
            case AMBROSIUM, ZANITE, GRAVITITE -> "aether";
            case ANDESITE_ALLOY, ZINC, BRASS -> "create";
            case OSMIUM, STEEL, REFINED_GLOWSTONE, REFINED_OBSIDIAN -> "mekanism";
            case BLACK_STEEL, ANCIENT_METAL, ENDERITE, IGNITIUM, CURSIUM, WITHERITE -> "cataclysm";
            case SILVER, DRAGONBONE, PHANTASMAL, DRAGONSTEEL_FIRE, DRAGONSTEEL_ICE, DRAGONSTEEL_LIGHTNING -> "iceandfire";
            case ENDERITE_MOD -> "enderitemod";
            case CAC_SILVER, NECROMIUM -> "caverns_and_chasms";
            case ELECTRUM, OREGANIZED_SILVER -> "oreganized";
            case SKYJADE, STRATUS -> "deep_aether";
            case FERRICORE, BLAZEGOLD, CELESTIGEM, ECLIPSE_ALLOY -> "justdirethings";
            case CLOGGRUM, FORGOTTEN, FROSTSTEEL, UTHERIUM -> "undergarden";
            case PEARLESCENT -> "unusualend";
            case DEEPSILVER, THERMAL_SPRINGSTONE, GLACITE, STARLIT_DIAMOND, AMARAMBER, MALARITE, UNREALIUM -> "eternal_starlight";
            case SOULSTAINED_STEEL -> "malum";
            case IESNIUM -> "occultism";
            case MAGNALYTH, MORTYX -> "behemoths";
            case VERIDIUM -> "aether_redux";
            case VINTEUM -> "mna";
            case BRIMSTEEL -> "blazegear";
            case PYROPE, AQUITE, DIOPSIDE, CHAROITE, HORIZONITE -> "blue_skies";
            case PHOENIX -> "lost_aether_content";
            default -> null;
        };
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
    public int slowSeconds() {
        return switch (this) {
            case DRAGONSTEEL_ICE, NECROMIUM, FROSTSTEEL, GLACITE -> 3;
            default -> 0;
        };
    }

    /** Knightmetal bites armored targets harder (Twilight Forest sword trait). */
    public float bonusVsArmored() { return this == KNIGHTMETAL ? 2.0f : 0.0f; }

    /** Zanite grows stronger the more worn the disc is (The Aether trait). */
    public boolean scalesWithDamage() { return this == ZANITE; }

    /** Gravitite launches its target skyward (The Aether sword trait). */
    public double launchPower() {
        return switch (this) {
            case GRAVITITE -> 0.9;
            case STRATUS -> 1.3;
            default -> 0.0;
        };
    }

    /** Silver bites the undead (Ice and Fire sword trait): damage multiplier bonus. */
    public float bonusVsUndead() {
        return switch (this) {
            case SILVER -> 0.5f;
            case MORTYX -> 0.15f;
            default -> 0.0f;
        };
    }

    /** Dragonbone and dragonsteel bite dragons (Ice and Fire): multiplier bonus. */
    public float bonusVsDragon() {
        return switch (this) {
            case DRAGONBONE, DRAGONSTEEL_FIRE, DRAGONSTEEL_ICE, DRAGONSTEEL_LIGHTNING -> 0.5f;
            default -> 0.0f;
        };
    }

    /** Flat bonus against sculk creatures, after Deeper and Darker's Sculk Smite. */
    public float bonusVsSculk() { return 0.0f; } // the Reinforced Echo disc is 1.21.1-only


    /** Part of the damage is dealt as magic damage, which ignores armour
     *  (Caverns and Chasms silver; Malum's soulstained steel). */
    public float magicSplit() {
        return switch (this) {
            case CAC_SILVER, SOULSTAINED_STEEL -> 0.5f;
            default -> 0.0f;
        };
    }

    /** Skyjade is the inverse of zanite: strongest pristine, weaker as it wears (Deep Aether). */
    public boolean scalesWithPristine() { return this == SKYJADE; }

    /** Electrum deals kinetic damage: the faster it flies, the harder it hits (Oreganized). */
    public boolean kineticDamage() { return this == ELECTRUM; }

    /** Phoenix weapons hit burning foes harder (Aether Lost Content). */
    public float bonusVsBurning() { return this == PHOENIX ? 0.2f : 0.0f; }

    /** Forgotten metal bites the Undergarden's own creatures (The Undergarden). */
    public float bonusVsUndergarden() { return this == FORGOTTEN ? 0.5f : 0.0f; }

    /** Utherium bites the Rotspawn (The Undergarden). */
    public float bonusVsRotspawn() { return this == UTHERIUM ? 0.5f : 0.0f; }

    /** Magnalyth and Mortyx bite the Behemoths. */
    public float bonusVsBehemoth() {
        return switch (this) {
            case MAGNALYTH, MORTYX -> 0.2f;
            default -> 0.0f;
        };
    }

    /** Extra base flight range, after the Pearlescent tier's long reach (Unusual End). */
    public double rangeBonus() { return this == PEARLESCENT ? 8.0 : 0.0; }

    /** Blazegold repairs itself in lava while carried (Just Dire Things). */
    public boolean lavaRepairs() { return this == BLAZEGOLD; }

    /** Steeleaf kills pay bonus experience, after its sword's built-in Looting II. */
    public int xpOnKill() { return this == STEELEAF ? 3 : 0; }
}
