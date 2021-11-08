package cn.ch3nnn.handle;


import cn.ch3nnn.OutboundHandler;
import cn.ch3nnn.annotation.OutBoundEventName;
import lombok.extern.slf4j.Slf4j;
import org.freeswitch.esl.client.transport.SendMsg;
import org.freeswitch.esl.client.transport.event.EslEvent;
import org.jboss.netty.channel.ChannelHandlerContext;

/**
 * 放音结束事件
 *
 * @Author ChenTong
 * @Date 2021/11/2 18:22
 */
@Slf4j
@OutBoundEventName("PLAYBACK_STOP")
public class PlayBackSTOPOutBoundEventHandler implements OutBoundEventHandler {

    @Override
    public void handle(ChannelHandlerContext ctx, EslEvent event, OutboundHandler handler) {
        SendMsg bridgeMsg1 = new SendMsg();
        bridgeMsg1.addCallCommand("execute");
        bridgeMsg1.addExecuteAppName("hangup");
        handler.sendSyncMultiLineCommand(ctx.getChannel(), bridgeMsg1.getMsgLines());
    }
}
