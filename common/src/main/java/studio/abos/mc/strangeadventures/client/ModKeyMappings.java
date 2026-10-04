package studio.abos.mc.strangeadventures.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.blay09.mods.kuma.api.InputBinding;
import net.blay09.mods.kuma.api.Kuma;
import net.blay09.mods.kuma.api.ManagedKeyMapping;
import studio.abos.mc.strangeadventures.StrangeAdventures;
import studio.abos.mc.strangeadventures.network.ServerboundGreenAvatarAttemptSplitPayload;

public class ModKeyMappings {

    public static ManagedKeyMapping greenAvatarSplitKey;

    // TODO remove this before release
    public static void initialize() {
        greenAvatarSplitKey = Kuma.createKeyMapping(StrangeAdventures.id("green_avatar_split"))
                .withDefault(InputBinding.key(InputConstants.KEY_Z))
                .handleWorldInput(e -> {
                    StrangeAdventures.platformProxy().c2s(ServerboundGreenAvatarAttemptSplitPayload.INSTANCE);
                    return true;
                })
                .build();
    }

}
