package emu.grasscutter.server.packet.send;

import emu.grasscutter.net.packet.*;
import emu.grasscutter.net.proto.CombineDataNotifyOuterClass.CombineDataNotify;

public class PacketCombineDataNotify extends BasePacket {

    public PacketCombineDataNotify(Iterable<Integer> unlockedCombines) {
        super(PacketOpcodes.CombineDataNotify);

        CombineDataNotify proto =
                CombineDataNotify.newBuilder()
                        // .addAllCombineIdList(unlockedCombines) // field not in current proto
                        .build();

        this.setData(proto);
    }
}
