package club.banyuan.zgMallMgt.dao;

import club.banyuan.zgMallMgt.dao.entity.OmsOrderOperateHistory;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OmsOrderOperateHistoryDao {
    int deleteByPrimaryKey(Long id);

    int insert(OmsOrderOperateHistory record);

    int insertSelective(OmsOrderOperateHistory record);

    OmsOrderOperateHistory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OmsOrderOperateHistory record);

    int updateByPrimaryKey(OmsOrderOperateHistory record);

    List<OmsOrderOperateHistory> selectByOrderId(Long orderId);

    int insertByOrderId(@Param("orderId") Long orderId,@Param("nickName") String name,@Param("date") Date date, @Param("status") int status,@Param("note") String note);

    int deleteByOrderIds(List<Long> ids);
}