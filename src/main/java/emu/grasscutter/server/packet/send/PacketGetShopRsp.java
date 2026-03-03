package emu.grasscutter.server.packet.send;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.game.shop.*;
import emu.grasscutter.net.packet.*;
import emu.grasscutter.net.proto.*;
import emu.grasscutter.net.proto.ShopGoodsOuterClass.ShopGoods;
import emu.grasscutter.net.proto.ShopOuterClass.Shop;
import emu.grasscutter.utils.Utils;
import java.util.*;
import java.util.stream.Collectors;

public class PacketGetShopRsp extends BasePacket {
    public PacketGetShopRsp(Player player, int shopType) {
        super(PacketOpcodes.GetShopRsp);

        Shop.Builder shop =
                Shop.newBuilder()
                        .setShopType(shopType)
                        .setCityId(1); // mock
                        // .setCityReputationLevel(10) // mock // field not in current proto

        ShopSystem manager = Grasscutter.getGameServer().getShopSystem();
        if (manager.getShopData().get(shopType) != null) {
            List<ShopInfo> list = manager.getShopData().get(shopType);
            List<ShopGoods> goodsList = new ArrayList<>();
            
            for (ShopInfo info : list) {
                ShopGoods.Builder goods =
                        ShopGoods.newBuilder()
                                .setGoodsId(info.getGoodsId())
                                .setGoodsItem(
                                        ItemParamOuterClass.ItemParam.newBuilder()
                                                .setItemId(info.getGoodsItem().getId())
                                                .setCount(info.getGoodsItem().getCount())
                                                .build())
                                // .setScoin(info.getScoin()) // field not in current proto
                                // .setHcoin(info.getHcoin()) // field not in current proto
                                // .setBuyLimit(info.getBuyLimit()) // field not in current proto
                                .setBeginTime(info.getBeginTime())
                                .setEndTime(info.getEndTime())
                                // .setMinLevel(info.getMinLevel()) // field not in current proto
                                .setMaxLevel(info.getMaxLevel());
                                // .setMcoin(info.getMcoin()) // field not in current proto
                                // .setDisableType(info.getDisableType()) // field not in current proto
                                // .setLKICBMCBHMH(true) // field not in current proto

                if (info.getCostItemList() != null) {
                    goods.addAllCostItemList(
                            info.getCostItemList().stream()
                                    .map(x ->
                                            ItemParamOuterClass.ItemParam.newBuilder()
                                                    .setItemId(x.getId())
                                                    .setCount(x.getCount())
                                                    .build())
                                    .collect(Collectors.toList()));
                }
                
                // if (info.getPreGoodsIdList() != null) {
                //     goods.addAllPreGoodsIdList(info.getPreGoodsIdList()); // field not in current proto
                // }

                int currentTs = Utils.getCurrentSeconds();
                ShopLimit currentShopLimit = player.getGoodsLimit(info.getGoodsId());
                int nextRefreshTime = ShopSystem.getShopNextRefreshTime(info);
                
                if (currentShopLimit != null) {
                    if (currentShopLimit.getNextRefreshTime() < currentTs) {
                        currentShopLimit.setHasBoughtInPeriod(0);
                        currentShopLimit.setNextRefreshTime(nextRefreshTime);
                    }
                    // goods.setBoughtNum(currentShopLimit.getHasBoughtInPeriod()); // field not in current proto
                    goods.setNextRefreshTime(currentShopLimit.getNextRefreshTime());
                } else {
                    player.addShopLimit(goods.getGoodsId(), 0, nextRefreshTime);
                    goods.setNextRefreshTime(nextRefreshTime);
                }

                goodsList.add(goods.build());
            }
            
            shop.addAllGoodsList(goodsList);
        }

        player.save();
        this.setData(GetShopRspOuterClass.GetShopRsp.newBuilder().setShop(shop).build());
    }
}