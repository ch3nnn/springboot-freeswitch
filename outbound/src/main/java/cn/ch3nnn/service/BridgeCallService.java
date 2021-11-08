package cn.ch3nnn.service;

import cn.ch3nnn.OutboundHandler;
import org.freeswitch.esl.client.transport.SendMsg;
import org.freeswitch.esl.client.transport.event.EslEvent;
import org.freeswitch.esl.client.transport.message.EslHeaders;
import org.freeswitch.esl.client.transport.message.EslMessage;
import org.jboss.netty.channel.Channel;

import java.util.ArrayList;
import java.util.List;

/**
 * 例子: 桥接呼叫
 *
 * @Author ChenTong
 * @Date 2021/11/3 23:39
 */
public class BridgeCallService {

    public void bridgeCall(Channel channel, EslEvent event, OutboundHandler handler) {
        List<String> extNums = new ArrayList<>(2);
        extNums.add("1000");
        extNums.add("1010");
        // 随机找1个目标（注：这里只是演示目的，真正分配时，应该考虑到客服的忙闲情况，通常应该分给最空闲的客服）
        // String destNumber = extNums.get((int) Math.abs(System.currentTimeMillis() % 2));
        String destNumber = "1010";

        SendMsg bridgeMsg = new SendMsg();
        bridgeMsg.addCallCommand("execute");
        bridgeMsg.addExecuteAppName("bridge");
        bridgeMsg.addExecuteAppArg("user/" + destNumber);

        // 同步发送bridge命令接通
        EslMessage response = handler.sendSyncMultiLineCommand(channel, bridgeMsg.getMsgLines());
        if (response.getHeaderValue(EslHeaders.Name.REPLY_TEXT).startsWith("+OK")) {
            String originCall = event.getEventHeaders().get("Caller-Destination-Number");
            System.out.println(originCall + " bridge to " + destNumber + " successful");
        } else {
            System.out.println("Call bridge failed: " + response.getHeaderValue(EslHeaders.Name.REPLY_TEXT));
        }
    }


}
