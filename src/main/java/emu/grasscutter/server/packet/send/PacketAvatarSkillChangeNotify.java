package emu.grasscutter.server.packet.send;

import emu.grasscutter.game.avatar.Avatar;
import emu.grasscutter.net.packet.*;
import emu.grasscutter.net.proto.AvatarSkillChangeNotifyOuterClass.AvatarSkillChangeNotify;

public class PacketAvatarSkillChangeNotify extends BasePacket {

    public PacketAvatarSkillChangeNotify(Avatar avatar, int skillId, int oldLevel, int curLevel) {
        super(PacketOpcodes.AvatarSkillChangeNotify);

        AvatarSkillChangeNotify proto =
                AvatarSkillChangeNotify.newBuilder()
                        .setAvatarGuid(avatar.getGuid())
                        .setEntityId(avatar.getEntityId())
                        .setSkillDepotId(avatar.getSkillDepotId())
                        // .setAvatarSkillId(skillId) // field not in current proto
                        // .setOldLevel(oldLevel) // field not in current proto
                        // .setCurLevel(curLevel) // field not in current proto
                        .build();

        this.setData(proto);
    }
}
