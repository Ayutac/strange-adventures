package studio.abos.mc.strangeadventures.data;

import net.blay09.mods.balm.platform.attachment.BalmDataAttachmentTypeRegistrar;
import net.blay09.mods.balm.platform.attachment.DataAttachmentLookup;

public final class ModDataAttachments {

    public final DataAttachmentLookup<GreenAvatarData> GREEN_AVATAR_DATA;

    public ModDataAttachments(final BalmDataAttachmentTypeRegistrar registrar) {
        GREEN_AVATAR_DATA = registrar
                .<GreenAvatarData>register("green_avatar_data", builder -> builder
                        .initializer(GreenAvatarData::new)
                        .persistent(GreenAvatarData.CODEC)
                        .networkSynchronized(GreenAvatarData.STREAM_CODEC)
                        .copyOnDeath())
                .asLookup();
    }

}
