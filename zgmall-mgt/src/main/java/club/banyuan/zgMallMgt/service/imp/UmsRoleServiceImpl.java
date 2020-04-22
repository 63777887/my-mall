package club.banyuan.zgMallMgt.service.imp;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UmsRoleServiceImpl implements UmsRoleService {

    @Autowired
    private UmsRoleDao umsRoleDao;
    @Autowired
    private UmsResourceDao umsResourceDao;
    @Autowired
    private UmsMenuDao umsMenuDao;

    @Override
    public List<UmsRoleResp> showList(Integer pageNum, Integer pageSize, String keyword) {

        UmsRoleExample umsRoleExample = new UmsRoleExample();
        if (keyword != null) {
            UmsRoleExample.Criteria criteria = umsRoleExample.createCriteria();
            criteria.andNameLike(StrUtil.concat(true, "%",keyword,"%"));

        }
        PageHelper.startPage(pageNum,pageSize);
        List<UmsRole> umsRoles = umsRoleDao.selectByExample(umsRoleExample);
        return umsRoles.stream().map(t-> {
            UmsRoleResp umsRoleResp = new UmsRoleResp();
            BeanUtil.copyProperties(t, umsRoleResp);
            return umsRoleResp;
        }).collect(Collectors.toList());
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
        UmsRole umsRole = new UmsRole();
        BeanUtil.copyProperties(umsRoleResp, umsRole);
        umsRole.setCreateTime(new Date());
        umsRole.setSort(0);
       return (long)umsRoleDao.insertSelective(umsRole);
    }

    @Override
    public Long deleteUmsRoleById(Long ids) {
        return (long)umsRoleDao.deleteByPrimaryKey(ids);
    }

    @Override
    public Long updateUmsRole(UmsRoleResp umsRoleResp, Long roleId) {
        UmsRole umsRole = new UmsRole();
        umsRole.setId(roleId);
        BeanUtil.copyProperties(umsRoleResp, umsRole);
        return (long)umsRoleDao.updateByPrimaryKeySelective(umsRole);
    }

    @Override
    public List<UmsResourceResp> showListResource(Long roleId) {
        List<UmsResource> umsResources = umsResourceDao.selectResourcesByRoleId(roleId);
        return umsResources.stream().map(t->{
            UmsResourceResp umsResourceResp = new UmsResourceResp();
            BeanUtil.copyProperties(t, umsResourceResp);
            return umsResourceResp;
        }).collect(Collectors.toList());
    }

    @Override
    public List<UmsMenuResp> listMenu(Long roleId) {
        List<UmsMenu> umsMenus = umsMenuDao.selectByRoleIds(Collections.singletonList(roleId));

        return umsMenus.stream().map(t->{
            UmsMenuResp umsMenuResp = new UmsMenuResp();
            BeanUtil.copyProperties(t, umsMenuResp);
            return umsMenuResp;
        }).collect(Collectors.toList());
    }

    @Override
    public Integer allocMenu(Long roleId, List menuIds) {
        umsRoleDao.deleteRoleMenuRelationByRoleId(roleId);
        umsRoleDao.updateRoleMenuRelationByRoleId(roleId,menuIds);
        return menuIds.size();
    }

    @Override
    public Integer allocResource(Long roleId, List resourceIds) {
        umsRoleDao.deleteRoleResourceRelationByRoleId(roleId);
        umsRoleDao.insertRoleResourceRelationByRoleId(roleId,resourceIds);
        return resourceIds.size();
    }

    @Override
    public Integer updateStatus(Long roleId, Integer status) {
        umsRoleDao.updateStatusByRoleId(roleId,status);
        return status;
    }

}
