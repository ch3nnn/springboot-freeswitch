package cn.ch3nnn;

import cn.ch3nnn.annotation.OutBoundEventName;
import cn.ch3nnn.utils.ClassUtil;
import org.freeswitch.esl.client.outbound.AbstractOutboundClientHandler;
import org.freeswitch.esl.client.outbound.AbstractOutboundPipelineFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 连接管道工厂类
 *
 * @Author ChenTong
 * @Date 2021/11/1 15:16
 */
public class PipelineFactory extends AbstractOutboundPipelineFactory {


    @Override
    protected AbstractOutboundClientHandler makeHandler() {

        final Map<String, List<Class<?>>> handlerTable = new HashMap<>(16);

        // 通过反射查找包下所有全类名
        String packageName = "cn.ch3nnn.handle";
        final List<Class<?>> classes = ClassUtil.getClasses(packageName);
        for (Class<?> clazz: classes){
            final OutBoundEventName eventName = clazz.getAnnotation(OutBoundEventName.class);
            if (eventName != null) for (String value : eventName.value()) {
                handlerTable.computeIfAbsent(value, k -> new ArrayList<>(4)).add(clazz);
            }
        }
        return new OutboundHandler(handlerTable);
    }
}
