package club.banyuan.demo.authorization.service;

import club.banyuan.demo.authorization.dto.AdminLoginReq;
import club.banyuan.demo.authorization.dto.AdminLoginResp;
import club.banyuan.demo.jwt.service.TokenService;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class impl {

    @Autowired
    private AdminService adminService;

    @Autowired
    private TokenService tokenService;

    @Test
    public void LoginTest(){
        AdminLoginReq adminLoginReq = new AdminLoginReq();
        adminLoginReq.setUsername("test");
        adminLoginReq.setPassword("banyuan");

        AdminLoginResp login = adminService.login(adminLoginReq);
        String token = login.getToken();

        Assert.isTrue(StrUtil.isNotBlank(token));

        Assert.isTrue(Long.parseLong(tokenService.parseSubject(token))>0);
    }

}
