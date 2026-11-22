package com.ruby.throwingdisc.config;

import net.neoforged.neoforge.common.ModConfigSpec;

/**
 * Common configuration (per world, synced to clients).
 */
public final class DiscConfig {
    public static final ModConfigSpec SPEC;
    private static final ModConfigSpec.DoubleValue DISC_SPEED;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        DISC_SPEED = builder
                .comment("Flight speed of the discs. 1.0 is the standard speed; "
                        + "each material's own speed trait still applies on top of this.")
                .defineInRange("discSpeed", 1.0, 0.1, 3.0);
        SPEC = builder.build();
    }

    private DiscConfig() {
    }

    /** The configured disc flight speed, or 1.0 until the config is loaded. */
    public static float speed() {
        try {
            return DISC_SPEED.get().floatValue();
        } catch (RuntimeException e) {
            return 1.0f;
        }
    }
}
