package emu.grasscutter.server.packet.send;

import emu.grasscutter.game.avatar.Avatar;
import emu.grasscutter.net.packet.*;
import emu.grasscutter.net.proto.AvatarSkillUpgradeRspOuterClass.AvatarSkillUpgradeRsp;

public class PacketAvatarSkillUpgradeRsp extends BasePacket {

    public PacketAvatarSkillUpgradeRsp(Avatar avatar, int skillId, int oldLevel, int newLevel) {
        super(PacketOpcodes.AvatarSkillUpgradeRsp);

        AvatarSkillUpgradeRsp proto =
                AvatarSkillUpgradeRsp.newBuilder()
                        .setAvatarGuid(avatar.getGuid())
                        // .setAvatarSkillId(skillId) // field not in current proto
                        // .setOldLevel(oldLevel) // field not in current proto
                        // .setCurLevel(newLevel) // field not in current proto
                        .build();

        this.setData(proto);
    }
}
