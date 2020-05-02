package club.banyuan.zgMallMgt.dao;

import club.banyuan.zgMallMgt.dao.entity.SmsFlashPromotionProductRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SmsFlashPromotionProductRelationDao {
    int deleteByPrimaryKey(Long id);

    int insert(SmsFlashPromotionProductRelation record);

    int insertSelective(SmsFlashPromotionProductRelation record);

    SmsFlashPromotionProductRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsFlashPromotionProductRelation record);

    int updateByPrimaryKey(SmsFlashPromotionProductRelation record);

    int deleteByProductId(Long id);

    List<SmsFlashPromotionProductRelation> selectByProductId(Long productId);

    int deleteByProductIds(List<Long> ids);

    List<SmsFlashPromotionProductRelation> selectByFlashPromotionIdAndFlashPromotionSessionId(@Param("flashPromotionId") Long flashPromotionId,@Param("flashPromotionSessionId") Long flashPromotionSessionId);

    int deleteBySmsFlashProMotionId(Long id);

    int selectCountByFlashPromotionIdAndFlashPromotionSessionId(@Param("flashPromotionId")Long flashPromotionId,@Param("id") Long id);
}