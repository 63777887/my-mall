package club.banyuan.zgMallMgt.service;

import club.banyuan.zgMallMgt.dao.entity.OmsOrderSetting;
import club.banyuan.zgMallMgt.dto.OmsOrderSettingResp;

public interface OrderSettingService {
    OmsOrderSettingResp showOrderSetting(Long orderSettingId);

    Integer update(Long orderSettingId, OmsOrderSetting omsOrderSetting);
}
