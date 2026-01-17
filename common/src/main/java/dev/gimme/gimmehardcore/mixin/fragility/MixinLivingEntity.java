package dev.gimme.gimmehardcore.mixin.fragility;

import dev.gimme.gimmehardcore.domain.config.FragilityConfig;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public class MixinLivingEntity {

    /**
     * Modifies armor damage based on config settings.
     */
    @Redirect(method = "doHurtEquipment", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;hurtAndBreak(ILnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;)V"))
    private void onHurtArmor(ItemStack instance, int damageAmount, LivingEntity entity, EquipmentSlot slot) {
        int newAmount = (int) Math.max(1.0F, damageAmount * FragilityConfig.INSTANCE.getArmorDamageMultiplier());
        instance.hurtAndBreak(newAmount, entity, slot);
    }
}
