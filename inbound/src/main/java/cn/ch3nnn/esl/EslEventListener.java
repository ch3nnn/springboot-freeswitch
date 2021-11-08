package cn.ch3nnn.esl;

import link.thingscloud.freeswitch.esl.IEslEventListener;
import link.thingscloud.freeswitch.esl.InboundClient;
import link.thingscloud.freeswitch.esl.spring.boot.starter.annotation.EslEventName;
import link.thingscloud.freeswitch.esl.spring.boot.starter.handler.EslEventHandler;
import link.thingscloud.freeswitch.esl.transport.event.EslEvent;
import link.thingscloud.freeswitch.esl.util.ArrayUtils;
import link.thingscloud.freeswitch.esl.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Esl 事件监听器
 *
 * @Author ChenTong
 * @Date 2021/11/1 11:13
 */
@Slf4j
@Component
public class EslEventListener implements IEslEventListener, InitializingBean {

    @Autowired
    private InboundClient inboundClient;

    @Autowired
    private final List<EslEventHandler> eslEventHandlers = Collections.emptyList();

    private final Map<String, List<EslEventHandler>> handlerTable = new HashMap<>(16);

    private void handleEslEvent(String addr, EslEvent event) {
        String eventName = event.getEventName();
        List<EslEventHandler> handlers = handlerTable.get(eventName);
        if (!CollectionUtils.isEmpty(handlers)) {
            handlers.forEach(eventHandler -> eventHandler.handle(addr, event));
        }
    }

    @Override
    public void eventReceived(String addr, EslEvent event) {
        handleEslEvent(addr, event);
    }

    @Override
    public void backgroundJobResultReceived(String addr, EslEvent event) {
        handleEslEvent(addr, event);
    }

    @Override
    public void afterPropertiesSet() {
        log.info("IEslEventListener init ...");
        for (EslEventHandler eventHandler : eslEventHandlers) {
            EslEventName eventName = eventHandler.getClass().getAnnotation(EslEventName.class);
            if (eventName == null) {
                // FIXED : AOP
                eventName = eventHandler.getClass().getSuperclass().getAnnotation(EslEventName.class);
            }
            if (eventName == null || ArrayUtils.isEmpty(eventName.value())) {
                continue;
            }
            for (String value : eventName.value()) {
                if (StringUtils.isBlank(value)) {
                    continue;
                }
                log.info("IEslEventListener add EventName[{}], EventHandler[{}] ...", value, eventHandler.getClass());
                handlerTable.computeIfAbsent(value, k -> new ArrayList<>(4)).add(eventHandler);

            }
        }
        inboundClient.option().addListener(this);
    }
}

