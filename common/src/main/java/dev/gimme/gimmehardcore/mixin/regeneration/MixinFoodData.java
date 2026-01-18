package dev.gimme.gimmehardcore.mixin.regeneration;

import dev.gimme.gimmehardcore.domain.config.GeneralConfig;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.food.FoodData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoodData.class)
public class MixinFoodData {

    @Shadow private int tickTimer;

    /**
     * Slows down natural health regeneration from saturation.
     */
    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;heal(F)V", ordinal = 0), cancellable = true)
    private void beforeBigHeal(ServerPlayer player, CallbackInfo ci) {
        if (gimme_hardcore$isModRegenFeatureDisabled()) return;

        float healRatio = Math.min(((FoodData) (Object) this).getSaturationLevel(), 6.0F) / 6.0F;

        if (this.tickTimer < (int) (healRatio * gimme_hardcore$getRegenTicks())) {
            ci.cancel();
        }
    }

    /**
     * Slows down natural health regeneration from having high food level.
     */
    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;heal(F)V", ordinal = 1), cancellable = true)
    private void beforeSmallHeal(ServerPlayer player, CallbackInfo ci) {
        if (gimme_hardcore$isModRegenFeatureDisabled()) return;

        if (this.tickTimer < (int) gimme_hardcore$getRegenTicks()) {
            ci.cancel();
        }
    }

    @Unique
    private static float gimme_hardcore$getRegenTicks() {
        float regenSpeedMultiplier = GeneralConfig.INSTANCE.getNaturalRegenerationSpeedMultiplier();
        return 80 / regenSpeedMultiplier;
    }

    @Unique
    private static boolean gimme_hardcore$isModRegenFeatureDisabled() {
        return GeneralConfig.INSTANCE.getNaturalRegenerationSpeedMultiplier() < 0;
    }
}
