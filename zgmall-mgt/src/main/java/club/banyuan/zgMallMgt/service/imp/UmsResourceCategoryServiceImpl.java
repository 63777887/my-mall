package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.dao.UmsResourceCategoryDao;
import club.banyuan.zgMallMgt.dao.entity.UmsResourceCategory;
import club.banyuan.zgMallMgt.dto.UmsResourceCategoryResp;
import club.banyuan.zgMallMgt.service.UmsResourceCategoryService;
import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
}
