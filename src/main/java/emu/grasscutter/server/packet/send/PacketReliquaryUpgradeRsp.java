package emu.grasscutter.server.packet.send;

import emu.grasscutter.game.inventory.GameItem;
import emu.grasscutter.net.packet.*;
import emu.grasscutter.net.proto.ReliquaryUpgradeRspOuterClass.ReliquaryUpgradeRsp;
import java.util.List;

public class PacketReliquaryUpgradeRsp extends BasePacket {

    public PacketReliquaryUpgradeRsp(
            GameItem relic, int rate, int oldLevel, List<Integer> oldAppendPropIdList) {
        super(PacketOpcodes.ReliquaryUpgradeRsp);

        ReliquaryUpgradeRsp proto =
                ReliquaryUpgradeRsp.newBuilder()
                        .setTargetReliquaryGuid(relic.getGuid())
                        // .setOldLevel(oldLevel) // field not in current proto
                        // .setCurLevel(relic.getLevel()) // field not in current proto
                        // .setPowerUpRate(rate) // field not in current proto
                        // .addAllOldAppendPropList(oldAppendPropIdList) // field not in current proto
                        // .addAllCurAppendPropList(relic.getAppendPropIdList()) // field not in current proto
                        .build();

        this.setData(proto);
    }
}
