package club.banyuan.zgMallMgt.dao;

import club.banyuan.zgMallMgt.dao.entity.PmsProductAttribute;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmsProductAttributeDao {
    int deleteByPrimaryKey(Long id);

    int insert(PmsProductAttribute record);

    int insertSelective(PmsProductAttribute record);

    PmsProductAttribute selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmsProductAttribute record);

    int updateByPrimaryKey(PmsProductAttribute record);

    List<PmsProductAttribute> selectByProductAttributeCategoryIdAndType(@Param("productAttributeCategoryId") Long productAttributeCategoryId, @Param("type") Integer type);

    List<PmsProductAttribute> selectByProductAttributeCategoryId(Long productAttributeCategoryId);

    Integer deleteByPrimaryKeys(List<Long> ids);
}