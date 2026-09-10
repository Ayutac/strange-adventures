package studio.abos.mc.strangeadventures.api;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

public interface InternalMethods {

    void tpOverworldToGreen(final LivingEntity toTeleport, final BlockPos startPos);

    void tpGreenToOverworld(final LivingEntity toTeleport, final BlockPos startPos);

    DamageSource createTreeTransformatorDamageSource(final Level level, final @Nullable Entity attacker);

}
