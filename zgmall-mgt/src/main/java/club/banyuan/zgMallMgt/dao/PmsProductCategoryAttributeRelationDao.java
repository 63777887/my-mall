package club.banyuan.zgMallMgt.dao;

import club.banyuan.zgMallMgt.dao.entity.PmsProductCategoryAttributeRelation;
import club.banyuan.zgMallMgt.dto.AttrInfoResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmsProductCategoryAttributeRelationDao {
    int deleteByPrimaryKey(Long id);

    int insert(PmsProductCategoryAttributeRelation record);

    int insertSelective(PmsProductCategoryAttributeRelation record);

    PmsProductCategoryAttributeRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmsProductCategoryAttributeRelation record);

    int updateByPrimaryKey(PmsProductCategoryAttributeRelation record);

    int deleteByProductCategoryId(Long productCategoryId);

    int insertByProductAttributeIdList(@Param("productAttributeIdList") List productAttributeIdList,@Param("productCategoryId") Long productCategoryId);

    List<AttrInfoResp> selectByProductCategoryId(Long productCategoryId);
}