package club.banyuan.zgMallMgt.service.impl;

import club.banyuan.zgMallMgt.dao.PmsProductCategoryAttributeRelationDao;
import club.banyuan.zgMallMgt.dao.PmsProductDao;
import club.banyuan.zgMallMgt.dao.entity.PmsProduct;
import club.banyuan.zgMallMgt.dto.AdminLoginReq;
import club.banyuan.zgMallMgt.dto.AdminLoginResp;
import club.banyuan.zgMallMgt.dto.AttrInfoResp;
import club.banyuan.zgMallMgt.service.AdminService;
import club.banyuan.zgMallMgt.service.TokenService;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PmsProductTest {

  @Autowired
  private AdminService adminService;

  @Autowired
  private TokenService tokenService;
  @Autowired
  PmsProductDao pmsProductDao;

  @Autowired
  PmsProductCategoryAttributeRelationDao pmsProductCategoryAttributeRelationDao;

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

    List<AttrInfoResp> attrInfoResps = pmsProductCategoryAttributeRelationDao.selectByProductCategoryId(1L);
    System.out.println(Arrays.toString(attrInfoResps.toArray()));
  }
  @Test
  public void test03(){
    String i= "sll";
    String m=null;
    System.out.println(i.equals(m));
  }
}
