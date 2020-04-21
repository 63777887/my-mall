package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.dao.UmsResourceDao;
import club.banyuan.zgMallMgt.dao.entity.UmsResource;
import club.banyuan.zgMallMgt.dao.entity.UmsResourceExample;
import club.banyuan.zgMallMgt.dto.UmsResourceResp;
import club.banyuan.zgMallMgt.service.UmsResourceService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UmsResourceServiceImpl implements UmsResourceService {

    @Autowired
    private UmsResourceDao umsResourceDao;



    @Override
    public List<UmsResource> getAllResource() {
        return umsResourceDao.selectAll();
    }
    @Test
    public void getAllRedsource() {
        System.out.println(umsResourceDao.selectAll());
    }

    @Override
    public List<UmsResource> getResourcesByAdminId(Long id) {
        return umsResourceDao.selectByAdminId(id);
    }

    @Override
    public List<UmsResourceResp> list(Integer pageSize, Integer pageNum, String nameKeyword, String urlKeyword, Long categoryId) {
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
//        List<UmsResource> umsResources = umsResourceDao.selectAll();
        return umsResources.stream().map(t->{
            UmsResourceResp umsResourceResp = new UmsResourceResp();
            BeanUtil.copyProperties(t, umsResourceResp);
            return umsResourceResp;
        }).collect(Collectors.toList());
    }
}
