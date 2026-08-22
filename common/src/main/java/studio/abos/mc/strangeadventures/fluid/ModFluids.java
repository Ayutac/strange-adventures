package studio.abos.mc.strangeadventures.fluid;

import lombok.NonNull;
import net.blay09.mods.balm.core.BalmRegistrar;
import net.minecraft.core.Holder;
import net.minecraft.world.level.material.Fluid;

public final class ModFluids {

    public static Holder<@NonNull Fluid> ACACIA_SAP_STILL;
    public static Holder<@NonNull Fluid> ACACIA_SAP_FLOWING;
    public static Holder<@NonNull Fluid> BIRCH_SAP_STILL;
    public static Holder<@NonNull Fluid> BIRCH_SAP_FLOWING;
    public static Holder<@NonNull Fluid> CHERRY_SAP_STILL;
    public static Holder<@NonNull Fluid> CHERRY_SAP_FLOWING;
    public static Holder<@NonNull Fluid> JUNGLE_SAP_STILL;
    public static Holder<@NonNull Fluid> JUNGLE_SAP_FLOWING;
    public static Holder<@NonNull Fluid> MANGROVE_SAP_STILL;
    public static Holder<@NonNull Fluid> MANGROVE_SAP_FLOWING;
    public static Holder<@NonNull Fluid> OAK_SAP_STILL;
    public static Holder<@NonNull Fluid> OAK_SAP_FLOWING;
    public static Holder<@NonNull Fluid> SPRUCE_SAP_STILL;
    public static Holder<@NonNull Fluid> SPRUCE_SAP_FLOWING;

    public static void initialize(BalmRegistrar.Scoped<@NonNull Fluid> fluids) {
        ACACIA_SAP_STILL = fluids.register(ModFluidIds.ACACIA_SAP_STILL.identifier().getPath(), _ -> new AcaciaSapFluid.Source());
        ACACIA_SAP_FLOWING = fluids.register(ModFluidIds.ACACIA_SAP_FLOWING.identifier().getPath(), _ -> new AcaciaSapFluid.Flowing());
        BIRCH_SAP_STILL = fluids.register(ModFluidIds.BIRCH_SAP_STILL.identifier().getPath(), _ -> new BirchSapFluid.Source());
        BIRCH_SAP_FLOWING = fluids.register(ModFluidIds.BIRCH_SAP_FLOWING.identifier().getPath(), _ -> new BirchSapFluid.Flowing());
        CHERRY_SAP_STILL = fluids.register(ModFluidIds.CHERRY_SAP_STILL.identifier().getPath(), _ -> new CherrySapFluid.Source());
        CHERRY_SAP_FLOWING = fluids.register(ModFluidIds.CHERRY_SAP_FLOWING.identifier().getPath(), _ -> new CherrySapFluid.Flowing());
        JUNGLE_SAP_STILL = fluids.register(ModFluidIds.JUNGLE_SAP_STILL.identifier().getPath(), _ -> new JungleSapFluid.Source());
        JUNGLE_SAP_FLOWING = fluids.register(ModFluidIds.JUNGLE_SAP_FLOWING.identifier().getPath(), _ -> new JungleSapFluid.Flowing());
        MANGROVE_SAP_STILL = fluids.register(ModFluidIds.MANGROVE_SAP_STILL.identifier().getPath(), _ -> new MangroveSapFluid.Source());
        MANGROVE_SAP_FLOWING = fluids.register(ModFluidIds.MANGROVE_SAP_FLOWING.identifier().getPath(), _ -> new MangroveSapFluid.Flowing());
        OAK_SAP_STILL = fluids.register(ModFluidIds.OAK_SAP_STILL.identifier().getPath(), _ -> new OakSapFluid.Source());
        OAK_SAP_FLOWING = fluids.register(ModFluidIds.OAK_SAP_FLOWING.identifier().getPath(), _ -> new OakSapFluid.Flowing());
        SPRUCE_SAP_STILL = fluids.register(ModFluidIds.SPRUCE_SAP_STILL.identifier().getPath(), _ -> new SpruceSapFluid.Source());
        SPRUCE_SAP_FLOWING = fluids.register(ModFluidIds.SPRUCE_SAP_FLOWING.identifier().getPath(), _ -> new SpruceSapFluid.Flowing());
    }
}
