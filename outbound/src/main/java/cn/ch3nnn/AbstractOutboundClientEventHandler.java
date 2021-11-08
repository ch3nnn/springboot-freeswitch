/*
 * Copyright 2010 david varnes.
 *
 * Licensed under the Apache License, version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.ch3nnn;

import org.freeswitch.esl.client.outbound.AbstractOutboundClientHandler;
import org.freeswitch.esl.client.transport.CommandResponse;
import org.freeswitch.esl.client.transport.message.EslMessage;
import org.jboss.netty.channel.ChannelHandlerContext;


/**
 * 抽象出站客户端事件处理程序
 */
public abstract class AbstractOutboundClientEventHandler extends AbstractOutboundClientHandler {


    /**
     * 注册监听事件
     *
     * @param ctx    通道处理程序上下文
     * @param format 格式
     * @param events 事件名称
     * @return
     */
    public CommandResponse setEventSubscriptions(ChannelHandlerContext ctx, String format, String events) {
        if (!format.equals("plain")) {
            throw new IllegalStateException("Only 'plain' event format is supported at present");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("event ");
        sb.append(format);
        if (events != null && !events.isEmpty()) {
            sb.append(' ');
            sb.append(events);
        }
        EslMessage response = sendSyncSingleLineCommand(ctx.getChannel(), sb.toString());

        return new CommandResponse(sb.toString(), response);
    }


    /**
     * 添加事件过滤器
     *
     * @param ctx           通道处理程序上下文
     * @param eventHeader   过滤名称
     * @param valueToFilter 过滤值
     * @return
     */
    public CommandResponse addEventFilter(ChannelHandlerContext ctx, String eventHeader, String valueToFilter) {
        StringBuilder sb = new StringBuilder();
        sb.append("filter ");
        if (eventHeader != null && !eventHeader.isEmpty()) {
            sb.append(eventHeader);
        }
        if (valueToFilter != null && !valueToFilter.isEmpty()) {
            sb.append(' ');
            sb.append(valueToFilter);
        }

        EslMessage response = sendSyncSingleLineCommand(ctx.getChannel(), sb.toString());
        return new CommandResponse(sb.toString(), response);
    }

}
