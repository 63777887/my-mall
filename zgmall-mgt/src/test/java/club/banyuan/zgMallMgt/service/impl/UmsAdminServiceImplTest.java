package club.banyuan.zgMallMgt.service.impl;

import club.banyuan.zgMallMgt.dao.PmsProductDao;
import club.banyuan.zgMallMgt.dao.SmsHomeNewProductDao;
import club.banyuan.zgMallMgt.dao.entity.PmsProduct;
import club.banyuan.zgMallMgt.dto.AdminInfoResp;
import club.banyuan.zgMallMgt.dto.AdminLoginReq;
import club.banyuan.zgMallMgt.dto.AdminLoginResp;
import club.banyuan.zgMallMgt.service.AdminService;
import club.banyuan.zgMallMgt.service.TokenService;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UmsAdminServiceImplTest {

  @Autowired
  private AdminService adminService;

  @Autowired
  private TokenService tokenService;
  @Autowired
  private PmsProductDao pmsProductDao;
  @Autowired
  private SmsHomeNewProductDao smsHomeNewProductDao;


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
//    AdminInfoResp info = adminService.getInfo(3);
//    Assert.assertTrue(CollUtil.isNotEmpty(info.getMenus()));
//    Assert.assertTrue(CollUtil.isNotEmpty(info.getRoles()));
//    PmsProduct pmsProduct = pmsProductDao.selectByPrimaryKey(90L);
//    System.out.println(pmsProductDao.deleteByPrimaryKey(90L));
//    System.out.println(ObjectUtil.isEmpty(pmsProduct));
    ArrayList<Long> integers = new ArrayList<>();
    integers.add(8L);
    smsHomeNewProductDao.updateRecommendStatusByIds(integers, 1);
  }
}
