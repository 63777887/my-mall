package club.banyuan.zgMallMgt.dao;

import club.banyuan.zgMallMgt.dao.entity.OmsOrderReturnReason;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OmsOrderReturnReasonDao {
    int deleteByPrimaryKey(Long id);

    int insert(OmsOrderReturnReason record);

    int insertSelective(OmsOrderReturnReason record);

    OmsOrderReturnReason selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OmsOrderReturnReason record);

    int updateByPrimaryKey(OmsOrderReturnReason record);

    List<OmsOrderReturnReason> selectAll();


    int deleteByPrimaryKeys(List<Long> ids);

    int updateStatusByids(@Param("ids") List<Long> ids,@Param("status") Integer status);
}