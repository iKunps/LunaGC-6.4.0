package emu.grasscutter.server.packet.send;

import emu.grasscutter.game.achievement.Achievement;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.net.packet.*;
import emu.grasscutter.net.proto.AchievementAllDataNotifyOuterClass;

public class PacketAchievementAllDataNotify extends BasePacket {
    public PacketAchievementAllDataNotify(Player player) {
        super(PacketOpcodes.AchievementAllDataNotify);

        var achievements = player.getAchievements();
        var notify =
                AchievementAllDataNotifyOuterClass.AchievementAllDataNotify.newBuilder()
                        // .addAllAchievementList(achievements.getAchievementList().values().stream().map(Achievement::toProto).toList()) // field not in current proto
                        .addAllRewardTakenGoalIdList(achievements.getTakenGoalRewardIdList())
                        .build();

        this.setData(notify);
    }
}
