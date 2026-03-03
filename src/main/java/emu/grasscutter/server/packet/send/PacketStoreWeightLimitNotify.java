package emu.grasscutter.server.packet.send;

import static emu.grasscutter.config.Configuration.INVENTORY_LIMITS;

import emu.grasscutter.net.packet.*;
import emu.grasscutter.net.proto.StoreTypeOuterClass.StoreType;
import emu.grasscutter.net.proto.StoreWeightLimitNotifyOuterClass.StoreWeightLimitNotify;

public class PacketStoreWeightLimitNotify extends BasePacket {

    public PacketStoreWeightLimitNotify() {
        super(PacketOpcodes.StoreWeightLimitNotify);

        StoreWeightLimitNotify p =
                StoreWeightLimitNotify.newBuilder()
                        // .setStoreType(StoreType.StoreType_STORE_PACK) // field not in current proto
                        .setWeightLimit(INVENTORY_LIMITS.all)
                        // .setWeaponCountLimit(INVENTORY_LIMITS.weapons) // field not in current proto
                        // .setReliquaryCountLimit(INVENTORY_LIMITS.relics) // field not in current proto
                        // .setMaterialCountLimit(INVENTORY_LIMITS.materials) // field not in current proto
                        // .setFurnitureCountLimit(INVENTORY_LIMITS.furniture) // field not in current proto
                        .build();

        this.setData(p);
    }
}
