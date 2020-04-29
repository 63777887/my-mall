package club.banyuan.zgMallMgt.dao;

import club.banyuan.zgMallMgt.dao.entity.CmsPrefrenceAreaProductRelation;

import java.util.List;

public interface CmsPrefrenceAreaProductRelationDao {
    int deleteByPrimaryKey(Long id);

    int insert(CmsPrefrenceAreaProductRelation record);

    int insertSelective(CmsPrefrenceAreaProductRelation record);

    CmsPrefrenceAreaProductRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmsPrefrenceAreaProductRelation record);

    int updateByPrimaryKey(CmsPrefrenceAreaProductRelation record);

    int insertMany(List<CmsPrefrenceAreaProductRelation> cmsPrefrenceAreaProductRelations);

    int deleteByProductId(Long id);

    List<CmsPrefrenceAreaProductRelation> selectByProductId(Long productId);

    int deleteByProductIds(List<Long> ids);
}