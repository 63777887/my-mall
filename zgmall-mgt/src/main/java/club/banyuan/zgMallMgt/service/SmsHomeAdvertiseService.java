package club.banyuan.zgMallMgt.service;

import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dao.entity.SmsHomeAdvertise;
import club.banyuan.zgMallMgt.dto.SmsHomeAdvertiseResp;

import java.util.Date;
import java.util.List;

public interface SmsHomeAdvertiseService {
    ResponsePage list(Integer pageNum, Integer pageSize, String name, Integer type, Date endTime);

    Integer create(SmsHomeAdvertise smsHomeAdvertise);

    SmsHomeAdvertiseResp getInfo(Long id);

    Integer update(Long id, SmsHomeAdvertise smsHomeAdvertise);

    Integer delete(List<Long> ids);

    Integer updateStatus(Long id, Integer status);
}
