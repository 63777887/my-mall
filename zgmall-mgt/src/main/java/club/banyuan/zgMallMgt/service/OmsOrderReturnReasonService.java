package club.banyuan.zgMallMgt.service;

import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dao.entity.OmsOrderReturnReason;
import club.banyuan.zgMallMgt.dto.OmsOrderReturnReasonResp;

import java.util.List;

public interface OmsOrderReturnReasonService {
    ResponsePage list(Integer pageNum, Integer pageSize);

    Integer create(OmsOrderReturnReason omsOrderReturnReason);

    OmsOrderReturnReasonResp getInfo(Long id);

    Integer update(OmsOrderReturnReason omsOrderReturnReason, Long id);

    Integer delete(List<Long> ids);

    Integer updateStatus(List<Long> ids, Integer status);
}
