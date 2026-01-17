package dev.gimme.gimmehardcore.mixin.regeneration;

import dev.gimme.gimmehardcore.domain.config.GeneralConfig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoodData.class)
public class MixinFoodData {

    @Shadow private int tickTimer;

    /**
     * Slows down natural health regeneration from saturation.
     */
    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;heal(F)V", ordinal = 0), cancellable = true)
    private void beforeBigHeal(Player player, CallbackInfo ci) {
        var regenSpeedMultiplier = GeneralConfig.INSTANCE.getNaturalRegenerationSpeedMultiplier();
        if (regenSpeedMultiplier == 1) return;

        if (this.tickTimer < (int) (10 / regenSpeedMultiplier)) {
            ci.cancel();
        }
    }

    /**
     * Slows down natural health regeneration from having high food level.
     */
    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;heal(F)V", ordinal = 1), cancellable = true)
    private void beforeSmallHeal(Player player, CallbackInfo ci) {
        var regenSpeedMultiplier = GeneralConfig.INSTANCE.getNaturalRegenerationSpeedMultiplier();
        if (regenSpeedMultiplier == 1) return;

        if (this.tickTimer < (int) (80 / regenSpeedMultiplier)) {
            ci.cancel();
        }
    }
}
