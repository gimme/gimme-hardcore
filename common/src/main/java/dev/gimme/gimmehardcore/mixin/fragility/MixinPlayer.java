package dev.gimme.gimmehardcore.mixin.fragility;

import dev.gimme.gimmehardcore.domain.config.Config;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Player.class)
public class MixinPlayer {

    /**
     * Modifies shield damage and adds a chance to break the shield completely based on config settings.
     */
    @Redirect(method = "hurtCurrentlyUsedShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;hurtAndBreak(ILnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;)V"))
    private void onHurtAndBreakShield(ItemStack shield, int shieldDamage, LivingEntity player, EquipmentSlot slot, float playerDamage) {
        var fragilityConfig = Config.INSTANCE.getFragilityConfig();
        int newAmount = 1 + Mth.floor(playerDamage * fragilityConfig.getShieldDamageMultiplier());

        // Risk to break completely
        float breakChance = fragilityConfig.getShieldBreakChance();
        if (player.level().getRandom().nextFloat() < breakChance) {
            newAmount = shield.getMaxDamage();
        }

        shield.hurtAndBreak(newAmount, player, slot);
    }
}
