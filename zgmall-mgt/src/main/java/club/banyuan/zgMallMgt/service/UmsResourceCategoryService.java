package club.banyuan.zgMallMgt.service;

import club.banyuan.zgMallMgt.dao.entity.UmsResourceCategory;
import club.banyuan.zgMallMgt.dto.UmsResourceCategoryResp;

import java.util.List;

public interface UmsResourceCategoryService {
    List<UmsResourceCategoryResp> allList();

    Integer update(UmsResourceCategory umsResourceCategory, Long id);

    Integer insert(UmsResourceCategory umsResourceCategory);

    Integer delete(Long id);
}
