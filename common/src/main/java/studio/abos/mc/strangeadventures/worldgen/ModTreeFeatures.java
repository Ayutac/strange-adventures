package studio.abos.mc.strangeadventures.worldgen;

import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import studio.abos.mc.strangeadventures.StrangeAdventures;

import java.util.Optional;

public final class ModTreeFeatures {

    public static final ResourceKey<ConfiguredFeature<?,?>> WEIR_CF_KEY = FeatureUtils.createKey(StrangeAdventures.MOD_ID + ".weir");

    public static final TreeGrower WEIR_GROWER = new TreeGrower(StrangeAdventures.MOD_ID + ".weir", Optional.empty(), Optional.of(WEIR_CF_KEY), Optional.empty());

}
