package studio.abos.mc.strangeadventures.client.blockrenderer;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.phys.Vec3;

import java.util.Comparator;

@Getter
@Setter
public class EssenceCauldronBlockEntityRenderState extends BlockEntityRenderState {

    protected static final Comparator<ItemRenderState> ITEM_NULL_LAST_COMPARATOR = Comparator.nullsLast(Comparator.comparingInt(_ -> 0));
    protected static final Comparator<FluidRenderState> FLUID_NULL_LAST_COMPARATOR = Comparator.nullsLast(Comparator.comparingInt(_ -> 0));

    private ItemRenderState[] items;
    private FluidRenderState[] fluids;
    private float rotation;
    private float height;

    public record ItemRenderState(ItemStackRenderState renderState, Vec3 relPos) {}

    public record FluidRenderState(TextureAtlasSprite sprite, int tint) {}

}
