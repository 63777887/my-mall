package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.common.ReqFailException;
import club.banyuan.zgMallMgt.dao.OmsOrderSettingDao;
import club.banyuan.zgMallMgt.dao.entity.OmsOrderSetting;
import club.banyuan.zgMallMgt.dto.OmsOrderSettingResp;
import club.banyuan.zgMallMgt.service.OrderSettingService;
import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static club.banyuan.zgMallMgt.common.FailReason.OMS_ORDER_SETTING_NOT_EXIST;

@Service
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    private OmsOrderSettingDao omsOrderSettingDao;

    @Override
    public OmsOrderSettingResp showOrderSetting(Long orderSettingId) {
        OmsOrderSetting omsOrderSetting = omsOrderSettingDao.selectByPrimaryKey(orderSettingId);
        OmsOrderSettingResp omsOrderSettingResp = new OmsOrderSettingResp();
        BeanUtil.copyProperties(omsOrderSetting, omsOrderSettingResp);
        return omsOrderSettingResp;
    }

    @Override
    public Integer update(Long orderSettingId, OmsOrderSetting omsOrderSetting) {
        OmsOrderSetting omsOrderSetting1 = omsOrderSettingDao.selectByPrimaryKey(orderSettingId);
        if (BeanUtil.isEmpty(omsOrderSetting1)){
            throw new ReqFailException(OMS_ORDER_SETTING_NOT_EXIST);
        }
        omsOrderSetting.setId(orderSettingId);
        return omsOrderSettingDao.updateByPrimaryKey(omsOrderSetting);
    }
}
