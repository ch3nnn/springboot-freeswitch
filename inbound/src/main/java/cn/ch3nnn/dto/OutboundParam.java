package cn.ch3nnn.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 机器人外呼发起
 *
 * @Author ChenTong
 * @Date 2021/10/28 15:12
 */

@NoArgsConstructor
@Data
public class OutboundParam {


    /**
     * 主叫号码即电话线路的号码
     */
    @JSONField(name = "caller")
    private String caller;
    /**
     * 授权id
     */
    @JSONField(name = "appId")
    private String appId;
    /**
     * 授权 key(已加密过的 key)
     */
    @JSONField(name = "appKey")
    private String appKey;
    /**
     * 语义模板xml
     */
    @JSONField(name = "xmlFileData")
    private String xmlFileData;
    /**
     * 被叫号码即被呼叫的用户的号码
     */
    @JSONField(name = "callee")
    private String callee;
    /**
     * 模板名称
     */
    @JSONField(name = "xmlFileName")
    private String xmlFileName;
    /**
     * 模板uuid(业务uuid)
     */
    @JSONField(name = "uuid")
    private String uuid;
    /**
     * 按顺序拼接字符串后 md5 加密
     */
    @JSONField(name = "sign")
    private String sign;

    /**
     * 时间戳，精确到毫秒（1550645131000）
     */
    @JSONField(name = "timeStamp")
    private String timeStamp;

}
