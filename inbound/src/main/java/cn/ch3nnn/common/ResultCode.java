package cn.ch3nnn.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * SpringBoot 统一响应格式
 *
 * @Author ChenTong
 * @Date 2021/8/27 13:55
 */
@Data
@AllArgsConstructor
public class ResultCode implements Serializable {

    private final static int SUCCESS_CODE = 1;
    private final static int ERROR_CODE = 0;
    private final static String SUCCESS_MESSAGE = "请求成功";
    private final static String ERROR_MESSAGE = "请求失败";

    private Integer code;
    private String message;
    private Object data;


    /**
     * 请求成功
     *
     * @return 视图模型实例
     */
    public static ResultCode success() {
        return success(SUCCESS_MESSAGE);
    }

    /**
     * 请求成功
     *
     * @param data 响应数据
     * @return 视图模型实例
     */
    public static ResultCode success(Object data) {
        return success(SUCCESS_MESSAGE, data);
    }

    /**
     * 请求成功
     *
     * @param message 响应信息
     * @return 视图模型实例
     */
    public static ResultCode success(String message) {
        return success(message, null);
    }

    /**
     * 请求成功
     *
     * @param message  响应信息
     * @param data 响应数据
     * @return 视图模型实例
     */
    public static ResultCode success(String message, Object data) {
        return new ResultCode(SUCCESS_CODE, message, data);
    }


    /**
     * 请求失败
     *
     * @return 视图模型实例
     */
    public static ResultCode error() {
        return error(ERROR_MESSAGE);
    }


    /**
     * 请求失败
     *
     * @param message 异常信息
     * @return 视图模型实例
     */
    public static ResultCode error(String message) {
        return error(message, null);
    }

    /**
     * 请求失败
     *
     * @param message  异常信息
     * @param data 响应数据
     * @return 视图模型实例
     */
    public static ResultCode error(String message, Object data) {
        return new ResultCode(ERROR_CODE, message, data);
    }


}
