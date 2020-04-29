package club.banyuan.zgMallMgt.service;

import club.banyuan.zgMallMgt.common.ResponsePage;

public interface OmsOrderService {
    ResponsePage list(Integer pageNum, Integer pageSize);
}
