package emu.grasscutter.server.packet.send;

import emu.grasscutter.data.common.ItemParamData;
import emu.grasscutter.game.gacha.*;
import emu.grasscutter.net.packet.*;
import emu.grasscutter.net.proto.DoGachaRspOuterClass.DoGachaRsp;
import emu.grasscutter.net.proto.GachaItemOuterClass.GachaItem;
import emu.grasscutter.net.proto.RetcodeOuterClass;
import emu.grasscutter.net.proto.RetcodeOuterClass.Retcode;
import java.util.List;

public class PacketDoGachaRsp extends BasePacket {

    public PacketDoGachaRsp(
            GachaBanner banner, List<GachaItem> list, PlayerGachaBannerInfo gachaInfo) {
        super(PacketOpcodes.DoGachaRsp);

        ItemParamData costItem = banner.getCost(1);
        ItemParamData costItem10 = banner.getCost(10);
        int gachaTimesLimit = banner.getGachaTimesLimit();
        int leftGachaTimes =
                switch (gachaTimesLimit) {
                    case Integer.MAX_VALUE -> Integer.MAX_VALUE;
                    default -> Math.max(gachaTimesLimit - gachaInfo.getTotalPulls(), 0);
                };
        DoGachaRsp.Builder rsp =
                DoGachaRsp.newBuilder()
                        // .setGachaType(banner.getGachaType()) // field not in current proto
                        // .setGachaScheduleId(banner.getScheduleId()) // field not in current proto
                        .setGachaTimes(list.size())
                        // .setNewGachaRandom(12345) // field not in current proto
                        // .setLeftGachaTimes(leftGachaTimes) // field not in current proto
                        // .setGachaTimesLimit(gachaTimesLimit) // field not in current proto
                        // .setCostItemId(costItem.getId()) // field not in current proto
                        // .setCostItemNum(costItem.getCount()) // field not in current proto
                        // .setTenCostItemId(costItem10.getId()) // field not in current proto
                        // .setTenCostItemNum(costItem10.getCount()) // field not in current proto
                        // .addAllGachaItemList(list) // field not in current proto
                        ;

        if (banner.hasEpitomized()) {
            // rsp // field not in current proto
                    // .setWishItemId(gachaInfo.getWishItemId())
                    // .setWishProgress(gachaInfo.getFailedChosenItemPulls())
                    // .setWishMaxProgress(banner.getWishMaxProgress());
        }

        this.setData(rsp.build());
    }

    public PacketDoGachaRsp() {
        super(PacketOpcodes.DoGachaRsp);

        DoGachaRsp p =
                DoGachaRsp.newBuilder().setRetcode(RetcodeOuterClass.Retcode.RET_SVR_ERROR_VALUE).build();

        this.setData(p);
    }

    public PacketDoGachaRsp(Retcode retcode) {
        super(PacketOpcodes.DoGachaRsp);

        DoGachaRsp p = DoGachaRsp.newBuilder().setRetcode(retcode.getNumber()).build();

        this.setData(p);
    }
}
