package dev.gimme.gimmehardcore.mixin.minor;

import dev.gimme.gimmehardcore.domain.config.GeneralConfig;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class MixinLivingEntity {

    /**
     * Sets max damage Ender Dragon can take from explosions.
     */
    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    private void onHurt(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!((LivingEntity) (Object) this instanceof EnderDragon)) return;
        float maxExplosionDamage = GeneralConfig.INSTANCE.getEnderDragonMaxExplosionDamage();
        if (source.is(DamageTypeTags.IS_EXPLOSION) && amount > maxExplosionDamage) {
            cir.setReturnValue(((LivingEntity) (Object) this).hurt(source, maxExplosionDamage));
        }
    }
}
