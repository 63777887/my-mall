package club.banyuan.zgMallMgt.service;

import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dto.CreateProductReq;
import club.banyuan.zgMallMgt.dto.PmsProductInfoResp;

import java.io.IOException;
import java.util.List;

public interface PmsProductService {
    ResponsePage list(Integer pageNum, Integer pageSize, String keyword, Integer publishStatus, Integer verifyStatus, String productSn, Long productCategoryId, Long brandId);

    Integer create(CreateProductReq createProductReq);

    Integer delete(List<Long> ids, Integer deleteStatus);

    PmsProductInfoResp updateInfo(Long productId);

    Integer update(CreateProductReq createProductReq, Long productId) throws IOException;

    Integer publishStatus(List<Long> ids, Integer publishStatus);

    Integer recommendStatus(List<Long> ids, Integer recommendStatus);

    Integer newStatus(List<Long> ids, Integer newStatus);
}
