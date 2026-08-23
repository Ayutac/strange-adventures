package studio.abos.mc.strangeadventures.item;

import net.blay09.mods.balm.world.item.BalmCreativeModeTabRegistrar;
import net.blay09.mods.balm.world.item.BalmItemRegistrar;
import net.blay09.mods.balm.world.item.DeferredItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import studio.abos.mc.strangeadventures.StrangeAdventures;
import studio.abos.mc.strangeadventures.block.ModBlocks;
import studio.abos.mc.strangeadventures.fluid.ModFluids;

public final class ModItems {

    public static DeferredItem GREEN_SAFEGUARD;
    public static DeferredItem LIVING_ROD;

    public static DeferredItem ACACIA_SAP_BOTTLE;
    public static DeferredItem ACACIA_SAP_BUCKET;
    public static DeferredItem BIRCH_SAP_BOTTLE;
    public static DeferredItem BIRCH_SAP_BUCKET;
    public static DeferredItem CACTUS_SAP_BOTTLE;
    public static DeferredItem CACTUS_SAP_BUCKET;
    public static DeferredItem CHERRY_SAP_BOTTLE;
    public static DeferredItem CHERRY_SAP_BUCKET;
    public static DeferredItem CHORUS_SAP_BOTTLE;
    public static DeferredItem CHORUS_SAP_BUCKET;
    public static DeferredItem CRIMSON_SAP_BOTTLE;
    public static DeferredItem CRIMSON_SAP_BUCKET;
    public static DeferredItem JUNGLE_SAP_BOTTLE;
    public static DeferredItem JUNGLE_SAP_BUCKET;
    public static DeferredItem LIVING_SAP_BOTTLE;
    public static DeferredItem LIVING_SAP_BUCKET;
    public static DeferredItem MANGROVE_SAP_BOTTLE;
    public static DeferredItem MANGROVE_SAP_BUCKET;
    public static DeferredItem OAK_SAP_BOTTLE;
    public static DeferredItem OAK_SAP_BUCKET;
    public static DeferredItem SPRUCE_SAP_BOTTLE;
    public static DeferredItem SPRUCE_SAP_BUCKET;
    public static DeferredItem WARPED_SAP_BOTTLE;
    public static DeferredItem WARPED_SAP_BUCKET;

    public static void initialize(final BalmItemRegistrar items) {
        GREEN_SAFEGUARD = items.register("green_safeguard", GreenSafeguardItem::new).asDeferredItem();
        LIVING_ROD = items.register("living_rod", props -> new Item(props.fireResistant())).asDeferredItem();

        ACACIA_SAP_BUCKET = items.register("acacia_sap_bucket", props -> new BucketItem(ModFluids.ACACIA_SAP_STILL.value(), sapBucket(props))).asDeferredItem();
        ACACIA_SAP_BOTTLE = items.register("acacia_sap_bottle", props -> new Item(sapBottle(props))).asDeferredItem();
        BIRCH_SAP_BUCKET = items.register("birch_sap_bucket", props -> new BucketItem(ModFluids.BIRCH_SAP_STILL.value(), sapBucket(props))).asDeferredItem();
        BIRCH_SAP_BOTTLE = items.register("birch_sap_bottle", props -> new Item(sapBottle(props))).asDeferredItem();
        CACTUS_SAP_BUCKET = items.register("cactus_sap_bucket", props -> new BucketItem(ModFluids.CACTUS_SAP_STILL.value(), sapBucket(props))).asDeferredItem();
        CACTUS_SAP_BOTTLE = items.register("cactus_sap_bottle", props -> new Item(sapBottle(props))).asDeferredItem();
        CHERRY_SAP_BUCKET = items.register("cherry_sap_bucket", props -> new BucketItem(ModFluids.CHERRY_SAP_STILL.value(), sapBucket(props))).asDeferredItem();
        CHERRY_SAP_BOTTLE = items.register("cherry_sap_bottle", props -> new Item(sapBottle(props))).asDeferredItem();
        CHORUS_SAP_BUCKET = items.register("chorus_sap_bucket", props -> new BucketItem(ModFluids.CHORUS_SAP_STILL.value(), sapBucket(props))).asDeferredItem();
        CHORUS_SAP_BOTTLE = items.register("chorus_sap_bottle", props -> new Item(sapBottle(props))).asDeferredItem();
        CRIMSON_SAP_BUCKET = items.register("crimson_sap_bucket", props -> new BucketItem(ModFluids.CRIMSON_SAP_STILL.value(), sapBucket(props))).asDeferredItem();
        CRIMSON_SAP_BOTTLE = items.register("crimson_sap_bottle", props -> new Item(sapBottle(props))).asDeferredItem();
        JUNGLE_SAP_BUCKET = items.register("jungle_sap_bucket", props -> new BucketItem(ModFluids.JUNGLE_SAP_STILL.value(), sapBucket(props))).asDeferredItem();
        JUNGLE_SAP_BOTTLE = items.register("jungle_sap_bottle", props -> new Item(sapBottle(props))).asDeferredItem();
        LIVING_SAP_BUCKET = items.register("living_sap_bucket", props -> new LivingSapBucketItem(sapBucket(props))).asDeferredItem();
        LIVING_SAP_BOTTLE = items.register("living_sap_bottle", props -> new LivingSapBottleItem(sapBottle(props))).asDeferredItem();
        MANGROVE_SAP_BUCKET = items.register("mangrove_sap_bucket", props -> new BucketItem(ModFluids.MANGROVE_SAP_STILL.value(), sapBucket(props))).asDeferredItem();
        MANGROVE_SAP_BOTTLE = items.register("mangrove_sap_bottle", props -> new Item(sapBottle(props))).asDeferredItem();
        OAK_SAP_BUCKET = items.register("oak_sap_bucket", props -> new BucketItem(ModFluids.OAK_SAP_STILL.value(), sapBucket(props))).asDeferredItem();
        OAK_SAP_BOTTLE = items.register("oak_sap_bottle", props -> new Item(sapBottle(props))).asDeferredItem();
        SPRUCE_SAP_BUCKET = items.register("spruce_sap_bucket", props -> new BucketItem(ModFluids.SPRUCE_SAP_STILL.value(), sapBucket(props))).asDeferredItem();
        SPRUCE_SAP_BOTTLE = items.register("spruce_sap_bottle", props -> new Item(sapBottle(props))).asDeferredItem();
        WARPED_SAP_BUCKET = items.register("warped_sap_bucket", props -> new BucketItem(ModFluids.WARPED_SAP_STILL.value(), sapBucket(props))).asDeferredItem();
        WARPED_SAP_BOTTLE = items.register("warped_sap_bottle", props -> new Item(sapBottle(props))).asDeferredItem();
    }

