package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.common.ReqFailException;
import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dao.PmsProductDao;
import club.banyuan.zgMallMgt.dao.SmsFlashPromotionProductRelationDao;
import club.banyuan.zgMallMgt.dao.entity.PmsProduct;
import club.banyuan.zgMallMgt.dao.entity.SmsFlashPromotionProductRelation;
import club.banyuan.zgMallMgt.dto.SmsFlashPromotionProductRelationReqWithProduct;
import club.banyuan.zgMallMgt.service.SmsFlashPromotionProductRelationService;
import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static club.banyuan.zgMallMgt.common.FailReason.SMS_FLASH_PROMOTION_PRODUCT_RELATION_NOT_EXIST;

@Service
public class SmsFlashPromotionProductRelationServiceImpl implements SmsFlashPromotionProductRelationService {

    @Autowired
    private SmsFlashPromotionProductRelationDao smsFlashPromotionProductRelationDao;
    @Autowired
    private PmsProductDao pmsProductDao;

    @Override
    public ResponsePage list(Integer pageNum, Integer pageSize, Long flashPromotionId, Long flashPromotionSessionId) {
        PageHelper.startPage(pageNum, pageSize);
        List<SmsFlashPromotionProductRelation> smsFlashPromotionProductRelations = smsFlashPromotionProductRelationDao.selectByFlashPromotionIdAndFlashPromotionSessionId(flashPromotionId, flashPromotionSessionId);
        PageInfo<SmsFlashPromotionProductRelation> pageInfo = new PageInfo<>(smsFlashPromotionProductRelations);
        List<SmsFlashPromotionProductRelationReqWithProduct> collect = smsFlashPromotionProductRelations.stream().map(t -> {
            SmsFlashPromotionProductRelationReqWithProduct smsFlashPromotionProductRelationReqWithProduct = new SmsFlashPromotionProductRelationReqWithProduct();
            PmsProduct pmsProduct = pmsProductDao.selectByPrimaryKey(t.getProductId());
            BeanUtil.copyProperties(t, smsFlashPromotionProductRelationReqWithProduct);
            smsFlashPromotionProductRelationReqWithProduct.setProduct(pmsProduct);
            return smsFlashPromotionProductRelationReqWithProduct;
        }).collect(Collectors.toList());
        return ResponsePage.setPages(pageInfo,collect);
    }

    @Override
    public Integer create(List<SmsFlashPromotionProductRelation> smsFlashPromotionProductRelations) {
        smsFlashPromotionProductRelations.forEach(t->{
            smsFlashPromotionProductRelationDao.insert(t);
        });
        return 1;
    }

    @Override
    public Integer update(Long id, SmsFlashPromotionProductRelationReqWithProduct smsFlashPromotionProductRelationReqWithProduct) {
        SmsFlashPromotionProductRelation smsFlashPromotionProductRelation = new SmsFlashPromotionProductRelation();
        BeanUtil.copyProperties(smsFlashPromotionProductRelationReqWithProduct, smsFlashPromotionProductRelation);
        smsFlashPromotionProductRelation.setId(id);
        return smsFlashPromotionProductRelationDao.updateByPrimaryKey(smsFlashPromotionProductRelation);
    }

    @Override
    public Integer delete(Long id) {
        SmsFlashPromotionProductRelation smsFlashPromotionProductRelation = smsFlashPromotionProductRelationDao.selectByPrimaryKey(id);
        if (BeanUtil.isEmpty(smsFlashPromotionProductRelation)){
            throw new ReqFailException(SMS_FLASH_PROMOTION_PRODUCT_RELATION_NOT_EXIST);
        }
        return smsFlashPromotionProductRelationDao.deleteByPrimaryKey(id);
    }
}
