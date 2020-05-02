package club.banyuan.zgMallMgt.service;

import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dao.entity.SmsFlashPromotionProductRelation;
import club.banyuan.zgMallMgt.dto.SmsFlashPromotionProductRelationReqWithProduct;

import java.util.List;

public interface SmsFlashPromotionProductRelationService {
    ResponsePage list(Integer pageNum, Integer pageSize, Long flashPromotionId, Long flashPromotionSessionId);

    Integer create(List<SmsFlashPromotionProductRelation> smsFlashPromotionProductRelations);

    Integer update(Long id, SmsFlashPromotionProductRelationReqWithProduct smsFlashPromotionProductRelationReqWithProduct);

    Integer delete(Long id);

}
