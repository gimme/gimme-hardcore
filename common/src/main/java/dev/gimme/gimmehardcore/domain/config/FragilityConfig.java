package dev.gimme.gimmehardcore.domain.config;

public abstract class FragilityConfig {

    public static FragilityConfig INSTANCE;

    public abstract float getShieldDamageMultiplier();
    public abstract float getShieldBreakChance();
}
