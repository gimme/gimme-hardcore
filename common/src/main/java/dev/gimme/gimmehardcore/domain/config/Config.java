package dev.gimme.gimmehardcore.domain.config;

public abstract class Config {

    public static Config INSTANCE;

    public abstract FragilityConfig getFragilityConfig();
    public abstract HardnessConfig getHardnessConfig();

    public interface FragilityConfig {

        float getShieldDamageMultiplier();
        float getShieldBreakChance();
    }

    public interface HardnessConfig {

        float getStartHardnessMultiplier();
        float getEndHardnessMultiplier();
        int getStartHardnessY();
        int getEndHardnessY();
        int getHardnessSoftCap();
        float getHardnessSoftCapMultiplier();
        float getToolDamageHardnessMultiplier();
        float getExhaustionHardnessMultiplier();
        boolean isHardnessInOverworldOnly();

        default float getHardnessMultiplier(int y) {
            float startMultiplier = getStartHardnessMultiplier();
            float endMultiplier = getEndHardnessMultiplier();
            int startY = getStartHardnessY();
            int endY = getEndHardnessY();

            if (y >= startY) {
                return startMultiplier;
            } else if (y <= endY) {
                return endMultiplier;
            } else {
                float factor = (float) (startY - y) / (startY - endY);
                float hardnessMultiplier = startMultiplier + factor * (endMultiplier - startMultiplier);
                return hardnessMultiplier;
            }
        }

        default float getAdjustedHardness(float defaultHardness, int y) {
            if (defaultHardness == 0.0f) return 0.0f;

            float hardnessMultiplier = getHardnessMultiplier(y);
            float newHardness = defaultHardness * hardnessMultiplier;

            int hardnessSoftCap = getHardnessSoftCap();
            if (newHardness > hardnessSoftCap) {
                float softCapMultiplier = getHardnessSoftCapMultiplier();
                newHardness = hardnessSoftCap + (newHardness - hardnessSoftCap) * softCapMultiplier;
            }

            return newHardness;
        }

        default float getAdjustedExhaustion(float defaultExhaustion, int y) {
            float hardnessMultiplier = getHardnessMultiplier(y);
            float exhaustionHardnessMultiplier = getExhaustionHardnessMultiplier();
            return (defaultExhaustion * (1 + (hardnessMultiplier - 1) * exhaustionHardnessMultiplier));
        }

        default int getAdjustedToolDamage(float defaultDamage, int y) {
            float hardnessMultiplier = getHardnessMultiplier(y);
            float toolDamageHardnessMultiplier = getToolDamageHardnessMultiplier();
            return (int) Math.floor(defaultDamage * (1 + (hardnessMultiplier - 1) * toolDamageHardnessMultiplier));
        }
    }
}
