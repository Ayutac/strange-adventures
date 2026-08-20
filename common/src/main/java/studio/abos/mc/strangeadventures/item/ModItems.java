package studio.abos.mc.strangeadventures.item;

import net.blay09.mods.balm.world.item.BalmCreativeModeTabRegistrar;
import net.blay09.mods.balm.world.item.BalmItemRegistrar;
import net.blay09.mods.balm.world.item.DeferredItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import studio.abos.mc.strangeadventures.StrangeAdventures;
import studio.abos.mc.strangeadventures.block.ModBlocks;

public class ModItems {
    public static DeferredItem GREEN_FLOWER;

    public static void initialize(BalmItemRegistrar items) {
        GREEN_FLOWER = items.register("green_flower", props -> new BlockItem(ModBlocks.GREEN_FLOWER.asBlock(), props)).asDeferredItem();
    }

    public static void initialize(BalmCreativeModeTabRegistrar creativeModeTabs) {
        creativeModeTabs.register(StrangeAdventures.MOD_ID, builder ->
                builder.title(Component.translatable(StrangeAdventures.id(StrangeAdventures.MOD_ID).toLanguageKey("itemGroup")))
                        .icon(() -> ModItems.GREEN_FLOWER.createStack())
                        .displayItems((displayParameters, output) -> {
                            output.accept(ModItems.GREEN_FLOWER);
                        })
        );
    }

}
