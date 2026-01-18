package dev.gimme.gimmehardcore.config;

import dev.gimme.gimmehardcore.domain.config.RegenConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class NeoForgeRegenConfig extends RegenConfig {

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.DoubleValue NATURAL_REGENERATION_SPEED_MULTIPLIER = BUILDER
            .comment("""
                    Multiplier for natural health regeneration speed. Note: this also makes being saturated not heal you faster.
                    For example, setting this to 0.1 makes it take 40 seconds per half heart instead of 4 seconds.
                     Vanilla: set to -1""")
            .defineInRange("naturalRegenerationSpeedMultiplier", 0.0, -1.0, 1.0);

    private static final ModConfigSpec.DoubleValue CAMPFIRE_HEAL_AMOUNT = BUILDER
            .comment("Amount of health restored to each player when Campfire regeneration triggers.")
            .defineInRange("campfireHealAmount", 0.5, 0.0, 20.0);

    private static final ModConfigSpec.DoubleValue CAMPFIRE_INTERVAL_SECONDS = BUILDER
            .comment("Seconds between each heal tick when Campfire regeneration is active.")
            .defineInRange("campfireIntervalSeconds", 2, 0.5, 60.0);

    private static final ModConfigSpec.DoubleValue CAMPFIRE_RANGE_CONFIG = BUILDER
            .comment("Range (in blocks) around the Campfire within which players must be present to activate the effect.")
            .defineInRange("campfireRange", 3.0, 1.0, 10.0);

    private static final ModConfigSpec.DoubleValue CAMPFIRE_REQUIRED_PLAYER_RATIO = BUILDER
            .comment("Ratio of online players required to be near the same Campfire to activate regeneration.")
            .defineInRange("campfireRequiredPlayerRatio", 0.51, 0.0, 1.0);

    private static final ModConfigSpec.BooleanValue CAMPFIRE_ONLY_ABOVE_SEA_LEVEL = BUILDER
            .comment("If true, Campfire regeneration only works above sea level.")
            .define("campfireOnlyAboveSeaLevel", true);

    private static final ModConfigSpec.DoubleValue CAMPFIRE_HEAL_EXHAUSTION = BUILDER
            .comment("""
                Amount of exhaustion applied to players when they receive a heal from Campfire regeneration.
                For reference, natural regeneration in vanilla Minecraft applies 6.0 exhaustion per 1 (half heart) healed.
                If this is above 0, players will only heal if they have foodLevel >= 18.
                """)
            .defineInRange("campfireHealExhaustion", 3.0, 0.0, 100.0);

    private static final ModConfigSpec.DoubleValue CAMPFIRE_MAX_HEAL_TO_PERCENTAGE = BUILDER
            .comment("""
                Maximum health percentage (0.0–1.0) up to which Campfires can heal players.
                For example, setting this to 0.8 means players will only be healed by Campfires up to 8 hearts.
                """)
            .defineInRange("campfireMaxHealToPercentage", 1.0, 0.0, 1.0);

    public static final ModConfigSpec SPEC = BUILDER.build();

    @Override
    public float getNaturalRegenerationSpeedMultiplier() {
        return NATURAL_REGENERATION_SPEED_MULTIPLIER.get().floatValue();
    }

    @Override
    public float getHealAmount() {
        return CAMPFIRE_HEAL_AMOUNT.get().floatValue();
    }

    @Override
    public float getSecondsBetweenHeals() {
        return CAMPFIRE_INTERVAL_SECONDS.get().floatValue();
    }

    @Override
    public float getCampfireRange() {
        return CAMPFIRE_RANGE_CONFIG.get().floatValue();
    }

    @Override
    public float getCampfireRequiredPlayerRatio() {
        return CAMPFIRE_REQUIRED_PLAYER_RATIO.get().floatValue();
    }

    @Override
    public boolean isCampfireOnlyAboveSeaLevel() {
        return CAMPFIRE_ONLY_ABOVE_SEA_LEVEL.get();
    }

    @Override
    public float getCampfireHealExhaustion() {
        return CAMPFIRE_HEAL_EXHAUSTION.get().floatValue();
    }

    @Override
    public float getCampfireMaxHealToPercentage() {
        return CAMPFIRE_MAX_HEAL_TO_PERCENTAGE.get().floatValue();
    }
}
