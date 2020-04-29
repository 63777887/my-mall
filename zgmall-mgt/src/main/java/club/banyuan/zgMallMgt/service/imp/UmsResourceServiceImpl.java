package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.common.ReqFailException;
import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dao.UmsResourceDao;
import club.banyuan.zgMallMgt.dao.entity.UmsResource;
import club.banyuan.zgMallMgt.dao.entity.UmsResourceExample;
import club.banyuan.zgMallMgt.dto.UmsResourceResp;
import club.banyuan.zgMallMgt.service.UmsResourceService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static club.banyuan.zgMallMgt.common.FailReason.*;

@Service
public class UmsResourceServiceImpl implements UmsResourceService {

    @Autowired
    private UmsResourceDao umsResourceDao;



    @Override
    public List<UmsResource> getAllResource() {
        return umsResourceDao.selectAll();
    }

    @Override
    public List<UmsResource> getResourcesByAdminId(Long id) {
        List<UmsResource> umsResources = umsResourceDao.selectByAdminId(id);
        return umsResources;
    }

    @Override
    public ResponsePage list(Integer pageNum, Integer pageSize,
                             String nameKeyword, String urlKeyword, Long categoryId) {
        PageHelper.startPage(pageNum, pageSize);
        UmsResourceExample umsResourceExample = new UmsResourceExample();
        UmsResourceExample.Criteria criteria = umsResourceExample.createCriteria();
        if (categoryId != null) {
            criteria.andCategoryIdEqualTo(categoryId);
        }
        if (StrUtil.isNotBlank(nameKeyword)) {
            criteria.andNameLike(StrUtil.concat(true, "%", nameKeyword, "%"));
        }
        if (StrUtil.isNotBlank(urlKeyword)) {
            criteria.andUrlLike(StrUtil.concat(true, "%", urlKeyword, "%"));
        }
        List<UmsResource> umsResources = umsResourceDao.selectByExample(umsResourceExample);
        List<UmsResourceResp> collect = umsResources.stream().map(t -> {
            UmsResourceResp umsResourceResp = new UmsResourceResp();
            BeanUtil.copyProperties(t, umsResourceResp);
            return umsResourceResp;
        }).collect(Collectors.toList());
        PageInfo<UmsResource> pageInfo = new PageInfo<>(umsResources);
        return ResponsePage.setPages(pageInfo,collect);
    }

    @Override
    public List<UmsResourceResp> getlistAll() {
        List<UmsResource> umsResources = umsResourceDao.selectAll();
        return umsResources.stream().map(t->{
            UmsResourceResp umsResourceResp = new UmsResourceResp();
            BeanUtil.copyProperties(t, umsResourceResp);
            return umsResourceResp;
        }).collect(Collectors.toList());
    }

    @Override
    public Integer update(@Valid UmsResource umsResource, Long resourceId) {
        UmsResource umsResource1 = umsResourceDao.selectByPrimaryKey(resourceId);
        if (ObjectUtil.isEmpty(umsResource1)){
            throw new ReqFailException(UMS_ADMIN_RESOURCE_NOT_EXIST);
        }
        umsResource.setCategoryId(resourceId);

        return umsResourceDao.updateByPrimaryKey(umsResource);
    }

    @Override
    public Integer create(UmsResource umsResource) {
        List<UmsResource> umsResources = umsResourceDao.selectAll();
        umsResources.forEach(t->{
            if (t.getUrl().equals(umsResource.getUrl())||t.getName().equals(umsResource.getName())){
                throw new ReqFailException(UMS_RESOURCE_NAME_DUPLICATE);
            }
        });
        umsResource.setCreateTime(new Date());

        return umsResourceDao.insert(umsResource);
    }

    @Override
    public Integer delete(Long resourceId) {
        UmsResource umsResource = umsResourceDao.selectByPrimaryKey(resourceId);
        if(ObjectUtil.isEmpty(umsResource)){
            throw new ReqFailException(UMS_ADMIN_RESOURCE_NOT_EXIST);
        }

        return umsResourceDao.deleteByPrimaryKey(resourceId);
    }
}
