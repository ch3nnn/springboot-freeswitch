package cn.ch3nnn.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Author ChenTong
 * @Date 2021/10/28 22:35
 */
public class JsonUtils {


    /**
     * 读取json文件
     *
     * @param jsonFilePath 文件路径
     * @return 文件内容数据
     */
    public static String readJsonFile(String jsonFilePath) {
        StringBuilder jsonStr = new StringBuilder();
        try {
            String lineTxt;
            final FileReader fileReader = new FileReader(jsonFilePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((lineTxt = bufferedReader.readLine()) != null) {
                jsonStr.append(lineTxt);
            }
            // 格式化json存在换行符需要替换
            return jsonStr.toString().replaceAll("[ \n\r]","");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }




}
