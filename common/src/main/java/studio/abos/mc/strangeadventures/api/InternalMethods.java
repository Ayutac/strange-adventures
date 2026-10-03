package studio.abos.mc.strangeadventures.api;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

public interface InternalMethods {

    void tpOverworldToGreen(final LivingEntity toTeleport, final BlockPos startPos);

    void tpGreenToOverworld(final LivingEntity toTeleport, final BlockPos startPos);

    DamageSource createTreeTransformatorDamageSource(final Level level, final @Nullable Entity attacker);

    /**
     * Makes the specified player an Avatar of the Green.
     * @param player the player to convert
     * @return <code>true</code> if this call changed the player, else <code>false</code>.
     */
    boolean greenAvatarActivate(final ServerPlayer player);

    /**
     * If the specified player is an Avatar of the Green
     * @param player the player to check
     * @return <code>true</code> if the player is an Avatar of the Green, else <code>false</code>.
     */
    boolean greenAvatarActive(final Player player);

    /**
     * Unmakes the specified player an Avatar of the Green.
     * @param player the player to convert
     * @return <code>true</code> if this call changed the player, else <code>false</code>.
     */
    boolean greenAvatarDeactivate(final ServerPlayer player);

    /**
     * Gets the AotG mass of the given player,
     * clamped between {@link StrangeAdventuresApi#GREEN_AVATAR_MASS_MIN} and {@link StrangeAdventuresApi#GREEN_AVATAR_MASS_MAX}.
     * @param player the player to get the Avatar of the Green mass of
     * @return The mass of the Avatar of the Green.
     */
    float greenAvatarGetMass(final Player player);

    /**
     * Sets the mass of the given player (but no other AotG data fields) to the specified amount,
     * clamped between {@link StrangeAdventuresApi#GREEN_AVATAR_MASS_MIN} and {@link StrangeAdventuresApi#GREEN_AVATAR_MASS_MAX}.
     * @param player the player to change the Avatar of the Green mass of
     * @param amount the amount to set
     * @return The mass after the change.
     */
    float greenAvatarSetMass(final ServerPlayer player, float amount);

    /**
     * Changes the mass of the given player (but no other AotG data fields) by the specified amount,
     * clamped between {@link StrangeAdventuresApi#GREEN_AVATAR_MASS_MIN} and {@link StrangeAdventuresApi#GREEN_AVATAR_MASS_MAX}.
     * <br>
     * Note that if the player didn't have mass previously, the amount here will be added to <code>1f</code>.
     * @param player the player to change the Avatar of the Green mass of
     * @param amount the amount to change, can be negative
     * @return The total mass after the change.
     */
    float greenAvatarAddMass(final ServerPlayer player, float amount);

    /**
     * Attempts to eat the specified block as AotG, replacing it with air.
     * @param player the player who tries to eat; no checks will be done if the player has AotG active
     * @param level the level of the attempt
     * @param pos the position of the block
     * @param state the state of the block; if <code>null</code>, will be looked up via <code>level</code> and <code>pos</code>
     * @return <code>true</code> if the consumption was successful (or would be), else <code>false</code>.
     */
    boolean greenAvatarAttemptToEat(final Player player, final Level level, final BlockPos pos, @Nullable BlockState state);

    /**
     * Resets the alive ticks of this AotG data. Doesn't check if the player is an active AotG
     * @param player the player to reset the alive ticks of
     */
    void greenAvatarResetAliveTick(ServerPlayer player);

    /**
     * Regrows the player like an Avatar of the Green. Doesn't check conditions for this regrow, only executes it.
     * @param player the player to regrow
     * @return <code>true</code> if the regrow was successful, else <code>false</code> (for example when no appropriate
     * regrow block was found).
     */
    boolean greenAvatarRegrow(final ServerPlayer player);

    /**
     * Roots the player. Doesn't check for AotG to be active, only tries to root.
     * @param player the player to take root
     * @return <code>true</code> if rooting was successful, else <code>false</code>
     */
    boolean greenAvatarTryTakeRoot(final ServerPlayer player);

    /**
     * Checks if the player is rooted (independent of checking for AotG).
     * @param player the player to check
     * @return <code>true</code> if the player is rooted, else <code>false</code>
     */
    boolean greenAvatarRooted(final Player player);

    /**
     * Uproots the player. Doesn't check if they were rooted or even are AotG
     * @param player the player to uproot
     */
    void greenAvatarUproot(final Player player);

    /**
     * Takes care of regeneration and other tickly needs for the Avatar of the Green.
     * Does not check if the player is currently an AotG.
     * @param player the player to tick
     */
    void greenAvatarTick(final ServerPlayer player);

}
