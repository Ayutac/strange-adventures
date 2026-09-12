package studio.abos.mc.strangeadventures.neoforge.mixin;

import net.neoforged.neoforge.common.extensions.IFluidExtension;
import net.neoforged.neoforge.fluids.FluidType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import studio.abos.mc.strangeadventures.fluid.AbstractSapFluid;

@Mixin(AbstractSapFluid.class)
public abstract class AbstractSapFluidMixin implements IFluidExtension {

    @Unique
    private static final FluidType SAP_FLUID_TYPE = new FluidType(FluidType.Properties.create());

    @Override
    public FluidType getFluidType() {
        return SAP_FLUID_TYPE;
    }

}
