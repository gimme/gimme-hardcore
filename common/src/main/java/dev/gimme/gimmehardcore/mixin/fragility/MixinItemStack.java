package dev.gimme.gimmehardcore.mixin.fragility;

import dev.gimme.gimmehardcore.domain.config.FragilityConfig;
import dev.gimme.gimmehardcore.domain.util.Constants;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public class MixinItemStack {

    /**
     * Modifies armor durability damage based on config settings.
     */
    @Inject(method = "hurtAndBreak(ILnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;)V", at = @At(value = "HEAD"), cancellable = true)
    private void makeArmorMoreFragile(int damageAmount, LivingEntity entity, EquipmentSlot slot, CallbackInfo ci) {
        if (slot.getType() != EquipmentSlot.Type.HUMANOID_ARMOR) return;
        if (!(entity.level() instanceof ServerLevel serverlevel)) return;

        ItemStack instance = (ItemStack) (Object) this;
        int newDamageAmount = (int) Math.max(1.0F, damageAmount * FragilityConfig.INSTANCE.getArmorDamageMultiplier());
        Constants.LOG.debug("Modifying armor damage from {} to {} on slot {}", damageAmount, newDamageAmount, slot);

        instance.hurtAndBreak(
                newDamageAmount,
                serverlevel,
                entity instanceof ServerPlayer serverplayer ? serverplayer : null,
                p_348383_ -> entity.onEquippedItemBroken(p_348383_, slot)
        );
        ci.cancel();
    }
}
