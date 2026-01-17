package dev.gimme.gimmehardcore.mixin.tool;

import dev.gimme.gimmehardcore.domain.config.GeneralConfig;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

@Mixin(Item.class)
public abstract class MixinItem {

    @Unique
    private static final Set<Block> STONE_BLOCKS = Set.of(
            Blocks.STONE,
            Blocks.DEEPSLATE,
            Blocks.COBBLESTONE,
            Blocks.COBBLED_DEEPSLATE,
            Blocks.BLACKSTONE,
            Blocks.ANDESITE,
            Blocks.CALCITE,
            Blocks.DIORITE,
            Blocks.GRANITE,
            Blocks.TUFF
    );

    @Unique
    private static final Set<Block> IRON_BLOCKS = Set.of(
            Blocks.IRON_ORE,
            Blocks.DEEPSLATE_IRON_ORE,
            Blocks.IRON_BLOCK
    );

    @Unique
    private static final Set<Block> DIAMOND_BLOCKS = Set.of(
            Blocks.DIAMOND_ORE,
            Blocks.DEEPSLATE_DIAMOND_ORE,
            Blocks.DIAMOND_BLOCK
    );

    /**
     * Adds pickaxe tier requirements for mining certain blocks.
     */
    @Inject(method = "isCorrectToolForDrops", at = @At("HEAD"), cancellable = true)
    private void onIsCorrectForDrops(ItemStack stack, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (!GeneralConfig.INSTANCE.pickaxeRequirements()) return;
        var instance = (Item) (Object) this;

        if (instance == Items.WOODEN_PICKAXE) {
            if (STONE_BLOCKS.contains(state.getBlock())) {
                cir.setReturnValue(false);
            }
        } else if (instance == Items.STONE_PICKAXE || instance == Items.COPPER_PICKAXE) {
            if (IRON_BLOCKS.contains(state.getBlock())) {
                cir.setReturnValue(false);
            }
        } else if (instance == Items.IRON_PICKAXE) {
            if (DIAMOND_BLOCKS.contains(state.getBlock())) {
                cir.setReturnValue(false);
            }
        }
    }
}
