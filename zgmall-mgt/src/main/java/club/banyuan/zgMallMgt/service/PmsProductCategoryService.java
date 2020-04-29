package club.banyuan.zgMallMgt.service;

import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dao.entity.PmsProductCategory;
import club.banyuan.zgMallMgt.dto.PmsProductCategoryResp;
import club.banyuan.zgMallMgt.dto.PmsProductCategoryWithListResp;

import java.util.List;

public interface PmsProductCategoryService {
    List<PmsProductCategoryResp> listWithChildren();

    ResponsePage list(Integer pageNum, Integer pageSize, Long parentId);

    Integer create(PmsProductCategory pmsProductCategory);

    Integer update(PmsProductCategoryWithListResp pmsProductCategory, Long productCategoryId);

    Integer delete(Long productCategoryId);

    Integer updateNavStatus(Long ids, Integer navStatus);

    Integer updateShowStatus(Long ids, Integer showStatus);

    PmsProductCategoryResp showInfo(Long productCategoryId);

}
