package dev.gimme.gimmehardcore.config;

import dev.gimme.gimmehardcore.domain.config.FragilityConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class NeoForgeFragilityConfig extends FragilityConfig {

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.DoubleValue SHIELD_DAMAGE_MULTIPLIER = BUILDER
            .comment("""
                Multiplier for shield durability damage when blocking attacks.
                 Vanilla: 1.0""")
            .defineInRange("shieldDamageMultiplier", 2.0, 0.0, 10.0);

    private static final ModConfigSpec.DoubleValue SHIELD_BREAK_CHANCE = BUILDER
            .comment("Chance for shields to break completely when blocking an attack.")
            .defineInRange("shieldBreakChance", 0.05, 0.0, 1.0);

    public static final ModConfigSpec SPEC = BUILDER.build();

    @Override
    public float getShieldDamageMultiplier() {
        return SHIELD_DAMAGE_MULTIPLIER.get().floatValue();
    }

    @Override
    public float getShieldBreakChance() {
        return SHIELD_BREAK_CHANCE.get().floatValue();
    }
}
