package club.banyuan.zgMallMgt.service;

import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dto.CreateCouponReq;

public interface SmsCouponService {
    ResponsePage list(Integer pageNum, Integer pageSize, String name, Integer type);

    Integer create(CreateCouponReq createCouponReq);

    CreateCouponReq getInfo(Long couponId);

    Integer update(Long couponId, CreateCouponReq createCouponReq);

    Integer delete(Long couponId);
}
