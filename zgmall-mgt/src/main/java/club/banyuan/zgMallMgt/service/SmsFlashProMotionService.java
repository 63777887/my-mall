package club.banyuan.zgMallMgt.service;

import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dao.entity.SmsFlashPromotion;

public interface SmsFlashProMotionService {
    ResponsePage list(Integer pageNum, Integer pageSize, String keyword);

    Integer create(SmsFlashPromotion smsFlashPromotion);

    Integer updateStatus(Long id, Integer status);

    Integer update(Long id, SmsFlashPromotion smsFlashPromotion);

    Integer delete(Long id);
}
