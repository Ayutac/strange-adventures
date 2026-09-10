package studio.abos.mc.strangeadventures.fabric.mixin;

import net.minecraft.world.level.block.grower.TreeGrower;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(TreeGrower.class)
public interface TreeGrowerAccessor {

    @Accessor("name")
    String getName();

}
