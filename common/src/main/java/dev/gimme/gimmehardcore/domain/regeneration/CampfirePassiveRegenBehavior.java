package dev.gimme.gimmehardcore.domain.regeneration;

import dev.gimme.gimmehardcore.domain.config.RegenConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.phys.AABB;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Handles passive regeneration behavior around campfires.
 */
public class CampfirePassiveRegenBehavior {

    private static final Map<CampfireBlockEntity, MutableInt> campfireTickTimers = new WeakHashMap<>();

    private static class MutableInt {
        public int value = 0;
    }

    /**
     * Ticks campfire regeneration logic.
     *
     * @param level    The level the campfire is in
     * @param campfire The campfire block entity
     */
    public static void tickCampfireRegen(ServerLevel level, CampfireBlockEntity campfire) {
        if (RegenConfig.INSTANCE.getHealAmount() <= 0) return;
        if (RegenConfig.INSTANCE.isCampfireOnlyAboveSeaLevel() && campfire.getBlockPos().getY() <= level.getSeaLevel())
            return;

        MutableInt tickTimer = campfireTickTimers.computeIfAbsent(campfire, k -> new MutableInt());

        AABB range = new AABB(campfire.getBlockPos()).inflate(RegenConfig.INSTANCE.getCampfireRange());
        List<ServerPlayer> playersInRange = level.getEntitiesOfClass(ServerPlayer.class, range);
        float playerRequirement = level.players().size() * RegenConfig.INSTANCE.getCampfireRequiredPlayerRatio();

        if (playersInRange.size() >= playerRequirement) {
            tickTimer.value++;

            playersInRange.forEach(player -> {
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 5, 0, true, true, true));
            });
        } else if (tickTimer.value > 0) {
            tickTimer.value--;
        }

        if (tickTimer.value >= 20 * RegenConfig.INSTANCE.getSecondsBetweenHeals()) {
            tickTimer.value = 0;

            playersInRange.forEach(player -> {
                var exhaustionCost = RegenConfig.INSTANCE.getCampfireHealExhaustion();
                var maxHealTo = player.getMaxHealth() * RegenConfig.INSTANCE.getCampfireMaxHealToPercentage();
                float healAmount = Math.min(RegenConfig.INSTANCE.getHealAmount(), maxHealTo - player.getHealth());

                if (healAmount > 0 && (player.getFoodData().getFoodLevel() >= 18 || exhaustionCost == 0)) {
                    player.heal(healAmount);
                    player.causeFoodExhaustion(exhaustionCost);
                }
            });
        }
    }
}
