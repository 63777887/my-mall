package club.banyuan.zgMallMgt.dao;

import club.banyuan.zgMallMgt.dao.entity.SmsFlashPromotionProductRelation;

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
}