package club.banyuan.zgMallMgt.service.impl;

import club.banyuan.zgMallMgt.service.imp.RedisCacheServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JacksonDemoTest {
    @Autowired
    private RedisCacheServiceImpl cacheService;
    @Test
    public void jacksonSerialize() throws JsonProcessingException {



    }


    @Test
    public void getSetTest(){
        cacheService.set("key","value");
    }

    @Test
    public void jacksonDeserialize() throws IOException {

    }
}
