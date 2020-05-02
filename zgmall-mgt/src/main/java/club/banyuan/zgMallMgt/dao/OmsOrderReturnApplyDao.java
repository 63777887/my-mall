package club.banyuan.zgMallMgt.dao;

import club.banyuan.zgMallMgt.dao.entity.OmsOrderReturnApply;
import club.banyuan.zgMallMgt.dao.entity.OmsOrderReturnApplyExample;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface OmsOrderReturnApplyDao {
    long countByExample(OmsOrderReturnApplyExample example);

    int deleteByExample(OmsOrderReturnApplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OmsOrderReturnApply record);

    int insertSelective(OmsOrderReturnApply record);

    List<OmsOrderReturnApply> selectByExample(OmsOrderReturnApplyExample example);

    OmsOrderReturnApply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OmsOrderReturnApply record, @Param("example") OmsOrderReturnApplyExample example);

    int updateByExample(@Param("record") OmsOrderReturnApply record, @Param("example") OmsOrderReturnApplyExample example);

    int updateByPrimaryKeySelective(OmsOrderReturnApply record);

    int updateByPrimaryKey(OmsOrderReturnApply record);

    int updateStatusById(@Param("id") Long id, @Param("companyAddressId") Long companyAddressId, @Param("handleMan") String handleMan, @Param("handleNote") String handleNote, @Param("receiveMan") String receiveMan, @Param("receiveNote") String receiveNote, @Param("returnAmount") BigDecimal returnAmount, @Param("status") Integer status);
}