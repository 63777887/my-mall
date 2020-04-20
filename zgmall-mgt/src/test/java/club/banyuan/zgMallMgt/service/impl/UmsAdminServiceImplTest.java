package club.banyuan.zgMallMgt.service.impl;

import club.banyuan.zgMallMgt.dto.AdminInfoResp;
import club.banyuan.zgMallMgt.dto.AdminLoginReq;
import club.banyuan.zgMallMgt.dto.AdminLoginResp;
import club.banyuan.zgMallMgt.service.AdminService;
import club.banyuan.zgMallMgt.service.TokenService;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UmsAdminServiceImplTest {

  @Autowired
  private AdminService adminService;

  @Autowired
  private TokenService tokenService;


  @Test
  public void loginTest() {
    AdminLoginReq adminLoginReq = new AdminLoginReq();
    adminLoginReq.setPassword("banyuan");
    adminLoginReq.setUsername("admin");

    AdminLoginResp loginResp = adminService.login(adminLoginReq);
    String token = loginResp.getToken();
    assertTrue(StrUtil.isNotBlank(token));

    assertTrue(Long.parseLong(tokenService.parseSubject(token)) > 0);

  }

  @Test
  public void infoTest(){
    AdminInfoResp info = adminService.getInfo(3);
    Assert.assertTrue(CollUtil.isNotEmpty(info.getMenus()));
    Assert.assertTrue(CollUtil.isNotEmpty(info.getRoles()));
  }
}
