package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.common.ReqFailException;
import club.banyuan.zgMallMgt.dao.SmsFlashPromotionProductRelationDao;
import club.banyuan.zgMallMgt.dao.SmsFlashPromotionSessionDao;
import club.banyuan.zgMallMgt.dao.entity.SmsFlashPromotionSession;
import club.banyuan.zgMallMgt.dto.SmsFlashPromotionSessionResp;
import club.banyuan.zgMallMgt.dto.SmsFlashSessionSelectListResp;
import club.banyuan.zgMallMgt.service.SmsFlashPromotionSessionService;
import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static club.banyuan.zgMallMgt.common.FailReason.SMS_FLASH_PROMOTION_SESSION_NOT_EXIST;

@Service
public class SmsFlashPromotionSessionServiceImpl implements SmsFlashPromotionSessionService {

    @Autowired
    private SmsFlashPromotionSessionDao smsFlashPromotionSessionDao;
    @Autowired
    private SmsFlashPromotionProductRelationDao smsFlashPromotionProductRelationDao;


    @Override
    public List<SmsFlashPromotionSessionResp> list() {
        List<SmsFlashPromotionSession> smsFlashPromotionSessions = smsFlashPromotionSessionDao.selectAll();
        return smsFlashPromotionSessions.stream().map(t->{
            SmsFlashPromotionSessionResp smsFlashPromotionSessionResp = new SmsFlashPromotionSessionResp();
            BeanUtil.copyProperties(t, smsFlashPromotionSessionResp);
            return smsFlashPromotionSessionResp;
        }).collect(Collectors.toList());
    }

    @Override
    public Integer create(SmsFlashPromotionSession smsFlashPromotionSession) {
        smsFlashPromotionSession.setCreateTime(new Date());
        return smsFlashPromotionSessionDao.insert(smsFlashPromotionSession);
    }

    @Override
    public Integer update(SmsFlashPromotionSession smsFlashPromotionSession, Long id) {
        SmsFlashPromotionSession smsFlashPromotionSession1 = smsFlashPromotionSessionDao.selectByPrimaryKey(id);
        if (BeanUtil.isEmpty(smsFlashPromotionSession1)){
            throw new ReqFailException(SMS_FLASH_PROMOTION_SESSION_NOT_EXIST);
        }
        smsFlashPromotionSession.setId(id);
        return smsFlashPromotionSessionDao.updateByPrimaryKey(smsFlashPromotionSession);
    }

    @Override
    public Integer updateStatus(Integer status, Long id) {
        SmsFlashPromotionSession smsFlashPromotionSession = smsFlashPromotionSessionDao.selectByPrimaryKey(id);
        if (BeanUtil.isEmpty(smsFlashPromotionSession)){
            throw new ReqFailException(SMS_FLASH_PROMOTION_SESSION_NOT_EXIST);
        }
        return smsFlashPromotionSessionDao.updateStatus(status,id);
    }

    @Override
    public Integer delete(Long id) {
        SmsFlashPromotionSession smsFlashPromotionSession = smsFlashPromotionSessionDao.selectByPrimaryKey(id);
        if (BeanUtil.isEmpty(smsFlashPromotionSession)){
            throw new ReqFailException(SMS_FLASH_PROMOTION_SESSION_NOT_EXIST);
        }
        return smsFlashPromotionSessionDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<SmsFlashSessionSelectListResp> selectList(Long flashPromotionId) {
        List<SmsFlashPromotionSession> smsFlashPromotionSessions = smsFlashPromotionSessionDao.selectByFlashPromotionId(flashPromotionId);
       return smsFlashPromotionSessions.stream().map(t->{
            SmsFlashSessionSelectListResp smsFlashSessionSelectListResp = new SmsFlashSessionSelectListResp();
            BeanUtil.copyProperties(t, smsFlashSessionSelectListResp);
            int i = smsFlashPromotionProductRelationDao.selectCountByFlashPromotionIdAndFlashPromotionSessionId(flashPromotionId, t.getId());
            smsFlashSessionSelectListResp.setProductCount(i);
            return smsFlashSessionSelectListResp;
        }).collect(Collectors.toList());
    }
}