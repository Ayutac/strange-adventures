package studio.abos.mc.strangeadventures.neoforge.mixin;

import net.neoforged.neoforge.common.extensions.IFluidExtension;
import net.neoforged.neoforge.fluids.FluidType;
import org.spongepowered.asm.mixin.Mixin;
import studio.abos.mc.strangeadventures.fluid.AbstractSapFluid;
import studio.abos.mc.strangeadventures.neoforge.NeoForgeStrangeAdventures;

@Mixin(AbstractSapFluid.class)
public abstract class AbstractSapFluidMixin implements IFluidExtension {

    @Override
    public FluidType getFluidType() {
        return NeoForgeStrangeAdventures.SAP_FLUID_TYPE.value();
    }

}
