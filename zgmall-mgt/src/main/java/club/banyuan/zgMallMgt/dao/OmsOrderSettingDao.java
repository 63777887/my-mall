package club.banyuan.zgMallMgt.dao;

import club.banyuan.zgMallMgt.dao.entity.OmsOrderSetting;

public interface OmsOrderSettingDao {
    int deleteByPrimaryKey(Long id);

    int insert(OmsOrderSetting record);

    int insertSelective(OmsOrderSetting record);

    OmsOrderSetting selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OmsOrderSetting record);

    int updateByPrimaryKey(OmsOrderSetting record);
}