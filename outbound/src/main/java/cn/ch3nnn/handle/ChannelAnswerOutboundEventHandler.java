package cn.ch3nnn.handle;


import cn.ch3nnn.OutboundHandler;
import cn.ch3nnn.annotation.OutBoundEventName;
import lombok.extern.slf4j.Slf4j;
import org.freeswitch.esl.client.transport.SendMsg;
import org.freeswitch.esl.client.transport.event.EslEvent;
import org.jboss.netty.channel.ChannelHandlerContext;

import static cn.ch3nnn.config.OutboundServerConfig.BASE_DIR;

/**
 * 应答事件
 *
 * @Author ChenTong
 * @Date 2021/11/2 18:22
 */
@Slf4j
@OutBoundEventName("CHANNEL_ANSWER")
public class ChannelAnswerOutboundEventHandler implements OutBoundEventHandler {

    @Override
    public void handle(ChannelHandlerContext ctx, EslEvent event, OutboundHandler handler) {

        SendMsg bridgeMsg = new SendMsg();
        bridgeMsg.addCallCommand("execute");
        bridgeMsg.addExecuteAppName("playback");
        bridgeMsg.addExecuteAppArg(BASE_DIR + "/outbound/src/main/resources/test.wav");
        handler.sendSyncMultiLineCommand(ctx.getChannel(), bridgeMsg.getMsgLines());
    }
}
