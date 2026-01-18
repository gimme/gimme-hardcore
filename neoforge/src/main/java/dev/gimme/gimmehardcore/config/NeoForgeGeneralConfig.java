package dev.gimme.gimmehardcore.config;

import dev.gimme.gimmehardcore.domain.config.GeneralConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class NeoForgeGeneralConfig extends GeneralConfig {

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.BooleanValue PICKAXE_REQUIREMENTS = BUILDER
            .comment("""
                If true, stone requires stone pickaxe to mine, iron requires iron pickaxe and diamond requires diamond pickaxe.
                Hint: You can still acquire blocks through explosions.""")
            .define("pickaxeRequirements", true);

    private static final ModConfigSpec.DoubleValue NATURAL_REGENERATION_SPEED_MULTIPLIER = BUILDER
            .comment("""
                    Multiplier for natural health regeneration speed. Note: this also makes being saturated not heal you faster.
                    For example, setting this to 0.1 makes it take 40 seconds per half heart instead of 4 seconds.
                     Vanilla: set to -1""")
            .defineInRange("naturalRegenerationSpeedMultiplier", 0.1, -1.0, 1.0);

    private static final ModConfigSpec.IntValue MAX_IRON_GOLEM_INGOT_DROPS = BUILDER
            .comment("""
                    Maximum number of iron ingots dropped by iron golems. The minimum becomes 2 less than this value.
                     Vanilla: set to -1 (3-5 ingots)""")
            .defineInRange("maxIronGolemIngotDrops", 3, 0, 5);

    private static final ModConfigSpec.BooleanValue DISABLE_LAVA_BUCKET = BUILDER
            .comment("If true, players cannot use lava buckets.")
            .define("disableLavaBucket", true);

    private static final ModConfigSpec.DoubleValue ENDER_DRAGON_MAX_EXPLOSION_DAMAGE = BUILDER
            .comment("Maximum damage the ender dragon can take from an explosion. Ender Dragon max health is 200.")
            .defineInRange("enderDragonMaxExplosionDamage", 10.0, 1.0, 200.0);

    public static final ModConfigSpec SPEC = BUILDER.build();

    @Override
    public boolean pickaxeRequirements() {
        return PICKAXE_REQUIREMENTS.get();
    }

    @Override
    public float getNaturalRegenerationSpeedMultiplier() {
        return NATURAL_REGENERATION_SPEED_MULTIPLIER.get().floatValue();
    }

    @Override
    public int getMaxIronGolemIngotDrops() {
        return MAX_IRON_GOLEM_INGOT_DROPS.get();
    }

    @Override
    public boolean disableLavaBucket() {
        return DISABLE_LAVA_BUCKET.get();
    }

    @Override
    public float getEnderDragonMaxExplosionDamage() {
        return ENDER_DRAGON_MAX_EXPLOSION_DAMAGE.get().floatValue();
    }
}
