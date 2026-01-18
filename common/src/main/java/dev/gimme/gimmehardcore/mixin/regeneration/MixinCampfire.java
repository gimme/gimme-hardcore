package dev.gimme.gimmehardcore.mixin.regeneration;

import dev.gimme.gimmehardcore.domain.regeneration.CampfirePassiveRegenBehavior;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CampfireBlockEntity.class)
public class MixinCampfire {

    @Inject(method = "cookTick", at = @At("TAIL"))
    private static void addCampfirePassiveRegen(ServerLevel level, BlockPos pos, BlockState state, CampfireBlockEntity campfire, RecipeManager.CachedCheck<SingleRecipeInput, CampfireCookingRecipe> check, CallbackInfo ci) {
        CampfirePassiveRegenBehavior.tickCampfireRegen(level, campfire);
    }
}
