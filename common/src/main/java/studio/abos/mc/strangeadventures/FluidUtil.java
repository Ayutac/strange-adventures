package studio.abos.mc.strangeadventures;

import lombok.experimental.UtilityClass;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.material.Fluid;

@UtilityClass
public class FluidUtil {

    public final long DROPLETS_PER_MB = 81L;
    public final long MB_PER_BUCKET = 1000L;
    public final String MB_FILLED_TEMPLATE = "%d/%dmB";

    public Component name(final Fluid fluid) {
        return Component.translatable("fluid." + BuiltInRegistries.FLUID.getKey(fluid).toString().replace(':', '.'));
    }

}
