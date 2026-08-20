package studio.abos.mc.strangeadventures.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.blay09.mods.kuma.api.InputBinding;
import net.blay09.mods.kuma.api.Kuma;
import net.blay09.mods.kuma.api.ManagedKeyMapping;
import studio.abos.mc.strangeadventures.StrangeAdventures;

public class ModKeyMappings {

    public static ManagedKeyMapping yourKey;

    public static void initialize() {
        yourKey = Kuma.createKeyMapping(StrangeAdventures.id("your_key"))
                .withDefault(InputBinding.key(InputConstants.KEY_B))
                .handleScreenInput(event -> {
                    StrangeAdventures.logger.info("B was pressed - " + StrangeAdventures.MOD_ID);
                    return true;
                })
                .build();
    }
}
