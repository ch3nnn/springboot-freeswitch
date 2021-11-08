package cn.ch3nnn.controller;

import cn.ch3nnn.common.ResultCode;
import cn.ch3nnn.dto.OutboundParam;
import link.thingscloud.freeswitch.esl.InboundClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 外呼中心接口
 *
 * @Author ChenTong
 * @Date 2021/10/28 15:03
 */
@RestController
@RequestMapping("/callcenter/api")
public class OutboundController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private InboundClient inboundClient;


    /**
     * 机器人外呼发起
     *
     * @param outboundParam
     * @return
     */
    @RequestMapping(value = "/startOutbound", method = RequestMethod.POST)
    public ResultCode outBound(@RequestBody(required = false) OutboundParam outboundParam) {
        // 测试本地
        final String originate = inboundClient.sendAsyncApiCommand("127.0.0.1:8021", "originate", "user/1010 &echo outbound");
        return ResultCode.success(originate);

    }

    /**
     * 当前主叫号可用线路数量
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/currentLineCount", method = RequestMethod.GET)
    public ResultCode lineCount() {
        return ResultCode.success();

    }

}
