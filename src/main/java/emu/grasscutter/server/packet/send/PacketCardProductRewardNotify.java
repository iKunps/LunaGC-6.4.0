package emu.grasscutter.server.packet.send;

import emu.grasscutter.net.packet.*;
import emu.grasscutter.net.proto.CardProductRewardNotifyOuterClass.CardProductRewardNotify;

public class PacketCardProductRewardNotify extends BasePacket {

    public PacketCardProductRewardNotify(int remainsDay) {
        super(PacketOpcodes.CardProductRewardNotify);

        CardProductRewardNotify proto =
                CardProductRewardNotify.newBuilder()
                        .setProductId("ys_chn_blessofmoon_tier5")
                        // .setHcoin(90) // field not in current proto
                        // .setRemainDays(remainsDay) // field not in current proto
                        .build();

        // Hard code Product id keep cool 😎

        this.setData(proto);
    }
}
