package studio.abos.mc.strangeadventures.api;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
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
     * Regrows the player like an Avatar of the Green. Doesn't check conditions for this regrow, only executes it.
     * @param player the player to regrow
     * @return <code>true</code> if the regrow was successful, else <code>false</code> (for example when no appropriate
     * regrow block was found).
     */
    boolean greenAvatarRegrow(final ServerPlayer player);

}
