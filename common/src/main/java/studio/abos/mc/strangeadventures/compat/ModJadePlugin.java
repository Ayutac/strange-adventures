package studio.abos.mc.strangeadventures.compat;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.material.Fluid;
import studio.abos.mc.strangeadventures.StrangeAdventures;
import studio.abos.mc.strangeadventures.block.ModBlocks;

public class ModJadePlugin {

    public static class SapSipperDataProvider {

        public record SapSipperData(Fluid fluid, int amount) {

            public static StreamCodec<RegistryFriendlyByteBuf, SapSipperData> STREAM_CODEC = StreamCodec.composite(
                    ByteBufCodecs.registry(BuiltInRegistries.FLUID.key()), SapSipperData::fluid,
                    ByteBufCodecs.VAR_INT, SapSipperData::amount,
                    SapSipperData::new
            );

        }

        public Identifier getUid() {
            return StrangeAdventures.id("sap_sipper_data");
        }

    }

    public static class SapSipperComponentProvider {

        public Identifier getUid() {
            return ModBlocks.SAP_SIPPER.asBlockItemId().block().identifier();
        }

    }

    public static class EssenceCauldronComponentProvider {

        public Identifier getUid() {
            return ModBlocks.ESSENCE_CAULDRON.asBlockItemId().block().identifier();
        }

    }

}
