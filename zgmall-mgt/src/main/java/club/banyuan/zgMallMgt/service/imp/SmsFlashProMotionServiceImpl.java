package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.common.ReqFailException;
import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dao.SmsFlashPromotionDao;
import club.banyuan.zgMallMgt.dao.SmsFlashPromotionProductRelationDao;
import club.banyuan.zgMallMgt.dao.entity.SmsFlashPromotion;
import club.banyuan.zgMallMgt.dao.entity.SmsFlashPromotionExample;
import club.banyuan.zgMallMgt.dto.SmsFlashPromotionResp;
import club.banyuan.zgMallMgt.service.SmsFlashProMotionService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static club.banyuan.zgMallMgt.common.FailReason.SMS_FLASH_PROMOTION_NOT_EXIST;

@Service
public class SmsFlashProMotionServiceImpl implements SmsFlashProMotionService {

    @Autowired
    private SmsFlashPromotionDao smsFlashPromotionDao;
    @Autowired
    private SmsFlashPromotionProductRelationDao smsFlashPromotionProductRelationDao;

    @Override
    public ResponsePage list(Integer pageNum, Integer pageSize, String keyword) {
        PageHelper.startPage(pageNum, pageSize);
        SmsFlashPromotionExample smsFlashPromotionExample = new SmsFlashPromotionExample();
        SmsFlashPromotionExample.Criteria criteria = smsFlashPromotionExample.createCriteria();
        if (StrUtil.isNotBlank(keyword)){
            criteria.andTitleLike(StrUtil.concat(true, "%",keyword,"%"));
        }
        List<SmsFlashPromotion> smsFlashPromotions = smsFlashPromotionDao.selectByExample(smsFlashPromotionExample);
        PageInfo<SmsFlashPromotion> pageInfo = new PageInfo<>(smsFlashPromotions);
        List<SmsFlashPromotionResp> collect = smsFlashPromotions.stream().map(t -> {
            SmsFlashPromotionResp smsFlashPromotionResp = new SmsFlashPromotionResp();
            BeanUtil.copyProperties(t, smsFlashPromotionResp);
            return smsFlashPromotionResp;
        }).collect(Collectors.toList());
        return ResponsePage.setPages(pageInfo,collect);

    }

    @Override
    public Integer create(SmsFlashPromotion smsFlashPromotion) {
        smsFlashPromotion.setCreateTime(new Date());
        return smsFlashPromotionDao.insert(smsFlashPromotion);
    }

    @Override
    public Integer updateStatus(Long id, Integer status) {
        SmsFlashPromotion smsFlashPromotion = smsFlashPromotionDao.selectByPrimaryKey(id);
        if (BeanUtil.isEmpty(smsFlashPromotion)){
            throw new ReqFailException(SMS_FLASH_PROMOTION_NOT_EXIST);
        }
        smsFlashPromotion.setStatus(status);
        return smsFlashPromotionDao.updateByPrimaryKey(smsFlashPromotion);
    }

    @Override
    public Integer update(Long id, SmsFlashPromotion smsFlashPromotion) {
        SmsFlashPromotion smsFlashPromotion1 = smsFlashPromotionDao.selectByPrimaryKey(id);
        if (BeanUtil.isEmpty(smsFlashPromotion1)){
            throw new ReqFailException(SMS_FLASH_PROMOTION_NOT_EXIST);
        }
        return smsFlashPromotionDao.updateByPrimaryKey(smsFlashPromotion);
    }

    @Override
    public Integer delete(Long id) {
        SmsFlashPromotion smsFlashPromotion1 = smsFlashPromotionDao.selectByPrimaryKey(id);
        if (BeanUtil.isEmpty(smsFlashPromotion1)){
            throw new ReqFailException(SMS_FLASH_PROMOTION_NOT_EXIST);
        }
        smsFlashPromotionDao.deleteByPrimaryKey(id);
        return smsFlashPromotionProductRelationDao.deleteBySmsFlashProMotionId(id);
    }
}