    public static void initialize(final BalmCreativeModeTabRegistrar creativeModeTabs) {
        creativeModeTabs.register(StrangeAdventures.MOD_ID, builder ->
                builder.title(Component.translatable(StrangeAdventures.id(StrangeAdventures.MOD_ID).toLanguageKey("itemGroup")))
                        .icon(() -> ModBlocks.GREEN_FLOWER.createStack())
                        .displayItems((_, output) -> {
                            output.accept(ModBlocks.GREEN_FLOWER);
                            output.accept(GREEN_SAFEGUARD);
                            output.accept(ModBlocks.GREEN_FARMLAND);
                            output.accept(ModBlocks.GREEN_CACTUS);
                            output.accept(ModBlocks.SLEEPING_WOOD);
                            output.accept(ModBlocks.LIVING_WOOD);
                            output.accept(LIVING_ROD);

                            output.accept(ModBlocks.SAP_SIPPER);

                            output.accept(OAK_SAP_BOTTLE);
                            output.accept(SPRUCE_SAP_BOTTLE);
                            output.accept(BIRCH_SAP_BOTTLE);
                            output.accept(JUNGLE_SAP_BOTTLE);
                            output.accept(ACACIA_SAP_BOTTLE);
                            output.accept(MANGROVE_SAP_BOTTLE);
                            output.accept(CHERRY_SAP_BOTTLE);
                            output.accept(CACTUS_SAP_BOTTLE);
                            output.accept(CRIMSON_SAP_BOTTLE);
                            output.accept(WARPED_SAP_BOTTLE);
                            output.accept(CHORUS_SAP_BOTTLE);
                            output.accept(LIVING_SAP_BOTTLE);

                            output.accept(OAK_SAP_BUCKET);
                            output.accept(SPRUCE_SAP_BUCKET);
                            output.accept(BIRCH_SAP_BUCKET);
                            output.accept(JUNGLE_SAP_BUCKET);
                            output.accept(ACACIA_SAP_BUCKET);
                            output.accept(MANGROVE_SAP_BUCKET);
                            output.accept(CHERRY_SAP_BUCKET);
                            output.accept(CACTUS_SAP_BUCKET);
                            output.accept(CRIMSON_SAP_BUCKET);
                            output.accept(WARPED_SAP_BUCKET);
                            output.accept(CHORUS_SAP_BUCKET);
                            output.accept(LIVING_SAP_BUCKET);
                        })
        );
    }

    public static Item.Properties sapBucket(Item.Properties properties) {
        return properties
                .craftRemainder(Items.BUCKET)
                .stacksTo(1);
    }

    public static Item.Properties sapBottle(Item.Properties properties) {
        return properties.craftRemainder(Items.GLASS_BOTTLE);
    }

}
