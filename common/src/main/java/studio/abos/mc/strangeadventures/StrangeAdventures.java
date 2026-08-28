package studio.abos.mc.strangeadventures;

import net.blay09.mods.balm.Balm;
import net.blay09.mods.balm.core.BalmRegistrars;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import studio.abos.mc.strangeadventures.api.StrangeAdventuresApi;
import studio.abos.mc.strangeadventures.block.ModBlocks;
import studio.abos.mc.strangeadventures.blockentity.ModBlockEntities;
import studio.abos.mc.strangeadventures.entity.ModEntityTypes;
import studio.abos.mc.strangeadventures.fluid.ModFluids;
import studio.abos.mc.strangeadventures.item.ModItems;
import studio.abos.mc.strangeadventures.recipe.ModRecipeTypes;
import studio.abos.mc.strangeadventures.targetingmode.ModTargetingModes;
import studio.abos.mc.strangeadventures.targetingspace.ModTargetingSpaces;

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

        registrars.registrar().createCustomRegistry(StrangeAdventuresApi.TARGETING_MODE_REGISTRY_KEY);
        registrars.registrar().createCustomRegistry(StrangeAdventuresApi.TARGETING_SPACE_REGISTRY_KEY);

        registrars.registrar(Registries.FLUID, ModFluids::initialize);
        registrars.blocks(ModBlocks::initialize);
        registrars.items(ModItems::initialize);
        registrars.blockEntityTypes(ModBlockEntities::initialize);
        registrars.entityTypes(ModEntityTypes::initialize);
        registrars.recipeTypes(ModRecipeTypes::initialize);
        registrars.creativeModeTabs(ModItems::initialize);
        registrars.registrar(StrangeAdventuresApi.TARGETING_MODE_REGISTRY_KEY, ModTargetingModes::initialize);
        registrars.registrar(StrangeAdventuresApi.TARGETING_SPACE_REGISTRY_KEY, ModTargetingSpaces::initialize);
    }

}
