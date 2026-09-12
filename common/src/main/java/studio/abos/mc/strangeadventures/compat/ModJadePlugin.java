package studio.abos.mc.strangeadventures.compat;

import net.minecraft.resources.Identifier;
import studio.abos.mc.strangeadventures.block.ModBlocks;

public class ModJadePlugin {

    public static class SapSipperComponentProvider {

        public Identifier getUid() {
            return ModBlocks.SAP_SIPPER.asBlockItemId().block().identifier();
        }

    }

}
