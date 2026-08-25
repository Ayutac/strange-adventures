package studio.abos.mc.strangeadventures.client.blockrenderer;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;

import java.util.Comparator;

@Getter
@Setter
public class EssenceCauldronBlockEntityRenderState extends BlockEntityRenderState {

    protected static final Comparator<FluidRenderState> NULL_LAST_COMPARATOR = Comparator.nullsLast(Comparator.comparingInt(_ -> 0));

    private FluidRenderState[] fluids;
    private float rotation;
    private float height;

    public record FluidRenderState(net.minecraft.client.renderer.texture.TextureAtlasSprite sprite, int tint) {}

}
