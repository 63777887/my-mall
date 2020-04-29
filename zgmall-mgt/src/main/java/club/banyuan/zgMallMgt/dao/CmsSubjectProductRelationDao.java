package club.banyuan.zgMallMgt.dao;

import club.banyuan.zgMallMgt.dao.entity.CmsSubjectProductRelation;

import java.util.List;

public interface CmsSubjectProductRelationDao {
    int deleteByPrimaryKey(Long id);

    int insert(CmsSubjectProductRelation record);

    int insertSelective(CmsSubjectProductRelation record);

    CmsSubjectProductRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmsSubjectProductRelation record);

    int updateByPrimaryKey(CmsSubjectProductRelation record);

    int insertMany(List<CmsSubjectProductRelation> cmsSubjectProductRelations);

    int deleteByProductId(Long id);

    List<CmsSubjectProductRelation> selectByProductId(Long productId);

    int deleteByProductIds(List<Long> ids);
}