package club.banyuan.zgMallMgt.service;


import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dao.entity.PmsBrand;
import club.banyuan.zgMallMgt.dto.PmsBrandResp;

import java.io.IOException;
import java.util.List;

public interface PmsBrandService {
    ResponsePage list(Integer pageNum, Integer pageSize, String keyword);

    Integer create(PmsBrand pmsBrand);

    PmsBrandResp pmsBrandInfo(Long brandId);

    Integer update(Long brandId, PmsBrand pmsBrand);

    Integer showStatus(List<Long> brandIds, Integer showStatus);

    Integer factoryStatus(List<Long> brandIds, Integer factoryStatus);

    Integer delete(Long brandId) throws IOException;
}
