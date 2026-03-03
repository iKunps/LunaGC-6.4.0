package emu.grasscutter.server.packet.recv;

import emu.grasscutter.net.packet.*;
import emu.grasscutter.net.proto.DoGachaReqOuterClass.DoGachaReq;
import emu.grasscutter.server.game.GameSession;

@Opcodes(PacketOpcodes.DoGachaReq)
public class HandlerDoGachaReq extends PacketHandler {
    @Override
    public void handle(GameSession session, byte[] header, byte[] payload) throws Exception {
        DoGachaReq req = DoGachaReq.parseFrom(payload);

        // req.getGachaScheduleId() - field not in current proto
        // session.getServer().getGachaSystem().doPulls(session.getPlayer(), req.getGachaScheduleId(), req.getGachaTimes());
    }
}
