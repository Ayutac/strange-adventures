package studio.abos.mc.strangeadventures.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;

public class BottleItem extends Item {

    protected final Fluid fluid;

    public BottleItem(final Fluid fluid, final Properties properties) {
        super(properties);
        this.fluid = fluid;
    }

    public Fluid getContent() {
        return fluid;
    }

}
