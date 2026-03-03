package emu.grasscutter.server.packet.recv;

import emu.grasscutter.net.packet.*;
import emu.grasscutter.net.proto.EvtEntityRenderersChangedNotifyOuterClass;
import emu.grasscutter.server.game.GameSession;
import emu.grasscutter.server.packet.send.PacketEvtEntityRenderersChangedNotify;

@Opcodes(PacketOpcodes.EvtEntityRenderersChangedNotify)
public class HandlerEvtEntityRenderersChangedNotify extends PacketHandler {

    @Override
    public void handle(GameSession session, byte[] header, byte[] payload) throws Exception {
        var req =
                EvtEntityRenderersChangedNotifyOuterClass.EvtEntityRenderersChangedNotify.parseFrom(
                        payload);

        // req.getForwardType() - field not in current proto, default to broadcast to all
        session
                .getPlayer()
                .getScene()
                .broadcastPacket(new PacketEvtEntityRenderersChangedNotify(req));
    }
}
