package club.banyuan.zgMallMgt.service;

import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dao.entity.SmsHomeBrand;

import java.util.List;

public interface SmsHomeBrandService {
    ResponsePage list(Integer pageNum, Integer pageSize, String brandName, Integer recommendStatus);

    Integer create(List<SmsHomeBrand> smsHomeBrands);

    Integer updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    Integer updateSort(Integer sort, Long id);

    Integer delete(List<Long> ids);
}
