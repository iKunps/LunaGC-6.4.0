package emu.grasscutter.server.packet.send;

import emu.grasscutter.game.player.Player;
import emu.grasscutter.net.packet.*;
import emu.grasscutter.net.proto.AbilitySyncStateInfoOuterClass.AbilitySyncStateInfo;
import emu.grasscutter.net.proto.SceneTeamAvatarOuterClass.SceneTeamAvatar;
import emu.grasscutter.net.proto.SceneTeamUpdateNotifyOuterClass.SceneTeamUpdateNotify;

public class PacketSceneTeamUpdateNotify extends BasePacket {
    public PacketSceneTeamUpdateNotify(Player player) {
        super(PacketOpcodes.SceneTeamUpdateNotify);

        var proto = SceneTeamUpdateNotify.newBuilder().setIsInMp(player.getWorld().isMultiplayer());

        for (var p : player.getWorld().getPlayers()) {
            for (var entityAvatar : p.getTeamManager().getActiveTeam(true)) {
                var avatarProto =
                        SceneTeamAvatar.newBuilder()
                                .setPlayerUid(p.getUid())
                                .setAvatarGuid(entityAvatar.getAvatar().getGuid())
                                .setSceneId(p.getSceneId())
                                .setEntityId(entityAvatar.getId())
                                .setSceneEntityInfo(entityAvatar.toProto())
                                .setWeaponGuid(entityAvatar.getAvatar().getWeaponNotNull().getGuid())
                                .setWeaponEntityId(entityAvatar.getWeaponEntityId())
                                // .setIsPlayerCurAvatar(p.getTeamManager().getCurrentAvatarEntity() == entityAvatar) // field not in current proto
                                // .setIsOnScene(p.getTeamManager().getCurrentAvatarEntity() == entityAvatar) // field not in current proto
                                // .setAvatarAbilityInfo(AbilitySyncStateInfo.newBuilder()) // field not in current proto
                                // .setWeaponAbilityInfo(AbilitySyncStateInfo.newBuilder()) // field not in current proto
                                // .setAbilityControlBlock(entityAvatar.getAbilityControlBlock()) // field not in current proto
                                ;

                if (player.getWorld().isMultiplayer()) {
                    avatarProto.setAvatarInfo(entityAvatar.getAvatar().toProto());
                    avatarProto.setSceneAvatarInfo(entityAvatar.getSceneAvatarInfo()); // why mihoyo...
                }

                proto.addSceneTeamAvatarList(avatarProto);
            }
        }

        this.setData(proto);
    }
}
