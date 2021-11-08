package cn.ch3nnn.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * spring容器加载完自动监听 初始化线路分配数据到缓存
 *
 * @Author ChenTong
 * @Date 2021/11/1 09:53
 */
@Slf4j
@Component
public class InitCacheLineDataRunner implements CommandLineRunner {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void run(String... args) throws Exception {
        log.info("Start Cache LineData ....");
        // TODO 初始化加载线路数据
        // String path = "src/main/resources/LineTest.json";
        // final String jsonData = JsonUtil.readJsonFile(path);
        // final LineDataDto parse = JSON.parseObject(jsonData, LineDataDto.class);

    }
}
