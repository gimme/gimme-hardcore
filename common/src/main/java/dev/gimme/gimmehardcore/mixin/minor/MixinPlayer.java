package dev.gimme.gimmehardcore.mixin.minor;

import dev.gimme.gimmehardcore.domain.config.GeneralConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class MixinPlayer {

    /**
     * Prevents lava bucket usage if disabled in config.
     */
    @Inject(method = "mayUseItemAt", at = @At(value = "HEAD"), cancellable = true)
    private void onMayUseItemAt(BlockPos pos, Direction facing, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (GeneralConfig.INSTANCE.disableLavaBucket()) {
            if (stack.getItem() == Items.LAVA_BUCKET) {
                cir.setReturnValue(false);
            }
        }
    }
}
