package studio.abos.mc.strangeadventures.fabric.compat;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import org.jspecify.annotations.Nullable;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.StreamServerDataProvider;
import snownee.jade.api.WailaPlugin;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.fluid.JadeFluidObject;
import snownee.jade.api.ui.JadeUI;
import snownee.jade.api.view.HideThingsExtensionProvider;
import studio.abos.mc.strangeadventures.FluidUtil;
import studio.abos.mc.strangeadventures.block.EssenceCauldronBlock;
import studio.abos.mc.strangeadventures.block.SapSipperBlock;
import studio.abos.mc.strangeadventures.blockentity.EssenceCauldronBlockEntity;
import studio.abos.mc.strangeadventures.blockentity.SapSipperBlockEntity;
import studio.abos.mc.strangeadventures.compat.ModJadePlugin;

import java.util.List;
import java.util.Optional;

@WailaPlugin
public class ModJadePluginFabric extends ModJadePlugin implements IWailaPlugin {

    @Override
    public void register(final IWailaCommonRegistration registration) {
        registration.registerFluidStorage(HideThingsExtensionProvider.instance(), SapSipperBlock.class);
        registration.registerBlockDataProvider(SapSipperDataProviderFabric.INSTANCE, SapSipperBlockEntity.class);
        registration.registerFluidStorage(HideThingsExtensionProvider.instance(), EssenceCauldronBlock.class);
    }

    @Override
    public void registerClient(final IWailaClientRegistration registration) {
        registration.registerBlockComponent(SapSipperComponentProviderFabric.INSTANCE, SapSipperBlock.class);
        registration.registerBlockComponent(EssenceCauldronComponentProviderFabric.INSTANCE, EssenceCauldronBlock.class);
    }

    public static class SapSipperDataProviderFabric extends SapSipperDataProvider implements StreamServerDataProvider<BlockAccessor, SapSipperDataProvider.SapSipperData> {

        public static final SapSipperDataProviderFabric INSTANCE = new SapSipperDataProviderFabric();

        @Override
        public SapSipperDataProvider.@Nullable SapSipperData streamData(final BlockAccessor blockAccessor) {
            if (blockAccessor.getBlockEntity() instanceof final SapSipperBlockEntity sapSipper) {
                final var tank = sapSipper.getFluidTank();
                return new SapSipperData(tank.getFluid(0), tank.getAmount(0));
            }
            return null;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, SapSipperDataProvider.SapSipperData> streamCodec() {
            return SapSipperData.STREAM_CODEC;
        }

    }

    public static class SapSipperComponentProviderFabric extends SapSipperComponentProvider implements IBlockComponentProvider {

        public static final SapSipperComponentProviderFabric INSTANCE = new SapSipperComponentProviderFabric();

        @Override
        public void appendTooltip(final ITooltip tooltip, final BlockAccessor blockAccessor, final IPluginConfig pluginConfig) {
            if (blockAccessor.getBlockEntity() instanceof SapSipperBlockEntity sapSipper) {
                final var tank = sapSipper.getFluidTank();
                Optional<SapSipperDataProvider.SapSipperData> dataOpt = SapSipperDataProviderFabric.INSTANCE.decodeFromData(blockAccessor);
                dataOpt.ifPresent(data -> {
                    final Fluid fluid = data.fluid();
                    if (!fluid.isSame(Fluids.EMPTY)) {
                        final var sapIcon = JadeUI.fluid(JadeFluidObject.of(fluid));
                        tooltip.add(sapIcon);
                        tooltip.add(FluidUtil.name(fluid));
                        tooltip.add(Component.literal(FluidUtil.MB_FILLED_TEMPLATE.formatted(data.amount(), tank.getCapacity(0))));
                    }
                });
            }
        }

    }

    public static class EssenceCauldronComponentProviderFabric extends EssenceCauldronComponentProvider implements IBlockComponentProvider {

        public static final EssenceCauldronComponentProviderFabric INSTANCE = new EssenceCauldronComponentProviderFabric();

        @Override
        public void appendTooltip(final ITooltip tooltip, final BlockAccessor blockAccessor, final IPluginConfig pluginConfig) {
            if (blockAccessor.getBlockEntity() instanceof EssenceCauldronBlockEntity essenceCauldron) {
                final var tank = essenceCauldron.getFluidTank();
                if (tank.getAmountOfBottles() > 0) {
                    tooltip.add(Component.literal(""));
                    for (int i = 0; i < tank.getSlotCount(); i++) {
                        final Fluid fluid = tank.getFluid(i);
                        if (!fluid.isSame(Fluids.EMPTY)) {
                            tooltip.append(JadeUI.fluid(JadeFluidObject.of(fluid)));
                        }
                    }
                }
                if (essenceCauldron.getItemCount() > 0) {
                    tooltip.add(Component.literal(""));
                    final List<ItemStack> items = essenceCauldron.getItems();
                    for (final ItemStack stack : items) {
                        if (!stack.isEmpty()) {
                            tooltip.append(JadeUI.item(stack));
                        }
                    }
                }
            }
        }

    }

}
