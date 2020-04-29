package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.common.ReqFailException;
import club.banyuan.zgMallMgt.common.ResponsePage;
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
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static club.banyuan.zgMallMgt.common.FailReason.*;


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

            throw new ReqFailException(UMS_ADMIN_USER_NOT_VALID);
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
            throw new ReqFailException(UMS_ADMIN_USER_NOT_EXIST);
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
            throw new ReqFailException(UMS_ADMIN_ROLE_EMPTY);
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
    public ResponsePage list(Integer pageNum, Integer pageSize, String keyword) {
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
        PageInfo<UmsAdmin> pageInfo = new PageInfo<>(umsAdmins);
        List<UmsAdminResp> collect = umsAdmins.stream().distinct().map(t -> {
            UmsAdminResp umsAdminResp = new UmsAdminResp();
            BeanUtil.copyProperties(t, umsAdminResp);
            return umsAdminResp;
        }).collect(Collectors.toList());
        return ResponsePage.setPages(pageInfo, collect);
    }

    @Override
    public List<UmsRoleResp> getRoleByAdminId(Long adminId) {

        List<UmsRole> umsRoles = umsRoleDao.selectByAdminId(adminId);

        return umsRoles.stream().map(t->{
            UmsRoleResp umsRoleResp = new UmsRoleResp();
            BeanUtil.copyProperties(t, umsRoleResp);
            return umsRoleResp;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Integer updateRole(Long adminId, List<Long> roleIds) {
        UmsAdmin umsAdmin = umsAdminDao.selectByPrimaryKey(adminId);
        if (ObjectUtil.isEmpty(umsAdmin)) {
            throw new ReqFailException(UMS_ADMIN_ROLE_EMPTY);
        }
        umsRoleDao.deleteAdminROleRelationByAdminId(adminId);
        umsRoleDao.insertAdminRoleRelationByAdminId(adminId,roleIds);
        return roleIds.size();
    }


    @Override
    public Long update(UmsAdmin admin, Long adminId) {
        UmsAdmin umsAdmin = umsAdminDao.selectByPrimaryKey(adminId);
        if (ObjectUtil.isEmpty(umsAdmin)) {
            throw new ReqFailException(UMS_ADMIN_ROLE_EMPTY);
        }
        admin.setId(adminId);
        umsAdminDao.updateByPrimaryKey(admin);
        return adminId;
    }

    @Override
    public Long delete(Long adminId) {
        List<UmsRole> umsRoles = umsRoleDao.selectByAdminId(adminId);
        if (CollUtil.isEmpty(umsRoles)) {
            throw new ReqFailException(UMS_ADMIN_ROLE_EMPTY);
        }
        umsAdminDao.deleteByPrimaryKey(adminId);
        return adminId;
    }

    @Override
    public UmsAdminResp register(UmsAdmin admin) {
        List<UmsAdmin> umsAdmins = umsAdminDao.selectAll();
        umsAdmins.forEach(t->{
            if (t.getUsername().equals(admin.getUsername())){
                throw new ReqFailException(UMS_ADMIN_NAME_DUPLICATE);
            }
        });
        admin.setCreateTime(new Date());
        umsAdminDao.insert(admin);
        UmsAdminResp umsAdminResp = new UmsAdminResp();
        BeanUtil.copyProperties(admin, umsAdminResp);
        return umsAdminResp;
    }

    @Override
    public Integer updateStatus(Integer status, Long adminId) {
        UmsAdmin umsAdmin = umsAdminDao.selectByPrimaryKey(adminId);
        if (ObjectUtil.isEmpty(umsAdmin)) {
            throw new ReqFailException(UMS_ADMIN_ROLE_EMPTY);
        }
        return umsAdminDao.updateStatusByAdminId(status,adminId);
    }

    @CacheEvict(value = CacheKey.MALL_ADMIN,allEntries = true)
    public void updateMenu(){

    }
}
