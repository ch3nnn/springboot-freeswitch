package cn.ch3nnn;

import cn.ch3nnn.handle.OutBoundEventHandler;
import cn.ch3nnn.service.BridgeCallService;
import lombok.SneakyThrows;
import org.freeswitch.esl.client.transport.event.EslEvent;
import org.freeswitch.esl.client.transport.message.EslMessage;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;

import java.util.List;
import java.util.Map;

/**
 * 出站处理程序
 *
 * @Author ChenTong
 * @Date 2021/11/1 15:14
 */
public class OutboundHandler extends AbstractOutboundClientEventHandler {

    private final Map<String, List<Class<?>>> handlerTable;

    public OutboundHandler(Map<String, List<Class<?>>> handlerTable) {
        this.handlerTable = handlerTable;
    }

    /**
     * 通道已连接
     *
     * @param ctx 通道处理程序上下文
     * @param e   通道状态事件对象
     */
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
        this.log.debug("Received new connection from server, sending connect message");
        EslMessage response = this.sendSyncSingleLineCommand(ctx.getChannel(), "connect");
        EslEvent channelDataEvent = new EslEvent(response, true);
        this.handleConnectResponse(ctx, channelDataEvent);

        // 注册监听事件
        setEventSubscriptions(ctx, "plain", "PLAYBACK_START");
        setEventSubscriptions(ctx, "plain", "PLAYBACK_STOP");
        setEventSubscriptions(ctx, "plain", "CHANNEL_HANGUP");
        setEventSubscriptions(ctx, "plain", "CHANNEL_HANGUP_COMPLETE");
        setEventSubscriptions(ctx, "plain", "RECORD_START");
        setEventSubscriptions(ctx, "plain", "RECORD_STOP");
        setEventSubscriptions(ctx, "plain", "CHANNEL_ANSWER");
        /*
         TODO 加上下面两个代码 监听不到事件 (过滤指定通话)
         final Map<String, String> eventHeaders = channelDataEvent.getEventHeaders();
         final CommandResponse commandResponse = addEventFilter(ctx, "Unique-ID", eventHeaders.get("Unique-ID"));
        */

    }


    /**
     * 处理连接响应
     *
     * @param ctx   通道处理程序上下文
     * @param event 事件对象
     */
    @Override
    protected void handleConnectResponse(ChannelHandlerContext ctx, EslEvent event) {
        System.out.println("Received connect response :" + event);
        System.out.println("EventName :" + event.getEventName());
        if (event.getEventName().equalsIgnoreCase("CHANNEL_DATA")) {
            // 初始连接的响应
            System.out.println("=======================  incoming channel data  =============================");
            System.out.println("Event-Date-Local: " + event.getEventDateLocal());
            System.out.println("Unique-ID: " + event.getEventHeaders().get("Unique-ID"));
            System.out.println("Channel-ANI: " + event.getEventHeaders().get("Channel-ANI"));
            System.out.println("Answer-State: " + event.getEventHeaders().get("Answer-State"));
            System.out.println("Caller-Destination-Number: " + event.getEventHeaders().get("Caller-Destination-Number"));
            System.out.println("=======================  = = = = = = = = = = =  =============================");

            // TODO 处理业务逻辑 读取wav文件 切割chunk wav文件 识别语音文件
            // 桥接电话
            new BridgeCallService().bridgeCall(ctx.getChannel(), event, this);

        } else {
            throw new IllegalStateException("Unexpected event after connect: [" + event.getEventName() + ']');
        }
    }

    /**
     * 处理 Esl 事件
     *
     * @param ctx   通道处理程序上下文
     * @param event 事件对象
     */
    @SneakyThrows
    @Override
    protected void handleEslEvent(ChannelHandlerContext ctx, EslEvent event) {
        final List<Class<?>> classes = handlerTable.get(event.getEventName());
        if (classes != null) for (Class<?> clazz : classes) {
            final OutBoundEventHandler instance = (OutBoundEventHandler) clazz.newInstance();
            try {
                // 执行每个事件任务
                instance.handle(ctx, event, this);
                log.info("Success EventHandle Name: {}", event.getEventName());
            } catch (Exception e) {
                log.error("Error EventHandle Name: {} -> {}", event.getEventName(), e.getMessage());
            }
        }
    }

    /**
     * 处理断线通知
     */
    @Override
    protected void handleDisconnectionNotice() {
        super.handleDisconnectionNotice();
        log.info("Received disconnection notice");
    }

}
