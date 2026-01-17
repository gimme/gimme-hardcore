package dev.gimme.gimmehardcore.domain.config;

public abstract class GeneralConfig {

    public static GeneralConfig INSTANCE;

    public abstract boolean pickaxeRequirements();
    public abstract float getNaturalRegenerationSpeedMultiplier();
    public abstract int getMaxIronGolemIngotDrops();
    public abstract boolean disableLavaBucket();
}
