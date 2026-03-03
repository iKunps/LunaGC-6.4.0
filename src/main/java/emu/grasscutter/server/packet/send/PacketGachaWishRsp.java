package emu.grasscutter.server.packet.send;

import emu.grasscutter.net.packet.*;
import emu.grasscutter.net.proto.GachaWishRspOuterClass.GachaWishRsp;

public class PacketGachaWishRsp extends BasePacket {

    public PacketGachaWishRsp(
            int gachaType, int scheduleId, int itemId, int progress, int maxProgress) {
        super(PacketOpcodes.GachaWishRsp);

        // Most GachaWishRsp fields not in current proto
        GachaWishRsp proto =
                GachaWishRsp.newBuilder()
                        .build();

        this.setData(proto);
    }
}
