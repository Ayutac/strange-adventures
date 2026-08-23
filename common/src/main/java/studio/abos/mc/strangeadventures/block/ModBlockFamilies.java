package studio.abos.mc.strangeadventures.block;

import net.minecraft.data.BlockFamily;

public final class ModBlockFamilies {

    public static BlockFamily WEIR_PLANKS;

    public static void initialize() {
        WEIR_PLANKS = new BlockFamily.Builder(ModBlocks.WEIR_PLANKS.asBlock())
                .log(ModBlocks.WEIR_LOG.asBlock())
                .strippedLog(ModBlocks.STRIPPED_WEIR_LOG.asBlock())
                .button(ModBlocks.WEIR_BUTTON.asBlock())
                .fence(ModBlocks.WEIR_FENCE.asBlock())
                .fenceGate(ModBlocks.WEIR_FENCE_GATE.asBlock())
                .hangingSign(ModBlocks.WEIR_HANGING_SIGN.asBlock(), ModBlocks.WEIR_WALL_HANGING_SIGN.asBlock())
                .pressurePlate(ModBlocks.WEIR_PRESSURE_PLATE.asBlock())
                .sign(ModBlocks.WEIR_SIGN.asBlock(), ModBlocks.WEIR_WALL_SIGN.asBlock())
                .slab(ModBlocks.WEIR_SLAB.asBlock())
                .stairs(ModBlocks.WEIR_STAIRS.asBlock())
                .door(ModBlocks.WEIR_DOOR.asBlock())
                .trapdoor(ModBlocks.WEIR_TRAPDOOR.asBlock())
                .recipeGroupPrefix("wooden")
                .recipeUnlockedBy("has_planks")
                .getFamily();
    }

}
