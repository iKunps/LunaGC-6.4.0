package emu.grasscutter.server.packet.send;

import emu.grasscutter.game.avatar.Avatar;
import emu.grasscutter.net.packet.*;
import emu.grasscutter.net.proto.ProudSkillExtraLevelNotifyOuterClass.ProudSkillExtraLevelNotify;

public class PacketProudSkillExtraLevelNotify extends BasePacket {

    public PacketProudSkillExtraLevelNotify(Avatar avatar, int talentIndex) {
        super(PacketOpcodes.ProudSkillExtraLevelNotify);

        ProudSkillExtraLevelNotify proto =
                ProudSkillExtraLevelNotify.newBuilder()
                        .setAvatarGuid(avatar.getGuid())
                        // .setTalentType(3) // Talent type = 3 // field not in current proto
                        // .setTalentIndex(talentIndex) // field not in current proto
                        // .setExtraLevel(3) // field not in current proto
                        .build();

        this.setData(proto);
    }
}
