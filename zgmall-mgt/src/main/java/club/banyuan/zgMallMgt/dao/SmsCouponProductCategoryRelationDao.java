package club.banyuan.zgMallMgt.dao;

import club.banyuan.zgMallMgt.dao.entity.SmsCouponProductCategoryRelation;

import java.util.List;

public interface SmsCouponProductCategoryRelationDao {
    int deleteByPrimaryKey(Long id);

    int insert(SmsCouponProductCategoryRelation record);

    int insertSelective(SmsCouponProductCategoryRelation record);

    SmsCouponProductCategoryRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsCouponProductCategoryRelation record);

    int updateByPrimaryKey(SmsCouponProductCategoryRelation record);

    List<SmsCouponProductCategoryRelation> selectByCouponId(Long couponId);

    int deleteBycouponId(Long couponId);
}