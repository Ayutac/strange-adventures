package studio.abos.mc.strangeadventures.fluid;

import lombok.NonNull;
import net.blay09.mods.balm.core.BalmRegistrar;
import net.minecraft.core.Holder;
import net.minecraft.world.level.material.Fluid;

public final class ModFluids {

    public static Holder<@NonNull Fluid> BIRCH_SAP_STILL;
    public static Holder<@NonNull Fluid> BIRCH_SAP_FLOWING;
    public static Holder<@NonNull Fluid> OAK_SAP_STILL;
    public static Holder<@NonNull Fluid> OAK_SAP_FLOWING;

    public static void initialize(BalmRegistrar.Scoped<@NonNull Fluid> fluids) {
        BIRCH_SAP_STILL = fluids.register(ModFluidIds.BIRCH_SAP_STILL.identifier().getPath(), _ -> new BirchSapFluid.Source());
        BIRCH_SAP_FLOWING = fluids.register(ModFluidIds.BIRCH_SAP_FLOWING.identifier().getPath(), _ -> new BirchSapFluid.Flowing());
        OAK_SAP_STILL = fluids.register(ModFluidIds.OAK_SAP_STILL.identifier().getPath(), _ -> new OakSapFluid.Source());
        OAK_SAP_FLOWING = fluids.register(ModFluidIds.OAK_SAP_FLOWING.identifier().getPath(), _ -> new OakSapFluid.Flowing());
    }
}
