package studio.abos.mc.strangeadventures;

import net.blay09.mods.balm.Balm;
import net.blay09.mods.balm.core.BalmRegistrars;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import studio.abos.mc.strangeadventures.block.ModBlocks;
import studio.abos.mc.strangeadventures.entity.ModEntities;
import studio.abos.mc.strangeadventures.fluid.ModFluids;
import studio.abos.mc.strangeadventures.item.ModItems;

public class StrangeAdventures {

    public static final Logger logger = LoggerFactory.getLogger(StrangeAdventures.class);

    public static final String MOD_ID = "strangeadventures";

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    public static StrangeAdventuresConfig config() {
        return Balm.config().getActiveConfig(StrangeAdventuresConfig.class);
    }

    public static void initialize(BalmRegistrars registrars) {
        Balm.config().registerConfig(StrangeAdventuresConfig.class);

        registrars.registrar(Registries.FLUID, ModFluids::initialize);
        registrars.blocks(ModBlocks::initialize);
        registrars.items(ModItems::initialize);
        registrars.entityTypes(ModEntities::initialize);
        registrars.creativeModeTabs(ModItems::initialize);
    }

}
