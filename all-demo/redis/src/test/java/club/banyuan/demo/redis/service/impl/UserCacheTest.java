package club.banyuan.demo.redis.service.impl;


import club.banyuan.demo.redis.user.JacksonUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserCacheTest {

    @Autowired
    private RedisCacheServiceImpl redisCacheService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Autowired
    JacksonUser jacksonUser;
    @Test
    public void cacheUser(){
        JacksonUser jacksonUser = new JacksonUser("admin","123456");
        redisCacheService.set("user", jacksonUser);
        JacksonUser user =  redisCacheService.get("user");
        Assert.assertEquals(user,jacksonUser);
    }

    @Before
    public void flushdb(){
        redisTemplate.delete(redisTemplate.keys("*"));
    }

    @Test
    public void cacheable(){
        JacksonUser user = jacksonUser.jacksonUser();
        JacksonUser user1 = jacksonUser.jacksonUser();
        JacksonUser admin = userService.getUser("admin");
        JacksonUser admin1 = userService.getUser("admin");
        Assert.assertEquals(user,user1);
        Assert.assertEquals(admin,admin1);
    }

    @Test
    public void cachePut(){
        JacksonUser admin = userService.getUser("admin");
        JacksonUser admin1 = userService.putUser("admin","111111");
        Assert.assertNotEquals(admin,admin1);

    }

    @Test
    public void delete(){
        JacksonUser admin = userService.getUser("admin");
        JacksonUser admin1 = userService.getUser("admin");
        Assert.assertEquals(admin,admin1);
        userService.deleteUser("admin");
        JacksonUser admin2 = userService.getUser("admin");
        Assert.assertNotEquals(admin,admin2);
    }


}
