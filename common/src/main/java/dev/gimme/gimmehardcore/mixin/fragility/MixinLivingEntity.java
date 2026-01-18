package dev.gimme.gimmehardcore.mixin.fragility;

import dev.gimme.gimmehardcore.domain.config.FragilityConfig;
import dev.gimme.gimmehardcore.domain.util.Constants;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class MixinLivingEntity {

    /**
     * Modifies armor absorption based on config settings.
     */
    @Inject(method = "getArmorValue", at = @At(value = "RETURN"), cancellable = true)
    private void makeArmorAbsorbLess(CallbackInfoReturnable<Integer> cir) {
        var instance = (LivingEntity) (Object) this;
        if (!(instance instanceof ServerPlayer)) return;

        int originalArmorValue = cir.getReturnValue();
        int newArmorValue = (int) Math.floor(originalArmorValue * FragilityConfig.INSTANCE.getArmorAbsorbMultiplier());
        Constants.LOG.debug("Modifying armor value from {} to {}", originalArmorValue, newArmorValue);
        cir.setReturnValue(newArmorValue);
    }
}
