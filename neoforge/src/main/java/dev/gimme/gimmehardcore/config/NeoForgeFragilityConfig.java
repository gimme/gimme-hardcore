package dev.gimme.gimmehardcore.config;

import dev.gimme.gimmehardcore.domain.config.FragilityConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class NeoForgeFragilityConfig extends FragilityConfig {

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.DoubleValue ARMOR_DAMAGE_MULTIPLIER = BUILDER
            .comment("""
                Multiplier for armor durability damage when taking damage.
                 Vanilla: 1.0""")
            .defineInRange("armorDamageMultiplier", 8.0, 1.0, 1000.0);

    private static final ModConfigSpec.DoubleValue ARMOR_ABSORB_MULTIPLIER = BUILDER
            .comment("""
                Multiplier for how much damage armor absorbs.
                 Vanilla: 1.0""")
            .defineInRange("armorAbsorbMultiplier", 0.5, 0.0, 1.0);

    private static final ModConfigSpec.DoubleValue SHIELD_DAMAGE_MULTIPLIER = BUILDER
            .comment("""
                Multiplier for shield durability damage when blocking attacks.
                 Vanilla: 1.0""")
            .defineInRange("shieldDamageMultiplier", 4.0, 1.0, 1000.0);

    private static final ModConfigSpec.DoubleValue SHIELD_BREAK_CHANCE = BUILDER
            .comment("Chance for shields to break completely when they take damage.")
            .defineInRange("shieldBreakChance", 0.05, 0.0, 1.0);

    public static final ModConfigSpec SPEC = BUILDER.build();

    @Override
    public float getArmorDamageMultiplier() {
        return ARMOR_DAMAGE_MULTIPLIER.get().floatValue();
    }

    @Override
    public float getArmorAbsorbMultiplier() {
        return ARMOR_ABSORB_MULTIPLIER.get().floatValue();
    }

    @Override
    public float getShieldDamageMultiplier() {
        return SHIELD_DAMAGE_MULTIPLIER.get().floatValue();
    }

    @Override
    public float getShieldBreakChance() {
        return SHIELD_BREAK_CHANCE.get().floatValue();
    }
}
