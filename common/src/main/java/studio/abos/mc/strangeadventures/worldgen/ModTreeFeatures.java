package studio.abos.mc.strangeadventures.worldgen;

import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.Feature;
import studio.abos.mc.strangeadventures.StrangeAdventures;

public final class ModTreeFeatures {

    public static final ResourceKey<Feature> WEIR_KEY = FeatureUtils.createKey(StrangeAdventures.MOD_ID + ".weir");

    public static final TreeGrower WEIR_GROWER = new TreeGrower(StrangeAdventures.MOD_ID + ".weir", WeightedList.of(new Weighted[]{new Weighted(WEIR_KEY, 1)}), WeightedList.of(), WeightedList.of(), WEIR_KEY);

}
