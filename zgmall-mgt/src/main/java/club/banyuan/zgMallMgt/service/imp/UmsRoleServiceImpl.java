package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.common.ReqFailException;
import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dao.UmsMenuDao;
import club.banyuan.zgMallMgt.dao.UmsResourceDao;
import club.banyuan.zgMallMgt.dao.UmsRoleDao;
import club.banyuan.zgMallMgt.dao.entity.UmsMenu;
import club.banyuan.zgMallMgt.dao.entity.UmsResource;
import club.banyuan.zgMallMgt.dao.entity.UmsRole;
import club.banyuan.zgMallMgt.dao.entity.UmsRoleExample;
import club.banyuan.zgMallMgt.dto.UmsMenuResp;
import club.banyuan.zgMallMgt.dto.UmsResourceResp;
import club.banyuan.zgMallMgt.dto.UmsRoleResp;
import club.banyuan.zgMallMgt.service.UmsRoleService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static club.banyuan.zgMallMgt.common.FailReason.*;

@Service
public class UmsRoleServiceImpl implements UmsRoleService {

    @Autowired
    private UmsRoleDao umsRoleDao;
    @Autowired
    private UmsResourceDao umsResourceDao;
    @Autowired
    private UmsMenuDao umsMenuDao;

    @Override
    public ResponsePage showList(Integer pageNum, Integer pageSize, String keyword) {
        PageHelper.startPage(pageNum,pageSize);
        UmsRoleExample umsRoleExample = new UmsRoleExample();
        if (keyword != null) {
            UmsRoleExample.Criteria criteria = umsRoleExample.createCriteria();
            criteria.andNameLike(StrUtil.concat(true, "%",keyword,"%"));
        }
        List<UmsRole> umsRoles = umsRoleDao.selectByExample(umsRoleExample);
        PageInfo<UmsRole> pageInfo = new PageInfo<>(umsRoles);
        List<UmsRoleResp> collect = umsRoles.stream().map(t -> {
            UmsRoleResp umsRoleResp = new UmsRoleResp();
            BeanUtil.copyProperties(t, umsRoleResp);
            return umsRoleResp;
        }).collect(Collectors.toList());
        return ResponsePage.setPages(pageInfo, collect);
    }

    @Override
    public List<UmsRoleResp> showAllList() {
        List<UmsRole> umsRoles = umsRoleDao.selectAll();
        return umsRoles.stream().map(t-> {
            UmsRoleResp umsRoleResp = new UmsRoleResp();
            BeanUtil.copyProperties(t, umsRoleResp);
            return umsRoleResp;
        }).collect(Collectors.toList());
    }

    @Override
    public Long createUmsRole(@RequestBody @Valid UmsRoleResp umsRoleResp) {
        UmsRoleExample umsRoleExample = new UmsRoleExample();
        umsRoleExample.createCriteria().andNameEqualTo(umsRoleResp.getName());

        if (umsRoleDao.countByExample(umsRoleExample) > 0) {
            throw new ReqFailException(UMS_ROLE_NAME_DUPLICATE);
        }
        UmsRole umsRole = new UmsRole();
        BeanUtil.copyProperties(umsRoleResp, umsRole);
        umsRole.setCreateTime(new Date());
        umsRole.setSort(0);
       return umsRole.getId();
    }

    @Override
    public Long deleteUmsRoleById(Long ids) {
        if (umsRoleDao.deleteByPrimaryKey(ids) <= 0) {
            throw new ReqFailException(UMS_ADMIN_ROLE_NOT_EXIST);
        }
        return ids;
    }

    @Override
    public Long updateUmsRole(UmsRoleResp umsRoleResp, Long roleId) {
        UmsRoleExample umsRoleExample = new UmsRoleExample();
        umsRoleExample.createCriteria().andNameEqualTo(umsRoleResp.getName())
                .andIdNotEqualTo(umsRoleResp.getId());
        if (umsRoleDao.countByExample(umsRoleExample) > 0) {
            throw new ReqFailException(UMS_ROLE_NAME_DUPLICATE);
        }
        UmsRole umsRole = new UmsRole();
        umsRole.setId(roleId);
        BeanUtil.copyProperties(umsRoleResp, umsRole);
        return roleId;
    }

    @Override
    public List<UmsResourceResp> showListResource(Long roleId) {
        UmsRoleExample umsRoleExample = new UmsRoleExample();
        umsRoleExample.createCriteria().andIdEqualTo(roleId);
        if (umsRoleDao.countByExample(umsRoleExample) <= 0) {
            throw new ReqFailException(UMS_ADMIN_ROLE_NOT_EXIST);
        }
        List<UmsResource> umsResources = umsResourceDao.selectResourcesByRoleId(roleId);
        return umsResources.stream().map(t->{
            UmsResourceResp umsResourceResp = new UmsResourceResp();
            BeanUtil.copyProperties(t, umsResourceResp);
            return umsResourceResp;
        }).collect(Collectors.toList());
    }

    @Override
    public List<UmsMenuResp> listMenu(Long roleId) {
        UmsRoleExample umsRoleExample = new UmsRoleExample();
        umsRoleExample.createCriteria().andIdEqualTo(roleId);
        if (umsRoleDao.countByExample(umsRoleExample) <= 0) {
            throw new ReqFailException(UMS_ADMIN_ROLE_NOT_EXIST);
        }
        List<UmsMenu> umsMenus = umsMenuDao.selectByRoleIds(Collections.singletonList(roleId));
        return umsMenus.stream().map(t->{
            UmsMenuResp umsMenuResp = new UmsMenuResp();
            BeanUtil.copyProperties(t, umsMenuResp);
            return umsMenuResp;
        }).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Integer allocMenu(Long roleId, List menuIds) {
        UmsRoleExample umsRoleExample = new UmsRoleExample();
        umsRoleExample.createCriteria().andIdEqualTo(roleId);
        if (umsRoleDao.countByExample(umsRoleExample) <= 0) {
            throw new ReqFailException(UMS_ADMIN_ROLE_NOT_EXIST);
        }
        if (!umsMenuDao.selectAll().containsAll(menuIds)){
            throw new ReqFailException(UMS_ROLE_MENU_REL_ILLEGAL);
        }
        umsRoleDao.deleteRoleMenuRelationByRoleId(roleId);
        umsRoleDao.updateRoleMenuRelationByRoleId(roleId,menuIds);
        return menuIds.size();
    }

    @Override
    public Integer allocResource(Long roleId, List resourceIds) {
        UmsRoleExample umsRoleExample = new UmsRoleExample();
        umsRoleExample.createCriteria().andIdEqualTo(roleId);
        if (umsRoleDao.countByExample(umsRoleExample) <= 0) {
            throw new ReqFailException(UMS_ADMIN_ROLE_NOT_EXIST);
        }
        if (!umsResourceDao.selectAll().containsAll(resourceIds)){
            throw new ReqFailException(UMS_ROLE_MENU_REL_ILLEGAL);
        }
        umsRoleDao.deleteRoleResourceRelationByRoleId(roleId);
        umsRoleDao.insertRoleResourceRelationByRoleId(roleId,resourceIds);
        return resourceIds.size();
    }

    @Override
    public Integer updateStatus(Long roleId, Integer status) {
        UmsRoleExample umsRoleExample = new UmsRoleExample();
        umsRoleExample.createCriteria().andIdEqualTo(roleId);
        if (umsRoleDao.countByExample(umsRoleExample) <= 0) {
            throw new ReqFailException(UMS_ADMIN_ROLE_NOT_EXIST);
        }
        umsRoleDao.updateStatusByRoleId(roleId,status);
        return status;
    }

}
