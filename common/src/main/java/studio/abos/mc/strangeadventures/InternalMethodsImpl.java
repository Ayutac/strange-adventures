package studio.abos.mc.strangeadventures;

import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Relative;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.apache.commons.lang3.function.TriConsumer;
import org.jspecify.annotations.Nullable;
import studio.abos.mc.strangeadventures.api.InternalMethods;
import studio.abos.mc.strangeadventures.api.StrangeAdventuresApi;
import studio.abos.mc.strangeadventures.block.ModBlocks;
import studio.abos.mc.strangeadventures.data.GreenAvatarData;
import studio.abos.mc.strangeadventures.effect.ModEffects;
import studio.abos.mc.strangeadventures.mixin.InventoryAccessor;
import studio.abos.mc.strangeadventures.tag.ModBlockTags;

import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;

public class InternalMethodsImpl implements InternalMethods {

    private static final int[] XZ_TP_RANGE = new int[] {0,-1,1,-2,2};
    private static final int[] Y_TP_RANGE = new int[] {0,-1,1,-2,2,-3,3,-4,4};
    private static final int GREEN_AVATAR_REGROW_RANGE = 16;

    private static Optional<BlockPos> findBestTpPosition(final LevelAccessor level, final BlockPos idealPos, final int height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be positive!");
        }
        final var mutablePos = new BlockPos.MutableBlockPos();
        for (final int dy : Y_TP_RANGE) {
            for (final int dx : XZ_TP_RANGE) {
                coordLoop: for (final int dz : XZ_TP_RANGE) {
                    mutablePos.set(idealPos);
                    mutablePos.move(dx, dy, dz);
                    for (int i = 0; i < height; i++) {
                        final BlockState blockState = level.getBlockState(mutablePos);
                        if (!(blockState.isAir() || blockState.getBlock() == ModBlocks.GREEN_FLOWER.asBlock())) {
                            continue coordLoop;
                        }
                        mutablePos.move(Direction.UP, 1);
                    }
                    mutablePos.move(Direction.DOWN, height+1);
                    if (!level.getBlockState(mutablePos).isAir()) {
                        mutablePos.move(Direction.UP, 1);
                        return Optional.of(mutablePos.immutable());
                    }
                }
            }
        }
        return Optional.empty();
    }

    private static void makeGreenSpace(final Level level, final BlockPos pos, final int height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be positive!");
        }
        level.setBlockAndUpdate(pos.below(), Blocks.MOSS_BLOCK.defaultBlockState());
        level.setBlockAndUpdate(pos, ModBlocks.GREEN_FLOWER.defaultBlockState());
        for (int i = 2; i <= height; i++) {
            level.setBlockAndUpdate(pos.above(i), Blocks.AIR.defaultBlockState());
        }
    }

    private static void tpFromTo(final ResourceKey<Level> from, final ResourceKey<Level> to, final LivingEntity toTeleport,
                          final BlockPos startPos, final UnaryOperator<BlockPos> targetPosOperator, final TriConsumer<Level, BlockPos, Integer> makeSpaceIfNeeded) {
        if (!from.equals(toTeleport.level().dimension())) {
            return;
        }
        final BlockPos idealTpPos = targetPosOperator.apply(startPos);
        final int entityHeight = (int)Math.ceil(toTeleport.getBbHeight());
        final ServerLevel level = toTeleport.level().getServer().getLevel(to);
        if (level == null) {
            return;
        }
        Optional<BlockPos> bestTpPos = findBestTpPosition(level, idealTpPos, entityHeight);
        if (bestTpPos.isEmpty()) {
            makeSpaceIfNeeded.accept(level, idealTpPos, entityHeight);
            bestTpPos = Optional.of(idealTpPos);
        }
        bestTpPos.ifPresent(pos -> toTeleport.teleportTo(level, pos.getX()+0.5, pos.getY(), pos.getZ()+0.5,
                EnumSet.noneOf(Relative.class), toTeleport.getYRot(), toTeleport.getXRot(), false));
    }

    @Override
    public void tpOverworldToGreen(final LivingEntity toTeleport, final BlockPos startPos) {
        tpFromTo(Level.OVERWORLD, StrangeAdventuresApi.GREEN_DIMENSION, toTeleport, startPos, pos -> new BlockPos(
                pos.getX() / StrangeAdventuresApi.GREEN_DIMENSION_FACTOR, pos.getY(),
                pos.getZ() / StrangeAdventuresApi.GREEN_DIMENSION_FACTOR), InternalMethodsImpl::makeGreenSpace);
    }

    @Override
    public void tpGreenToOverworld(final LivingEntity toTeleport, final BlockPos startPos) {
        tpFromTo(StrangeAdventuresApi.GREEN_DIMENSION, Level.OVERWORLD, toTeleport, startPos, pos -> new BlockPos(
                pos.getX() * StrangeAdventuresApi.GREEN_DIMENSION_FACTOR, pos.getY(),
                pos.getZ() * StrangeAdventuresApi.GREEN_DIMENSION_FACTOR), InternalMethodsImpl::makeGreenSpace);
    }

    @Override
    public DamageSource createTreeTransformatorDamageSource(final Level level, final @Nullable Entity attacker) {
        return new DamageSource(level.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(StrangeAdventuresApi.TREE_TRANSFORMATOR_DAMAGE_TYPE), attacker);
    }

    @Override
    public boolean greenAvatarActivate(final ServerPlayer player) {
        final var lookup = StrangeAdventures.dataAttachments().GREEN_AVATAR_DATA;
        if (lookup.has(player) && lookup.get(player).active()) {
            return false;
        }
        final GreenAvatarData data = lookup.getOrCreate(player);
        lookup.update(player, data.withActive(true));
        updateGreenAvatarAttributes(player);
        ((InventoryAccessor)player.getInventory()).getEquipment().dropAll(player);
        return false;
    }

    @Override
    public boolean greenAvatarActive(final Player player) {
        final var lookup = StrangeAdventures.dataAttachments().GREEN_AVATAR_DATA;
        return lookup.has(player) && lookup.get(player).active();
    }

    @Override
    public boolean greenAvatarDeactivate(final ServerPlayer player) {
        final var lookup = StrangeAdventures.dataAttachments().GREEN_AVATAR_DATA;
        if (!lookup.has(player) || !lookup.get(player).active()) {
            return false;
        }
        final GreenAvatarData data = lookup.get(player);
        lookup.update(player, data.withActive(false));
        clearGreenAvatarAttributes(player);
        return true;
    }

    private void updateGreenAvatarAttributes(final ServerPlayer player) {
        final float mass = greenAvatarGetMass(player);
        player.getAttribute(Attributes.MAX_HEALTH).addOrReplacePermanentModifier(
                new AttributeModifier(StrangeAdventuresApi.MODIFIER_ID_GREEN_AVATAR, mass - 1d, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        player.getAttribute(Attributes.ARMOR).addOrReplacePermanentModifier(
                new AttributeModifier(StrangeAdventuresApi.MODIFIER_ID_GREEN_AVATAR, 20 /* full dia value */, AttributeModifier.Operation.ADD_VALUE));
        player.getAttribute(Attributes.ARMOR_TOUGHNESS).addOrReplacePermanentModifier(
                new AttributeModifier(StrangeAdventuresApi.MODIFIER_ID_GREEN_AVATAR, 6 /* full dia value */, AttributeModifier.Operation.ADD_VALUE));
        player.getAttribute(Attributes.SCALE).addOrReplacePermanentModifier(
                new AttributeModifier(StrangeAdventuresApi.MODIFIER_ID_GREEN_AVATAR, mass - 1d, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
    }

    private void clearGreenAvatarAttributes(final ServerPlayer player) {
        player.getAttribute(Attributes.MAX_HEALTH).removeModifier(StrangeAdventuresApi.MODIFIER_ID_GREEN_AVATAR);
        player.getAttribute(Attributes.ARMOR).removeModifier(StrangeAdventuresApi.MODIFIER_ID_GREEN_AVATAR);
        player.getAttribute(Attributes.ARMOR_TOUGHNESS).removeModifier(StrangeAdventuresApi.MODIFIER_ID_GREEN_AVATAR);
        player.getAttribute(Attributes.SCALE).removeModifier(StrangeAdventuresApi.MODIFIER_ID_GREEN_AVATAR);
    }

    @Override
    public float greenAvatarGetMass(final Player player) {
        final var lookup = StrangeAdventures.dataAttachments().GREEN_AVATAR_DATA;
        if (!lookup.has(player)) {
            return 1f;
        }
        return lookup.get(player).mass();
    }

    @Override
    public float greenAvatarSetMass(final ServerPlayer player, final float amount) {
        final var lookup = StrangeAdventures.dataAttachments().GREEN_AVATAR_DATA;
        final GreenAvatarData data = lookup.getOrCreate(player);
        final float newMass = Mth.clamp(amount, StrangeAdventuresApi.GREEN_AVATAR_MASS_MIN, StrangeAdventuresApi.GREEN_AVATAR_MASS_MAX);
        lookup.update(player, data.withMass(newMass));
        if (data.active()) {
            updateGreenAvatarAttributes(player);
        }
        return newMass;
    }

    @Override
    public float greenAvatarAddMass(final ServerPlayer player, final float amount) {
        final var lookup = StrangeAdventures.dataAttachments().GREEN_AVATAR_DATA;
        final GreenAvatarData data = lookup.getOrCreate(player);
        final float newMass = Mth.clamp(data.mass() + amount, StrangeAdventuresApi.GREEN_AVATAR_MASS_MIN, StrangeAdventuresApi.GREEN_AVATAR_MASS_MAX);
        lookup.update(player, data.withMass(newMass));
        if (data.active()) {
            updateGreenAvatarAttributes(player);
        }
        return newMass;
    }

    @Override
    public boolean greenAvatarAttemptToEat(final Player player, final Level level, final BlockPos pos, @Nullable BlockState state) {
        if (state == null) {
            state = level.getBlockState(pos);
        }
        if (!state.is(ModBlockTags.GREEN_MASS)) {
            return false;
        }
        if (level.isClientSide()) {
            return true;
        }
        level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
        float greenMass = 0f;
        if (state.is(ModBlockTags.GREEN_MASS_HIGH)) {
            greenMass = 1f;
        }
        else if (state.is(ModBlockTags.GREEN_MASS_MEDIUM_HIGH)) {
            greenMass = 0.85f;
        }
        else if (state.is(ModBlockTags.GREEN_MASS_MEDIUM)) {
            greenMass = 0.65f;
        }
        else if (state.is(ModBlockTags.GREEN_MASS_LOW_MEDIUM)) {
            greenMass = 0.5f;
        }
        else if (state.is(ModBlockTags.GREEN_MASS_LOW)) {
            greenMass = 0.3f;
        }
        if (greenMass > 0f) {
            greenAvatarAddMass((ServerPlayer)player, greenMass * (float)Math.max(1e-4, Math.pow((Math.cos(Math.PI * greenAvatarGetMass(player) / StrangeAdventuresApi.GREEN_AVATAR_MASS_MAX) + 1) / 2, 4)));
        }
        return true;
    }

    @Override
    public boolean greenAvatarRegrow(final ServerPlayer player) {
        final List<BlockPos> regrowPosList = new LinkedList<>();
        for (final BlockPos pos : BlockPos.betweenClosed(player.getBlockX() - GREEN_AVATAR_REGROW_RANGE, player.getBlockY() - GREEN_AVATAR_REGROW_RANGE, player.getBlockZ() - GREEN_AVATAR_REGROW_RANGE,
                player.getBlockX() + GREEN_AVATAR_REGROW_RANGE, player.getBlockY() + GREEN_AVATAR_REGROW_RANGE, player.getBlockZ() + GREEN_AVATAR_REGROW_RANGE)) {
            if (!pos.equals(player.blockPosition()) && player.level().getBlockState(pos).is(ModBlockTags.GREEN_AVATAR_CAN_REGROW_IN)) {
                regrowPosList.add(pos.immutable());
            }
        }
        if (regrowPosList.isEmpty()) {
            return false;
        }
        final BlockPos regrowPos = regrowPosList.get(player.getRandom().nextInt(regrowPosList.size()));
        final Vec3 oldPos = player.position();
        player.getInventory().dropAll();
        player.teleportTo(regrowPos.getX() + 0.5, regrowPos.getY(), regrowPos.getZ() + 0.5);
        player.lookAt(EntityAnchorArgument.Anchor.EYES, oldPos);
        player.clearFire();
        player.clearFreeze();
        player.removeAllEffects();
        greenAvatarSetMass(player, 0.5f);
        player.setHealth(player.getMaxHealth() / 2);
        player.addEffect(new MobEffectInstance(ModEffects.GREEN_AVATAR_REGROW_BLOCK, 5 * 60 * 20));
        return true;
    }

}
