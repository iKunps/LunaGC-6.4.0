package emu.grasscutter.server.packet.recv;

import emu.grasscutter.net.packet.*;
import emu.grasscutter.net.proto.AvatarExpeditionStartReqOuterClass.AvatarExpeditionStartReq;
import emu.grasscutter.server.game.GameSession;
import emu.grasscutter.server.packet.send.PacketAvatarExpeditionStartRsp;
import emu.grasscutter.utils.Utils;

@Opcodes(PacketOpcodes.AvatarExpeditionStartReq)
public class HandlerAvatarExpeditionStartReq extends PacketHandler {
    @Override
    public void handle(GameSession session, byte[] header, byte[] payload) throws Exception {
        AvatarExpeditionStartReq req = AvatarExpeditionStartReq.parseFrom(payload);
        var player = session.getPlayer();

        // Proto structure changed: getAvatarGuid(), getExpId(), getHourTime() no longer exist.
        // The proto now uses getBasicInfoListList() which returns a list of AvatarExpeditionBasicInfo.
        // TODO: Reimplement using req.getBasicInfoListList()
        // int startTime = Utils.getCurrentSeconds();
        // player.addExpeditionInfo(req.getAvatarGuid(), req.getExpId(), req.getHourTime(), startTime);
        // player.save();
        session.send(new PacketAvatarExpeditionStartRsp(player.getExpeditionInfo()));
    }
}
