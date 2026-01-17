package dev.gimme.gimmehardcore.domain.config;

public abstract class FragilityConfig {

    public static FragilityConfig INSTANCE;

    public abstract float getArmorDamageMultiplier();
    public abstract float getArmorAbsorbMultiplier();

    public abstract float getShieldDamageMultiplier();
    public abstract float getMinShieldBreakChance();
    public abstract float getMaxShieldBreakChance();
}
