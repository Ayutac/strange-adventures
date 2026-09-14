package studio.abos.mc.strangeadventures.effect;

import net.blay09.mods.balm.core.BalmRegistrar;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;

public final class ModEffects {

    public static Holder<MobEffect> GREEN_AVATAR_REVIVAL_BLOCK;

    public static void initialize(final BalmRegistrar.Scoped<MobEffect> mobEffects) {
        GREEN_AVATAR_REVIVAL_BLOCK = mobEffects.register("green_avatar_revival_block", _ -> new GreenAvatarRevivalBlock());
    }

}
