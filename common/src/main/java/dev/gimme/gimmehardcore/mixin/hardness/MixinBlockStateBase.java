package dev.gimme.gimmehardcore.mixin.hardness;

import dev.gimme.gimmehardcore.domain.config.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin to adjust block hardness.
 */
@Mixin(BlockStateBase.class)
public class MixinBlockStateBase {

    /**
     * Adjusts block hardness based on Y level and config settings.
     */
    @Inject(at = @At("RETURN"), method = "getDestroySpeed", cancellable = true)
    private void onGetDestroySpeed(BlockGetter level, BlockPos pos, CallbackInfoReturnable<Float> cir) {
        float originalHardness = cir.getReturnValue();
        var hardnessConfig = Config.INSTANCE.getHardnessConfig();

        if (level instanceof Level realLevel && hardnessConfig.isHardnessInOverworldOnly() && !realLevel.dimensionType().natural()) return;

        float newSpeed = hardnessConfig.getAdjustedHardness(originalHardness, pos.getY());
        cir.setReturnValue(newSpeed);
    }
}
