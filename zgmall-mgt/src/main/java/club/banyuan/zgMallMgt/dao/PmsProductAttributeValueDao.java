package club.banyuan.zgMallMgt.dao;

import club.banyuan.zgMallMgt.dao.entity.PmsProductAttributeValue;

import java.util.List;

public interface PmsProductAttributeValueDao {
    int deleteByPrimaryKey(Long id);

    int insert(PmsProductAttributeValue record);

    int insertSelective(PmsProductAttributeValue record);

    PmsProductAttributeValue selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmsProductAttributeValue record);

    int updateByPrimaryKey(PmsProductAttributeValue record);

    int insertMany(List<PmsProductAttributeValue> pmsProductAttributeValues);

    int deleteByProductId(Long id);

    List<PmsProductAttributeValue> selectByProductId(Long productId);

    int deleteByProductIds(List<Long> ids);
}