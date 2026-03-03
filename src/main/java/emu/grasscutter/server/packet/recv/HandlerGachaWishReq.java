package emu.grasscutter.server.packet.recv;

import emu.grasscutter.game.gacha.*;
import emu.grasscutter.net.packet.*;
import emu.grasscutter.net.proto.GachaWishReqOuterClass.GachaWishReq;
import emu.grasscutter.server.game.GameSession;
import emu.grasscutter.server.packet.send.PacketGachaWishRsp;

@Opcodes(PacketOpcodes.GachaWishReq)
public class HandlerGachaWishReq extends PacketHandler {

    @Override
    public void handle(GameSession session, byte[] header, byte[] payload) throws Exception {
        GachaWishReq req = GachaWishReq.parseFrom(payload);

        // req.getGachaScheduleId(), req.getGachaType() - fields not in current proto
        // Cannot look up banner without schedule ID; stub response
        session.send(new PacketGachaWishRsp(
                0,
                0,
                req.getItemId(),
                0,
                0));
    }
}
