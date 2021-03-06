package club.banyuan.zgMallMgt.dao;

import club.banyuan.zgMallMgt.dao.entity.OmsOrder;
import club.banyuan.zgMallMgt.dao.entity.OmsOrderExample;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface OmsOrderDao {
    long countByExample(OmsOrderExample example);

    int deleteByExample(OmsOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OmsOrder record);

    int insertSelective(OmsOrder record);

    List<OmsOrder> selectByExample(OmsOrderExample example);

    OmsOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OmsOrder record, @Param("example") OmsOrderExample example);

    int updateByExample(@Param("record") OmsOrder record, @Param("example") OmsOrderExample example);

    int updateByPrimaryKeySelective(OmsOrder record);

    int updateByPrimaryKey(OmsOrder record);


    int uodateDeliveryById(@Param("deliveryCompany") String deliveryCompany,@Param("status") Integer status, @Param("deliverySn") String deliverySn, @Param("deliveryTime") Date deliveryTime, @Param("orderId") Long orderId);

    int updateNoteById(@Param("id") Long id, @Param("note") String note, @Param("status") Integer status);
}