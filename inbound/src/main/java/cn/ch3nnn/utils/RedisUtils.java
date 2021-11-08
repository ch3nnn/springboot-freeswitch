package cn.ch3nnn.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author chentong
 */
@Component
@Slf4j
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public boolean expire(String key, long expire) {
        try {
            // 这儿没有ops什么的是因为每种数据类型都能设置过期时间
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            log.error("redis set key expire exception:" + e);
            return false;
        }
    }

    /*hash数据类型方法 opsForHash 表示是操作字符串类型*/

    /**
     * @param key   健
     * @param field 属性
     * @param value 值
     * @return
     */
    public boolean hset(String key, String field, Object value) {
        try {
            redisTemplate.opsForHash().put(key, field, value);
            return true;
        } catch (Exception e) {
            log.error("redis hset eror,key:{},field:{},value:{}", key, field, value);
            return false;
        }
    }

    /**
     * @param key
     * @param field
     * @param value
     * @param seconds(秒) 过期时间
     * @return
     */
    public boolean hset(String key, String field, Object value, long seconds) {
        try {
            redisTemplate.opsForHash().put(key, field, value);
            // 调用通用方法设置过期时间
            expire(key, seconds);
            return true;
        } catch (Exception e) {
            log.error("redis hset and expire eror,key:{},field:{},value:{},exception:{}", key, field, value, e);
            return false;
        }
    }

    /**
     * 获取key中field属性的值
     *
     * @param key
     * @param field
     * @return
     */
    public Object hget(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 获取key中多个属性的键值对，这儿使用map来接收
     *
     * @param key
     * @param fields
     * @return
     */
    public Map<String, Object> hmget(String key, String... fields) {
        Map<String, Object> map = new HashMap<>(10);
        for (String field : fields) {
            map.put(field, hget(key, field));
        }
        return map;
    }

    /**
     * @param key 获得该key下的所有键值对
     * @return
     */
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * @param key 键
     * @param map 对应多个键值
     * @return
     */
    public boolean hmset(String key, Map<Object, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            log.error("redis hmset eror,key:{},value:{},exception:{}", key, map, e);
            return false;
        }
    }


    /**
     * @param key     键
     * @param map     对应多个键值
     * @param seconds 过期时间(秒)
     * @return
     */
    public boolean hmset(String key, Map<String, Object> map, long seconds) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            expire(key, seconds);
            return true;
        } catch (Exception e) {
            log.error("redis hmset eror,key:{},value:{},expireTime,exception:{}", key, map, seconds, e);
            return false;
        }
    }

    /**
     * 删除key中的属性
     *
     * @param key
     * @param fields
     */
    public void hdel(String key, Object... fields) {
        redisTemplate.opsForHash().delete(key, fields);
    }

    /**
     * 判断key中是否存在某属性
     *
     * @param key
     * @param field
     * @return
     */
    public boolean hHashKey(String key, String field) {
        return redisTemplate.opsForHash().hasKey(key, field);
    }

}