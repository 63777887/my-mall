package club.banyuan.demo.redis.service;

import club.banyuan.demo.redis.user.JacksonUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JacksonDemoTest {

    @Test
    public void jacksonSerialize() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String admin = objectMapper.writer().writeValueAsString(new JacksonUser("admin", "123456"));
        System.out.println(admin);
    }

    @Test
    public void jacksonDeserialize() throws IOException {
        String s="{\"username\":\"admin\",\"password\":\"123456\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonUser jacksonUser = objectMapper.readValue(s, JacksonUser.class);
        System.out.println(jacksonUser);
    }
}
