package dev.gimme.gimmehardcore.config;

import dev.gimme.gimmehardcore.domain.config.HardnessConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class NeoForgeHardnessConfig extends HardnessConfig {

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.DoubleValue START_HARDNESS_MULTIPLIER = BUILDER
            .comment("""
                Multiplier for block hardness at the starting Y level and above.
                Vanilla: 1.0""")
            .defineInRange("startHardnessMultiplier", 1.0, 0.0, 10.0);

    private static final ModConfigSpec.DoubleValue END_HARDNESS_MULTIPLIER = BUILDER
            .comment("Multiplier for block hardness at the ending Y level and below.")
            .defineInRange("endHardnessMultiplier", 8.0, 0.0, 10.0);

    private static final ModConfigSpec.IntValue START_HARDNESS_Y = BUILDER
            .comment("Y level at which the hardness starts to increase.")
            .defineInRange("startHardnessY", 62, -64, 320);

    private static final ModConfigSpec.IntValue END_HARDNESS_Y = BUILDER
            .comment("Y level at which the hardness reaches its maximum multiplier.")
            .defineInRange("endHardnessY", -64, -64, 320);

    private static final ModConfigSpec.IntValue HARDNESS_SOFT_CAP = BUILDER
            .comment("Hardness value above which the soft cap multiplier is applied. Obsidian has a hardness of 50.")
            .defineInRange("hardnessSoftCap", 50, 1, 100);

    private static final ModConfigSpec.DoubleValue HARDNESS_SOFT_CAP_MULTIPLIER = BUILDER
            .comment("""
                Multiplier applied to hardness values above the soft cap.
                For example, a value of 0.2 means that an excess hardness of 10 above the soft cap will only put the final hardness
                2 above the soft cap.""")
            .defineInRange("hardnessSoftCapMultiplier", 0.2, 0.0, 1.0);

    private static final ModConfigSpec.DoubleValue TOOL_DAMAGE_HARDNESS_MULTIPLIER = BUILDER
            .comment("How much tool damage is affected by the adjusted block hardness.")
            .defineInRange("toolDamageHardnessMultiplier", 1.0, 0.0, 10.0);

    private static final ModConfigSpec.DoubleValue EXHAUSTION_HARDNESS_MULTIPLIER = BUILDER
            .comment("How much exhaustion is affected by the adjusted block hardness.")
            .defineInRange("exhaustionHardnessMultiplier", 2.0, 0.0, 10.0);

    private static final ModConfigSpec.BooleanValue HARDNESS_IN_OVERWORLD_ONLY = BUILDER
            .comment("If true, hardness adjustments only apply in the Overworld dimension.")
            .define("hardnessInOverworldOnly", true);

    public static final ModConfigSpec SPEC = BUILDER.build();

    @Override
    public float getStartHardnessMultiplier() {
        return START_HARDNESS_MULTIPLIER.get().floatValue();
    }

    @Override
    public float getEndHardnessMultiplier() {
        return END_HARDNESS_MULTIPLIER.get().floatValue();
    }

    @Override
    public int getStartHardnessY() {
        return START_HARDNESS_Y.get();
    }

    @Override
    public int getEndHardnessY() {
        return END_HARDNESS_Y.get();
    }

    @Override
    public int getHardnessSoftCap() {
        return HARDNESS_SOFT_CAP.get();
    }

    @Override
    public float getHardnessSoftCapMultiplier() {
        return HARDNESS_SOFT_CAP_MULTIPLIER.get().floatValue();
    }

    @Override
    public float getToolDamageHardnessMultiplier() {
        return TOOL_DAMAGE_HARDNESS_MULTIPLIER.get().floatValue();
    }

    @Override
    public float getExhaustionHardnessMultiplier() {
        return EXHAUSTION_HARDNESS_MULTIPLIER.get().floatValue();
    }

    @Override
    public boolean isHardnessInOverworldOnly() {
        return HARDNESS_IN_OVERWORLD_ONLY.get();
    }
}
