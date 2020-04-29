package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.common.ReqFailException;
import club.banyuan.zgMallMgt.dao.UmsResourceCategoryDao;
import club.banyuan.zgMallMgt.dao.entity.UmsResourceCategory;
import club.banyuan.zgMallMgt.dto.UmsResourceCategoryResp;
import club.banyuan.zgMallMgt.service.UmsResourceCategoryService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static club.banyuan.zgMallMgt.common.FailReason.*;

@Service
public class UmsResourceCategoryServiceImpl implements UmsResourceCategoryService {

    @Autowired
    private UmsResourceCategoryDao umsResourceCategoryDao;

    @Override
    public List<UmsResourceCategoryResp> allList() {
        List<UmsResourceCategory> umsResourceCategories = umsResourceCategoryDao.selectAll();
        return umsResourceCategories.stream().map(t->{
            UmsResourceCategoryResp umsResourceCategoryResp = new UmsResourceCategoryResp();
            BeanUtil.copyProperties(t, umsResourceCategoryResp);
            return umsResourceCategoryResp;
        }).collect(Collectors.toList());
    }

    @Override
    public Integer update(UmsResourceCategory umsResourceCategory, Long id) {
        UmsResourceCategory umsResourceCategory1 = umsResourceCategoryDao.selectByPrimaryKey(id);
        if (ObjectUtil.isEmpty(umsResourceCategory1)){
            throw new ReqFailException(UMS_RESOURCE_CATEGORY_NOT_EXIST);
        }
        umsResourceCategory.setId(id);
        return umsResourceCategoryDao.updateByPrimaryKey(umsResourceCategory);
    }

    @Override
    public Integer insert(UmsResourceCategory umsResourceCategory) {
        List<UmsResourceCategory> umsResourceCategories = umsResourceCategoryDao.selectAll();
        umsResourceCategories.forEach(t->{
            if (t.getName().equals(umsResourceCategory.getName())){
                throw new ReqFailException(UMS_RESOURCE_CATEGORY_NAME_DUPLICATE);
            }
        });
        umsResourceCategory.setCreateTime(new Date());
        return umsResourceCategoryDao.insert(umsResourceCategory);
    }

    @Override
    public Integer delete(Long id) {
        UmsResourceCategory umsResourceCategory = umsResourceCategoryDao.selectByPrimaryKey(id);
        if (ObjectUtil.isEmpty(umsResourceCategory)){
            throw new ReqFailException(UMS_RESOURCE_CATEGORY_NOT_EXIST);
        }
        return umsResourceCategoryDao.deleteByPrimaryKey(id);
    }

}