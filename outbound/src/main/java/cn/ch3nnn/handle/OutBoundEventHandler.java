package cn.ch3nnn.handle;

import cn.ch3nnn.OutboundHandler;
import org.freeswitch.esl.client.transport.event.EslEvent;
import org.jboss.netty.channel.ChannelHandlerContext;

/**
 * 出站事件处理程序
 *
 * @Author ChenTong
 * @Date 2021/11/3 15:05
 */
public interface OutBoundEventHandler {

    /**
     * 处理事件方法
     *
     * @param ctx     通道处理程序上下文
     * @param event   事件对象
     * @param handler 出站处理器 (可以获取发送命令方法)
     */
    void handle(ChannelHandlerContext ctx, EslEvent event, OutboundHandler handler);

}
