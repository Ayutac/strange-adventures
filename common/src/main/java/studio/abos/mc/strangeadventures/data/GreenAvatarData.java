package studio.abos.mc.strangeadventures.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GreenAvatarData {

    public static final Codec<GreenAvatarData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.BOOL.fieldOf("active").forGetter(GreenAvatarData::isActive)
        ).apply(instance, GreenAvatarData::new));

    protected boolean active;

}
