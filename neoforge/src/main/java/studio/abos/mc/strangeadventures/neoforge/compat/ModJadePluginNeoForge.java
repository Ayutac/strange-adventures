package studio.abos.mc.strangeadventures.neoforge.compat;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.fluid.JadeFluidObject;
import snownee.jade.api.ui.JadeUI;
import studio.abos.mc.strangeadventures.block.SapSipperBlock;
import studio.abos.mc.strangeadventures.blockentity.SapSipperBlockEntity;
import studio.abos.mc.strangeadventures.compat.ModJadePlugin;

@WailaPlugin
public class ModJadePluginNeoForge extends ModJadePlugin implements IWailaPlugin {

    @Override
    public void registerClient(final IWailaClientRegistration registration) {
        registration.registerBlockComponent(SapSipperComponentProviderNeoForge.INSTANCE, SapSipperBlock.class);
    }

    public static class SapSipperComponentProviderNeoForge extends SapSipperComponentProvider implements IBlockComponentProvider {

        public static final SapSipperComponentProviderNeoForge INSTANCE = new SapSipperComponentProviderNeoForge();

        @Override
        public void appendTooltip(final ITooltip tooltip, final BlockAccessor blockAccessor, final IPluginConfig pluginConfig) {
            if (blockAccessor.getBlockEntity() instanceof SapSipperBlockEntity sapSipper) {
                final Fluid fluid = sapSipper.getFluidTank().getFluid(0);
                if (!fluid.isSame(Fluids.EMPTY)) {
                    final var sapIcon = JadeUI.fluid(JadeFluidObject.of(fluid));
                    tooltip.add(sapIcon);
                    tooltip.append(Component.translatable("fluid." + BuiltInRegistries.FLUID.getKey(fluid)));
                    tooltip.append(Component.literal("%d/%dmB".formatted(sapSipper.getFluidTank().getAmount(0), sapSipper.getFluidTank().getCapacity(0))));
                }
            }
        }

    }

}
