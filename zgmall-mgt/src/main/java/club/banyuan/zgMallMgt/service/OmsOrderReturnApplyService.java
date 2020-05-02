package club.banyuan.zgMallMgt.service;

import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dto.OmsOrderReturnApplyResp;
import club.banyuan.zgMallMgt.dto.OmsUpdateApplyResp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface OmsOrderReturnApplyService {
    ResponsePage list(Integer pageNum, Integer pageSize, Long id, Integer status, Date createTime, String handleMan, Date handleTime);

    OmsOrderReturnApplyResp getInfo(Long id);


    Integer delete(List<Long> ids);

    Integer updateStatus(Long id, OmsUpdateApplyResp omsUpdateApplyResp);
}
