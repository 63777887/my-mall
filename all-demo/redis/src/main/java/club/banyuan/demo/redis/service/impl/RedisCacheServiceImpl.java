package club.banyuan.demo.redis.service.impl;

import club.banyuan.demo.redis.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@EnableCaching
public class RedisCacheServiceImpl implements CacheService {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    @SuppressWarnings("unchecked")      //转换异常
    public <T> T get(String key) {

        return (T)redisTemplate.opsForValue().get(key);
    }

    @Override
    public void setExpire(String key, Long sec) {
        redisTemplate.expire(key, sec, TimeUnit.SECONDS);
    }

    @Override
    public Long getExpireSec(String key) {
        return redisTemplate.getExpire(key);
    }
}
