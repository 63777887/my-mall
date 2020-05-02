package club.banyuan.zgMallMgt.service;

import club.banyuan.zgMallMgt.dao.entity.SmsFlashPromotionSession;
import club.banyuan.zgMallMgt.dto.SmsFlashPromotionSessionResp;
import club.banyuan.zgMallMgt.dto.SmsFlashSessionSelectListResp;

import java.util.List;

public interface SmsFlashPromotionSessionService {
    List<SmsFlashPromotionSessionResp> list();

    Integer create(SmsFlashPromotionSession smsFlashPromotionSession);

    Integer update(SmsFlashPromotionSession smsFlashPromotionSession, Long id);

    Integer updateStatus(Integer status, Long id);

    Integer delete(Long id);

    List<SmsFlashSessionSelectListResp> selectList(Long flashPromotionId);
}
