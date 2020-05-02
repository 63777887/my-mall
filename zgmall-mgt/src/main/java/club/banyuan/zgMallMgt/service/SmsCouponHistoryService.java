package club.banyuan.zgMallMgt.service;

import club.banyuan.zgMallMgt.common.ResponsePage;

public interface SmsCouponHistoryService {
    ResponsePage list(Integer pageNum, Integer pageSize, Long couponId, Integer useStatus, String orderSn);
}
