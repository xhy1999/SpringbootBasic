package com.xhy.common.util;

import com.xhy.common.constant.CmRedisConst;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.HashMap;

/**
 * Redis工具类
 * @author xuehy
 * @since 2022/12/8
 */
@Component
public class RedisHelper {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    //增加<Key,HashMap>
    public void addHashMap(String key, HashMap<String, String> map) {
        stringRedisTemplate.boundHashOps(key).putAll(map);
    }

    //通过Key获取字符串类型的值
    public String getHashMapValue(String key, String hKey) {
        return (String) stringRedisTemplate.boundHashOps(key).get(hKey);
    }

    //删除Key
    public void delKey(String key) {
        stringRedisTemplate.delete(key);
    }

    //设置过期时间(过期时间 = 当前时间 + duration)
    public void setExpireTime(String key) {
        setExpireTime(key, Duration.ofMinutes(CmRedisConst.TOKEN_EXPIRES_MINUTE));
    }
    public void setExpireTime(String key, Duration duration) {
        stringRedisTemplate.boundValueOps(key).expire(duration);
    }

}
