package cn.ch3nnn.config;

import java.io.File;
import java.io.IOException;

/**
 * @Author ChenTong
 * @Date 2021/11/8 10:18
 */
public class OutboundServerConfig {

    /**
     * 项目基础路径
     */
    public static String BASE_DIR;

    static {
        try {
            BASE_DIR = new File("").getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
