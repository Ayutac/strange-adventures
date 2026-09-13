package studio.abos.mc.strangeadventures.data;

import net.blay09.mods.balm.platform.attachment.BalmDataAttachmentTypeRegistrar;
import net.blay09.mods.balm.platform.attachment.DataAttachmentLookup;

public final class ModDataAttachments {

    public final DataAttachmentLookup<GreenAvatarData> GREEN_AVATAR_DATA;

    public ModDataAttachments(final BalmDataAttachmentTypeRegistrar registrar) {
        GREEN_AVATAR_DATA = registrar
                .register("green_avatar_data", GreenAvatarData.CODEC, GreenAvatarData::new, true)
                .asLookup();
    }

}
