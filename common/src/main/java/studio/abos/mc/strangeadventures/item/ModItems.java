package studio.abos.mc.strangeadventures.item;

import net.blay09.mods.balm.world.item.BalmCreativeModeTabRegistrar;
import net.blay09.mods.balm.world.item.BalmItemRegistrar;
import net.blay09.mods.balm.world.item.DeferredItem;
import net.minecraft.network.chat.Component;
import studio.abos.mc.strangeadventures.StrangeAdventures;
import studio.abos.mc.strangeadventures.block.ModBlocks;

public class ModItems {

    public static DeferredItem GREEN_SAFEGUARD;

    public static void initialize(final BalmItemRegistrar items) {
        GREEN_SAFEGUARD = items.register("green_safeguard", GreenSafeguardItem::new).asDeferredItem();
    }

    public static void initialize(final BalmCreativeModeTabRegistrar creativeModeTabs) {
        creativeModeTabs.register(StrangeAdventures.MOD_ID, builder ->
                builder.title(Component.translatable(StrangeAdventures.id(StrangeAdventures.MOD_ID).toLanguageKey("itemGroup")))
                        .icon(() -> ModBlocks.GREEN_FLOWER.createStack())
                        .displayItems((displayParameters, output) -> {
                            output.accept(ModBlocks.GREEN_FLOWER);
                            output.accept(GREEN_SAFEGUARD);
                        })
        );
    }

}
