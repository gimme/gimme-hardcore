package dev.gimme.gimmehardcore.mixin.fragility;

import dev.gimme.gimmehardcore.domain.fragility.Fragility;
import net.minecraft.world.item.component.BlocksAttacks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlocksAttacks.ItemDamageFunction.class)
public class MixinItemDamageFunction {

    /**
     * Modifies shield damage and adds a chance to break the shield completely based on config settings.
     */
    @Inject(method = "apply", at = @At(value = "RETURN"), cancellable = true)
    private void makeShieldMoreFragile(float damageAmount, CallbackInfoReturnable<Integer> cir) {
        var instance = (BlocksAttacks.ItemDamageFunction) (Object) this;
        int newAmount = Fragility.getAdjustedShieldDamage(cir.getReturnValue(), instance);
        cir.setReturnValue(newAmount);
    }
}
