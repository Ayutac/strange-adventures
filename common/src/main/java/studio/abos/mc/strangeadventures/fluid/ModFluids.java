package studio.abos.mc.strangeadventures.fluid;

import net.blay09.mods.balm.core.BalmRegistrar;
import net.minecraft.core.Holder;
import net.minecraft.world.level.material.Fluid;

public final class ModFluids {

    public static int BOTTLE_AMOUNT = 333; // in mB
    public static int BUCKET_AMOUNT = 1000; // in mB

    public static Holder<Fluid> ACACIA_SAP_STILL;
    public static Holder<Fluid> ACACIA_SAP_FLOWING;
    public static Holder<Fluid> BIRCH_SAP_STILL;
    public static Holder<Fluid> BIRCH_SAP_FLOWING;
    public static Holder<Fluid> CACTUS_SAP_STILL;
    public static Holder<Fluid> CACTUS_SAP_FLOWING;
    public static Holder<Fluid> CHERRY_SAP_STILL;
    public static Holder<Fluid> CHERRY_SAP_FLOWING;
    public static Holder<Fluid> CHORUS_SAP_STILL;
    public static Holder<Fluid> CHORUS_SAP_FLOWING;
    public static Holder<Fluid> CRIMSON_SAP_STILL;
    public static Holder<Fluid> CRIMSON_SAP_FLOWING;
    public static Holder<Fluid> JUNGLE_SAP_STILL;
    public static Holder<Fluid> JUNGLE_SAP_FLOWING;
    public static Holder<Fluid> MANGROVE_SAP_STILL;
    public static Holder<Fluid> MANGROVE_SAP_FLOWING;
    public static Holder<Fluid> OAK_SAP_STILL;
    public static Holder<Fluid> OAK_SAP_FLOWING;
    public static Holder<Fluid> SPRUCE_SAP_STILL;
    public static Holder<Fluid> SPRUCE_SAP_FLOWING;
    public static Holder<Fluid> WARPED_SAP_STILL;
    public static Holder<Fluid> WARPED_SAP_FLOWING;

    public static void initialize(final BalmRegistrar.Scoped<Fluid> fluids) {
        ACACIA_SAP_STILL = fluids.register(ModFluidIds.ACACIA_SAP_STILL.identifier().getPath(), _ -> new AcaciaSapFluid.Source());
        ACACIA_SAP_FLOWING = fluids.register(ModFluidIds.ACACIA_SAP_FLOWING.identifier().getPath(), _ -> new AcaciaSapFluid.Flowing());
        BIRCH_SAP_STILL = fluids.register(ModFluidIds.BIRCH_SAP_STILL.identifier().getPath(), _ -> new BirchSapFluid.Source());
        BIRCH_SAP_FLOWING = fluids.register(ModFluidIds.BIRCH_SAP_FLOWING.identifier().getPath(), _ -> new BirchSapFluid.Flowing());
        CACTUS_SAP_STILL = fluids.register(ModFluidIds.CACTUS_SAP_STILL.identifier().getPath(), _ -> new CactusSapFluid.Source());
        CACTUS_SAP_FLOWING = fluids.register(ModFluidIds.CACTUS_SAP_FLOWING.identifier().getPath(), _ -> new CactusSapFluid.Flowing());
        CHERRY_SAP_STILL = fluids.register(ModFluidIds.CHERRY_SAP_STILL.identifier().getPath(), _ -> new CherrySapFluid.Source());
        CHERRY_SAP_FLOWING = fluids.register(ModFluidIds.CHERRY_SAP_FLOWING.identifier().getPath(), _ -> new CherrySapFluid.Flowing());
        CHORUS_SAP_STILL = fluids.register(ModFluidIds.CHORUS_SAP_STILL.identifier().getPath(), _ -> new ChorusSapFluid.Source());
        CHORUS_SAP_FLOWING = fluids.register(ModFluidIds.CHORUS_SAP_FLOWING.identifier().getPath(), _ -> new ChorusSapFluid.Flowing());
        CRIMSON_SAP_STILL = fluids.register(ModFluidIds.CRIMSON_SAP_STILL.identifier().getPath(), _ -> new CrimsonSapFluid.Source());
        CRIMSON_SAP_FLOWING = fluids.register(ModFluidIds.CRIMSON_SAP_FLOWING.identifier().getPath(), _ -> new CrimsonSapFluid.Flowing());
        JUNGLE_SAP_STILL = fluids.register(ModFluidIds.JUNGLE_SAP_STILL.identifier().getPath(), _ -> new JungleSapFluid.Source());
        JUNGLE_SAP_FLOWING = fluids.register(ModFluidIds.JUNGLE_SAP_FLOWING.identifier().getPath(), _ -> new JungleSapFluid.Flowing());
        MANGROVE_SAP_STILL = fluids.register(ModFluidIds.MANGROVE_SAP_STILL.identifier().getPath(), _ -> new MangroveSapFluid.Source());
        MANGROVE_SAP_FLOWING = fluids.register(ModFluidIds.MANGROVE_SAP_FLOWING.identifier().getPath(), _ -> new MangroveSapFluid.Flowing());
        OAK_SAP_STILL = fluids.register(ModFluidIds.OAK_SAP_STILL.identifier().getPath(), _ -> new OakSapFluid.Source());
        OAK_SAP_FLOWING = fluids.register(ModFluidIds.OAK_SAP_FLOWING.identifier().getPath(), _ -> new OakSapFluid.Flowing());
        SPRUCE_SAP_STILL = fluids.register(ModFluidIds.SPRUCE_SAP_STILL.identifier().getPath(), _ -> new SpruceSapFluid.Source());
        SPRUCE_SAP_FLOWING = fluids.register(ModFluidIds.SPRUCE_SAP_FLOWING.identifier().getPath(), _ -> new SpruceSapFluid.Flowing());
        WARPED_SAP_STILL = fluids.register(ModFluidIds.WARPED_SAP_STILL.identifier().getPath(), _ -> new WarpedSapFluid.Source());
        WARPED_SAP_FLOWING = fluids.register(ModFluidIds.WARPED_SAP_FLOWING.identifier().getPath(), _ -> new WarpedSapFluid.Flowing());
    }

}
