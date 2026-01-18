package dev.gimme.gimmehardcore.domain.fragility;

import dev.gimme.gimmehardcore.domain.config.FragilityConfig;
import dev.gimme.gimmehardcore.domain.util.Constants;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.component.BlocksAttacks;

public class Fragility {

    private static final RandomSource RANDOM = RandomSource.create();

    /**
     * Returns the modified shield damage including a chance to break completely based on config settings.
     */
    public static int getAdjustedShieldDamage(int originalAmount, BlocksAttacks.ItemDamageFunction originalFunction) {
        if (originalAmount <= 0) return originalAmount;

        var fragilityConfig = FragilityConfig.INSTANCE;
        int newAmount = (int) (originalFunction.base() + Math.floor((originalAmount - originalFunction.base()) * fragilityConfig.getShieldDamageMultiplier()));

        // Risk to break completely
        float breakChance = fragilityConfig.getShieldBreakChance();
        if (RANDOM.nextFloat() < breakChance) {
            newAmount = 10000;
            Constants.LOG.debug("Shield break chance succeeded (chance: {}), breaking shield completely", breakChance);
        } else {
            Constants.LOG.debug("Modifying shield damage from {} to {}", originalAmount, newAmount);
        }

        return newAmount;
    }
}
