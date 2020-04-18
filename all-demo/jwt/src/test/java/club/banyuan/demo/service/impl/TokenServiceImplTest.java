package club.banyuan.demo.service.impl;

import club.banyuan.demo.jwt.service.TokenService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import javax.xml.bind.DatatypeConverter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;

//@SpringBootTest
//@RunWith(SpringRunner.class)
public class TokenServiceImplTest {
//    private TokenService tokenService = new TokenServiceImpl();
    @Autowired
    TokenService tokenService;

//    @Before
//    public void initFiled() {
//        ReflectionTestUtils.setField(tokenService, "EXPIRE_SEC", 3600);
//        ReflectionTestUtils.setField(tokenService, "SECRET_KEY", "banyuan");
//    }

    @Test
    public void generate() {
        String subject = "admin";
        String token = tokenService.generateToken(subject);
        Assert.assertEquals(subject, tokenService.parseSubject(token));
    }

    @Test
    public void testExpired() {
        String subject = "test";
        ReflectionTestUtils.setField(tokenService, "EXPIRE_SEC", 1);
        String token = tokenService.generateToken(subject);
        assertTrue(tokenService.isExpire(token));
        assertTrue(tokenService.getExpireSec(token) <= 0);
    }

    // TODO
    @Test
    public void refreshExpiredDateTest() {
        String token = tokenService.generateToken("test");
        String token1 = tokenService.refreshToken(token);
        assertTrue(tokenService.getExpireSec(token) <= tokenService.getExpireSec(token1));
    }

    // TODO
    @Test
    public void generateTokenFromMapTest() {
        String token = tokenService.generateToken("test");
        Map<String, Object> map = tokenService.parseMap(token);
        tokenService.generateToken(map);
    }

    @Test
    public void encodeSecret() {
        String encoded = DatatypeConverter
                .printBase64Binary(DatatypeConverter.parseBase64Binary("banyuan"));
        System.out.println(encoded);
    }

    @Test
    public void lamda(){
        List<String> words= Arrays.asList("hello","word");
        words.stream()
                .map(w->w.split(""))
                .forEach(System.out::println);
        words.sort(String.CASE_INSENSITIVE_ORDER);  //大小写不敏感的排序

        words.sort(Comparator.naturalOrder());  //自然排序
//
//        words.sort(Comparator.comparing(USER::getName).reversed()   //先按照名字正序
//                .thenComparingInt(USER::age).reversed());       //然后按照年龄倒序  reversed倒叙对之前所有排序的有效
//        System.out.println(words);

        words.stream()
                .flatMap(w -> Arrays.stream(w.split("")))   //对多重数组惊醒操作
                .map(w->w+"a")      //对每个成员进行便利操作
                .filter(s -> s.contains("l"))   //过滤
                .skip(2)//跳过2个
                .limit(2)//选择两个
                .distinct()//去重
                .sorted()//排序
                .forEach(System.out::println);
    }
}
