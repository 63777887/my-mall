package club.banyuan.zgMallMgt.dao;

import club.banyuan.zgMallMgt.dao.entity.OmsOrderItem;

import java.util.List;

public interface OmsOrderItemDao {
    int deleteByPrimaryKey(Long id);

    int insert(OmsOrderItem record);

    int insertSelective(OmsOrderItem record);

    OmsOrderItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OmsOrderItem record);

    int updateByPrimaryKey(OmsOrderItem record);

    List<OmsOrderItem> selectByOrderId(Long orderId);
}