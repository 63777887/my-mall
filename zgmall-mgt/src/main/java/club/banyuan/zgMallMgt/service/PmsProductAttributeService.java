package club.banyuan.zgMallMgt.service;

import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dao.entity.PmsProductAttribute;
import club.banyuan.zgMallMgt.dto.AttrInfoResp;
import club.banyuan.zgMallMgt.dto.PmsProductAttributeResp;

import java.util.List;

public interface PmsProductAttributeService {
    ResponsePage list(Integer pageNum, Integer pageSize, Integer type, Long productAttributeCategoryId);

    List<AttrInfoResp> attrInfo(Long productCategoryId);

    Integer create(PmsProductAttribute pmsProductAttribute);

    PmsProductAttributeResp showAttr(Long id);

    Integer update(Long id, PmsProductAttribute pmsProductAttribute);

    Integer delete(List<Long> ids);
}
