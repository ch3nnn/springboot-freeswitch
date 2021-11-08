package cn.ch3nnn.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 当前主叫号可用线路数量
 *
 * @Author ChenTong
 * @Date 2021/11/4 14:36
 */
public class LineCountParam {

    /**
     * 授权 id
     */
    @JSONField(name = "appId")
    private String appId;

    /**
     * 授权 key(已加密过的 key)
     */
    @JSONField(name = "appKey")
    private String appKey;

    /**
     * 主叫号码即电话线路的号码  (请求 caller 下面线路号码为空闲线路)
     */
    @JSONField(name = "caller")
    private String caller;

    /**
     * 时间戳，精确到毫秒（1550645131000）
     */
    @JSONField(name = "timeStamp")
    private String timeStamp;


    /**
     * 按顺序拼接字符串后 md5 加密
     */
    @JSONField(name = "sign")
    private String sign;


}
