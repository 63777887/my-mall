package club.banyuan.zgMallMgt.service;

import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dto.PmsProductAttributeCategoryTreeNode;

import java.util.List;

public interface PmsProductAttributeCategoryService {
    ResponsePage list(Integer pageNum, Integer pageSize);

    List<PmsProductAttributeCategoryTreeNode> listWithAttr();

    Integer create(String name);

    Integer update(String name, Long id);

    Integer delete(Long id);
}
