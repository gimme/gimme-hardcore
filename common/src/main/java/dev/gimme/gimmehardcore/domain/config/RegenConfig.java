package dev.gimme.gimmehardcore.domain.config;

public abstract class RegenConfig {

    public static RegenConfig INSTANCE;

    public abstract float getNaturalRegenerationSpeedMultiplier();

    public abstract float getHealAmount();
    public abstract float getSecondsBetweenHeals();
    public abstract float getCampfireRequiredPlayerRatio();
    public abstract float getCampfireRange();
    public abstract boolean isCampfireOnlyAboveSeaLevel();
    public abstract float getCampfireHealExhaustion();
    public abstract float getCampfireMaxHealToPercentage();
}
