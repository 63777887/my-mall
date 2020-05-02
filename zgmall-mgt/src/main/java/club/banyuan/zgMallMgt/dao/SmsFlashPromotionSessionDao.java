package club.banyuan.zgMallMgt.dao;

import club.banyuan.zgMallMgt.dao.entity.SmsFlashPromotionSession;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SmsFlashPromotionSessionDao {
    int deleteByPrimaryKey(Long id);

    int insert(SmsFlashPromotionSession record);

    int insertSelective(SmsFlashPromotionSession record);

    SmsFlashPromotionSession selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsFlashPromotionSession record);

    int updateByPrimaryKey(SmsFlashPromotionSession record);

    List<SmsFlashPromotionSession> selectAll();

    int updateStatus(@Param("status") Integer status,@Param("id") Long id);

    List<SmsFlashPromotionSession> selectByFlashPromotionId(Long flashPromotionId);

}