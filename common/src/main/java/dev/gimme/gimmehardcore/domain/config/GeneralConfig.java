package dev.gimme.gimmehardcore.domain.config;

public abstract class GeneralConfig {

    public static GeneralConfig INSTANCE;

    public abstract int getMaxIronGolemIngotDrops();
    public abstract boolean disableLavaBucket();
    public abstract float getEnderDragonMaxExplosionDamage();
}
