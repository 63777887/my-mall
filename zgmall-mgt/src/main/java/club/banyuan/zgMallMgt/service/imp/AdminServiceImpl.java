package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.common.ReqFailException;
import club.banyuan.zgMallMgt.common.ReqFailMessage;
import club.banyuan.zgMallMgt.dao.UmsAdminDao;
import club.banyuan.zgMallMgt.dao.UmsMenuDao;
import club.banyuan.zgMallMgt.dao.UmsRoleDao;
import club.banyuan.zgMallMgt.dao.entity.*;
import club.banyuan.zgMallMgt.dto.*;
import club.banyuan.zgMallMgt.security.AdminUserDetails;
import club.banyuan.zgMallMgt.security.ResourceConfigAttribute;
import club.banyuan.zgMallMgt.service.AdminService;
import club.banyuan.zgMallMgt.service.CacheKey;
import club.banyuan.zgMallMgt.service.TokenService;
import club.banyuan.zgMallMgt.service.UmsResourceService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AdminServiceImpl implements AdminService {


    @Resource
    private UmsAdminDao umsAdminDao;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UmsResourceService umsResourceService;

    @Autowired
    private UmsMenuDao umsMenuDao;
    @Autowired
    private UmsRoleDao umsRoleDao;

    private static final String SCHEMA="Bearer";
    private static final String TOKEN_HEAD_KEY="Authorization";


    @Override
    public AdminLoginResp login(AdminLoginReq adminLoginReq) {
        AdminLoginResp adminLoginResp = new AdminLoginResp();
        String username = adminLoginReq.getUsername();
        UmsAdminExample example = new UmsAdminExample();
        UmsAdminExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<UmsAdmin> umsAdmins = umsAdminDao.selectByExample(example);
        if (CollUtil.isEmpty(umsAdmins)||
                !passwordEncoder.matches(adminLoginReq.getPassword(), umsAdmins.get(0).getPassword())){

            throw new ReqFailException(ReqFailMessage.LOGIN_FAIL);
        }

        adminLoginResp.setToken(tokenService.generateToken(umsAdmins.get(0).getId().toString()));
        adminLoginResp.setTokenHead(SCHEMA);
        return adminLoginResp;
    }

//    private List<UmsAdmin> getUmsAdminByUsername(String username) {
//        UmsAdminExample example = new UmsAdminExample();
//        UmsAdminExample.Criteria criteria = example.createCriteria();
//        criteria.andUsernameEqualTo(username);
//        return umsAdminDao.selectByExample(example);
//    }

    @Override
    public UserDetails getUserDetailsByToken(String token) {
        long adminId = Long.parseLong(tokenService.parseSubject(token));
        UmsAdmin umsAdmin = umsAdminDao.selectByPrimaryKey(adminId);
        if (umsAdmin==null){
            throw new ReqFailException(ReqFailMessage.USER_NOT_EXIST);
        }
        List<UmsResource> resources = umsResourceService.getResourcesByAdminId(adminId);
        List<ResourceConfigAttribute> grantedAuthorities=new ArrayList<>();
        if (CollUtil.isNotEmpty(resources)){
            resources.forEach(t-> grantedAuthorities.add(new ResourceConfigAttribute(t)));
        }

        return new AdminUserDetails(umsAdmin,grantedAuthorities);
    }

    @Cacheable(value = CacheKey.MALL_ADMIN,key = "#adminId")
    @Override
    public AdminInfoResp getInfo(long adminId) {
        UmsAdmin umsAdmin = umsAdminDao.selectByPrimaryKey(adminId);
        AdminInfoResp adminInfoResp = new AdminInfoResp();
        adminInfoResp.setIcon(umsAdmin.getIcon());
        adminInfoResp.setUsername(umsAdmin.getUsername());

        List<UmsRole> umsRoles = umsRoleDao.selectByAdminId(adminId);
        if (CollUtil.isEmpty(umsRoles)){
            throw new ReqFailException(ReqFailMessage.ROLE_IS_NULL);
        }
        List<UmsMenu> umsMenus = umsMenuDao.selectByRoleIds(umsRoles.stream().map(UmsRole::getId).collect(Collectors.toList()));
        adminInfoResp.setMenus(umsMenus.stream().map(t->{
            AdminMenusResp adminMenusResp = new AdminMenusResp();
            BeanUtil.copyProperties(t,adminMenusResp);
            return adminMenusResp;
        }).collect(Collectors.toList()));

        adminInfoResp.setRoles(umsRoles.stream().map(UmsRole::getName).collect(Collectors.toList()));
        return adminInfoResp;
    }

    @Override
    public List<UmsAdminResp> list(Integer pageNum, Integer pageSize, String keyword) {

        PageHelper.startPage(pageNum,pageSize);
        UmsAdminExample umsAdminExample = new UmsAdminExample();
        UmsAdminExample.Criteria criteria = umsAdminExample.createCriteria();
        UmsAdminExample.Criteria criteria1 = umsAdminExample.createCriteria();
        if (StrUtil.isNotBlank(keyword)){
            criteria.andNickNameLike(StrUtil.concat(true, "%", keyword, "%"));
            criteria1.andUsernameLike(StrUtil.concat(true, "%", keyword, "%"));
        }
        umsAdminExample.or(criteria1);
        List<UmsAdmin> umsAdmins = umsAdminDao.selectByExample(umsAdminExample);
        return umsAdmins.stream().distinct().map(t->{
            UmsAdminResp umsAdminResp = new UmsAdminResp();
            BeanUtil.copyProperties(t, umsAdminResp);
            return umsAdminResp;
        }).collect(Collectors.toList());
    }

    @CacheEvict(value = CacheKey.MALL_ADMIN,allEntries = true)
    public void updateMenu(){

    }
}
