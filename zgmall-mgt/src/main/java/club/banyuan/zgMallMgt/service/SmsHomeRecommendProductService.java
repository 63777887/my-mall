package club.banyuan.zgMallMgt.service;

import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dao.entity.SmsHomeRecommendProduct;

import java.util.List;

public interface SmsHomeRecommendProductService {
    ResponsePage list(Integer pageNum, Integer pageSize, String productName, Integer recommendStatus);

    Integer updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    Integer create(List<SmsHomeRecommendProduct> smsHomeRecommendProducts);

    Integer updateSort(Integer sort, Long id);

    Integer delete(List<Long> ids);
}
