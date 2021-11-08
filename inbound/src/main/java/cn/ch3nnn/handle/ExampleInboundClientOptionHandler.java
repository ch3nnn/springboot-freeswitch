package cn.ch3nnn.handle;

import link.thingscloud.freeswitch.esl.inbound.option.InboundClientOption;
import link.thingscloud.freeswitch.esl.spring.boot.starter.handler.AbstractInboundClientOptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class ExampleInboundClientOptionHandler extends AbstractInboundClientOptionHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void intercept(InboundClientOption inboundClientOption) {
    }
}
