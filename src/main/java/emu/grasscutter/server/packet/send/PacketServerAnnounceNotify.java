package emu.grasscutter.server.packet.send;

import emu.grasscutter.net.packet.*;
import emu.grasscutter.net.proto.*;
import emu.grasscutter.utils.Utils;
import java.util.List;

public class PacketServerAnnounceNotify extends BasePacket {

    public PacketServerAnnounceNotify(List<AnnounceDataOuterClass.AnnounceData> data) {
        super(PacketOpcodes.ServerAnnounceNotify);

        var proto = ServerAnnounceNotifyOuterClass.ServerAnnounceNotify.newBuilder();

        proto.addAllAnnounceDataList(data);

        this.setData(proto);
    }

    public PacketServerAnnounceNotify(String msg, int configId) {
        super(PacketOpcodes.ServerAnnounceNotify);

        var proto = ServerAnnounceNotifyOuterClass.ServerAnnounceNotify.newBuilder();

        proto.addAnnounceDataList(
                AnnounceDataOuterClass.AnnounceData.newBuilder()
                        .setConfigId(configId)
                        .setBeginTime(Utils.getCurrentSeconds() + 1)
                        .setEndTime(Utils.getCurrentSeconds() + 2)
                        // .setCenterSystemText(msg) // field not in current proto
                        // .setCenterSystemFrequency(1) // field not in current proto
                        .build());

        this.setData(proto);
    }
}
