package studio.abos.mc.strangeadventures.api;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;

public interface InternalMethods {

    void tpOverworldToGreen(final LivingEntity toTeleport, final BlockPos startPos);

    void tpGreenToOverworld(final LivingEntity toTeleport, final BlockPos startPos);

}
