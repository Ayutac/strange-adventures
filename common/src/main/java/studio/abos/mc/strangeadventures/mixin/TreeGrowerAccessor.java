package studio.abos.mc.strangeadventures.mixin;

import net.minecraft.world.level.block.grower.TreeGrower;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(TreeGrower.class)
public interface TreeGrowerAccessor {

    @Accessor("name")
    String getName();

    @Accessor("GROWERS")
    static Map<String, TreeGrower> getGrowers() {
        throw new AssertionError("Untransformed @Accessor");
    }

}
