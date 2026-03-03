package emu.grasscutter.server.packet.send;

import emu.grasscutter.game.inventory.GameItem;
import emu.grasscutter.net.packet.*;
import emu.grasscutter.net.proto.WeaponPromoteRspOuterClass.WeaponPromoteRsp;

public class PacketWeaponPromoteRsp extends BasePacket {

    public PacketWeaponPromoteRsp(GameItem item, int oldPromoteLevel) {
        super(PacketOpcodes.WeaponPromoteRsp);

        WeaponPromoteRsp proto =
                WeaponPromoteRsp.newBuilder()
                        .setTargetWeaponGuid(item.getGuid())
                        // .setCurPromoteLevel(item.getPromoteLevel()) // field not in current proto
                        // .setOldPromoteLevel(oldPromoteLevel) // field not in current proto
                        .build();

        this.setData(proto);
    }
}
