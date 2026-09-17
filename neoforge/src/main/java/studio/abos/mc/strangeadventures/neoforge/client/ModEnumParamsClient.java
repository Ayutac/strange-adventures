package studio.abos.mc.strangeadventures.neoforge.client;

import net.minecraft.client.gui.Hud;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;
import studio.abos.mc.strangeadventures.StrangeAdventures;

public class ModEnumParamsClient {

    public static final EnumProxy<Hud.HeartType> HEART_TYPE_GREEN_AVATAR_PROXY = new EnumProxy<>(
            Hud.HeartType.class,
            StrangeAdventures.id("hud/heart/green_avatar_full"),
            StrangeAdventures.id("hud/heart/green_avatar_full_blinking"),
            StrangeAdventures.id("hud/heart/green_avatar_half"),
            StrangeAdventures.id("hud/heart/green_avatar_half_blinking"),
            StrangeAdventures.id("hud/heart/green_avatar_hardcore_full"),
            StrangeAdventures.id("hud/heart/green_avatar_hardcore_full_blinking"),
            StrangeAdventures.id("hud/heart/green_avatar_hardcore_half"),
            StrangeAdventures.id("hud/heart/green_avatar_hardcore_half_blinking")
    );

}
