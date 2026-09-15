package studio.abos.mc.strangeadventures;

import net.blay09.mods.balm.Balm;
import net.blay09.mods.balm.core.BalmRegistrars;
import net.blay09.mods.balm.platform.event.callback.LivingEntityCallback;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import studio.abos.mc.strangeadventures.api.StrangeAdventuresApi;
import studio.abos.mc.strangeadventures.api.BiomeTree;
import studio.abos.mc.strangeadventures.block.ModBlocks;
import studio.abos.mc.strangeadventures.blockentity.ModBlockEntities;
import studio.abos.mc.strangeadventures.command.ModCommands;
import studio.abos.mc.strangeadventures.data.ModDataAttachments;
import studio.abos.mc.strangeadventures.effect.ModEffects;
import studio.abos.mc.strangeadventures.entity.ModEntityTypes;
import studio.abos.mc.strangeadventures.fluid.ModFluids;
import studio.abos.mc.strangeadventures.item.ModItems;
import studio.abos.mc.strangeadventures.recipe.ModRecipeTypes;
import studio.abos.mc.strangeadventures.targetingmode.ModTargetingModes;
import studio.abos.mc.strangeadventures.targetingspace.ModTargetingSpaces;

import java.util.Objects;

public class StrangeAdventures {

    public static final Logger logger = LoggerFactory.getLogger(StrangeAdventures.class);

    public static final String MOD_ID = "strangeadventures";

    private static @Nullable ModDataAttachments dataAttachments;

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    public static StrangeAdventuresConfig config() {
        return Balm.config().getActiveConfig(StrangeAdventuresConfig.class);
    }

    public static ModDataAttachments dataAttachments() {
        return Objects.requireNonNull(dataAttachments);
    }

    public static void initialize(BalmRegistrars registrars) {
        Balm.config().registerConfig(StrangeAdventuresConfig.class);

        registrars.registrar().createCustomRegistry(StrangeAdventuresApi.TARGETING_MODE_REGISTRY_KEY);
        registrars.registrar().createCustomRegistry(StrangeAdventuresApi.TARGETING_SPACE_REGISTRY_KEY);
        registrars.registrar().createDynamicRegistry(StrangeAdventuresApi.BIOME_TREE_REGISTRY_KEY, BiomeTree.CODEC);

        registrars.registrar(Registries.FLUID, ModFluids::initialize);
        registrars.blocks(ModBlocks::initialize);
        registrars.items(ModItems::initialize);
        registrars.blockEntityTypes(ModBlockEntities::initialize);
        registrars.entityTypes(ModEntityTypes::initialize);
        registrars.registrar(Registries.MOB_EFFECT, ModEffects::initialize);
        registrars.recipeTypes(ModRecipeTypes::initialize);
        registrars.creativeModeTabs(ModItems::initialize);
        registrars.registrar(StrangeAdventuresApi.TARGETING_MODE_REGISTRY_KEY, ModTargetingModes::initialize);
        registrars.registrar(StrangeAdventuresApi.TARGETING_SPACE_REGISTRY_KEY, ModTargetingSpaces::initialize);

        registrars.dataAttachmentTypes(registrar -> dataAttachments = new ModDataAttachments(registrar));

        ModCommands.initialize(Balm.commands());

        LivingEntityCallback.Damage.Before.EVENT.register((entity, damageSource, damageAmount) -> {
            if (entity instanceof final ServerPlayer player && StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive(player)) {
                if (damageSource.is(DamageTypeTags.IS_FIRE)) {
                    damageAmount *= 2;
                }
                else if (damageSource.is(DamageTypeTags.IS_FREEZING)) {
                    player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 3 * 20));
                }
                if (damageAmount >= entity.getHealth() && !player.hasEffect(ModEffects.GREEN_AVATAR_REGROW_BLOCK)) {
                    if (StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarRegrow(player)) {
                        return 0f;
                    }
                }
            }
            return damageAmount;
        });
        LivingEntityCallback.Fall.Before.EVENT.register((entity, fallDamage) -> {
            if (entity instanceof final ServerPlayer player && StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarActive(player)) {
                if (fallDamage >= entity.getHealth() && !player.hasEffect(ModEffects.GREEN_AVATAR_REGROW_BLOCK)) {
                    if (StrangeAdventuresApi.INTERNAL_METHODS.greenAvatarRegrow(player)) {
                        return 0f;
                    }
                }
            }
            return fallDamage;
        });
    }

}
