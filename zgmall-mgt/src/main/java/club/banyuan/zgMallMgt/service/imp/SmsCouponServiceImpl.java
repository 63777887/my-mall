package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.common.ReqFailException;
import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dao.SmsCouponDao;
import club.banyuan.zgMallMgt.dao.SmsCouponProductCategoryRelationDao;
import club.banyuan.zgMallMgt.dao.SmsCouponProductRelationDao;
import club.banyuan.zgMallMgt.dao.entity.SmsCoupon;
import club.banyuan.zgMallMgt.dao.entity.SmsCouponExample;
import club.banyuan.zgMallMgt.dao.entity.SmsCouponProductCategoryRelation;
import club.banyuan.zgMallMgt.dao.entity.SmsCouponProductRelation;
import club.banyuan.zgMallMgt.dto.CreateCouponReq;
import club.banyuan.zgMallMgt.dto.SmsCouponResp;
import club.banyuan.zgMallMgt.service.SmsCouponService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static club.banyuan.zgMallMgt.common.FailReason.SMS_COUPON_NOT_EXIST;

@Service
public class SmsCouponServiceImpl implements SmsCouponService {

    @Autowired
    private SmsCouponDao smsCouponDao;
    @Autowired
    private SmsCouponProductRelationDao smsCouponProductRelationDao;
    @Autowired
    private SmsCouponProductCategoryRelationDao smsCouponProductCategoryRelationDao;

    @Override
    public ResponsePage list(Integer pageNum, Integer pageSize, String name, Integer type) {
        PageHelper.startPage(pageNum, pageSize);
        SmsCouponExample smsCouponExample = new SmsCouponExample();
        SmsCouponExample.Criteria criteria = smsCouponExample.createCriteria();
        if (StrUtil.isNotBlank(name)){
            criteria.andNameLike(StrUtil.concat(true, "%",name,"%"));
        }
        if (type!=null){
            criteria.andTypeEqualTo(type);
        }
        List<SmsCoupon> smsCoupons = smsCouponDao.selectByExample(smsCouponExample);
        PageInfo<SmsCoupon> pageInfo = new PageInfo<>(smsCoupons);
        List<SmsCouponResp> collect = smsCoupons.stream().map(t -> {
            SmsCouponResp smsCouponResp = new SmsCouponResp();
            BeanUtil.copyProperties(t, smsCouponResp);
            return smsCouponResp;
        }).collect(Collectors.toList());
        return ResponsePage.setPages(pageInfo,collect);
    }

    @Override
    @Transactional
    public Integer create(CreateCouponReq createCouponReq) {
        List<SmsCouponProductRelation> productRelationList = createCouponReq.getProductRelationList();
        List<SmsCouponProductCategoryRelation> productCategoryRelationList = createCouponReq.getProductCategoryRelationList();
        SmsCoupon smsCoupon = new SmsCoupon();
        BeanUtil.copyProperties(createCouponReq, smsCoupon);
        smsCoupon.setUseCount(0);
        smsCoupon.setReceiveCount(0);
        smsCoupon.setCount(smsCoupon.getPublishCount()-smsCoupon.getReceiveCount());
        int insert = smsCouponDao.insert(smsCoupon);
        Long couponId = smsCoupon.getId();
        productRelationList.forEach(t->{
            t.setCouponId(couponId);
            smsCouponProductRelationDao.insert(t);
        });
        productCategoryRelationList.forEach(t->{
            t.setCouponId(couponId);
            smsCouponProductCategoryRelationDao.insert(t);
        });
        return insert;
    }

    @Override
    public CreateCouponReq getInfo(Long couponId) {
        SmsCoupon smsCoupon = smsCouponDao.selectByPrimaryKey(couponId);
        if (BeanUtil.isEmpty(smsCoupon)){
            throw new ReqFailException(SMS_COUPON_NOT_EXIST);
        }
        List<SmsCouponProductRelation> smsCouponProductRelations = smsCouponProductRelationDao.selectByCouponId(couponId);
        List<SmsCouponProductCategoryRelation> smsCouponProductCategoryRelations = smsCouponProductCategoryRelationDao.selectByCouponId(couponId);
        CreateCouponReq createCouponReq = new CreateCouponReq();
        BeanUtil.copyProperties(smsCoupon, createCouponReq);
        createCouponReq.setProductRelationList(smsCouponProductRelations);
        createCouponReq.setProductCategoryRelationList(smsCouponProductCategoryRelations);
        return createCouponReq;
    }

    @Override
    @Transactional
    public Integer update(Long couponId, CreateCouponReq createCouponReq) {
        List<SmsCouponProductRelation> productRelationList = createCouponReq.getProductRelationList();
        List<SmsCouponProductCategoryRelation> productCategoryRelationList = createCouponReq.getProductCategoryRelationList();
        SmsCoupon smsCoupon = new SmsCoupon();
        BeanUtil.copyProperties(createCouponReq, smsCoupon);
        smsCoupon.setId(couponId);
        int i = smsCouponDao.updateByPrimaryKey(smsCoupon);
        smsCouponProductRelationDao.deleteByCouponId(couponId);
        if (CollUtil.isNotEmpty(productRelationList)) {
            productRelationList.forEach(t -> {
                t.setCouponId(couponId);
                smsCouponProductRelationDao.insert(t);
            });
        }
        smsCouponProductCategoryRelationDao.deleteBycouponId(couponId);
        if (CollUtil.isNotEmpty(productCategoryRelationList)){
            productCategoryRelationList.forEach(t->{
                t.setCouponId(couponId);
                smsCouponProductCategoryRelationDao.insert(t);
            });
        }
        return i;
    }

    @Override
    @Transactional
    public Integer delete(Long couponId) {
        SmsCoupon smsCoupon = smsCouponDao.selectByPrimaryKey(couponId);
        if (BeanUtil.isEmpty(smsCoupon)){
            throw new ReqFailException(SMS_COUPON_NOT_EXIST);
        }
        smsCouponDao.deleteByPrimaryKey(couponId);
        smsCouponProductRelationDao.deleteByCouponId(couponId);
        return smsCouponProductCategoryRelationDao.deleteBycouponId(couponId);
    }
}
