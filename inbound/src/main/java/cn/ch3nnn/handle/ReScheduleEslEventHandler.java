/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.ch3nnn.handle;

import link.thingscloud.freeswitch.esl.InboundClient;
import link.thingscloud.freeswitch.esl.constant.EventNames;
import link.thingscloud.freeswitch.esl.spring.boot.starter.annotation.EslEventName;
import link.thingscloud.freeswitch.esl.spring.boot.starter.handler.AbstractEslEventHandler;
import link.thingscloud.freeswitch.esl.transport.event.EslEvent;
import link.thingscloud.spring.boot.common.aop.Logging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@EslEventName({EventNames.CHANNEL_HANGUP, EventNames.CHANNEL_HANGUP_COMPLETE})
@Component
public class ReScheduleEslEventHandler extends AbstractEslEventHandler {

    @Autowired
    private InboundClient inboundClient;

    @Logging
    @Override
    public void handle(String addr, EslEvent event) {
        log.info("ReScheduleEslEventHandler handle addr[{}] EslEvent[{}].", addr, event);
        log.info("{}", inboundClient);
        // EslMessage eslMessage = inboundClient.sendSyncApiCommand(addr, "version", null);
        // log.info("{}", EslHelper.formatEslMessage(eslMessage));
    }
}
