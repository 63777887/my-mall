package club.banyuan.zgMallMgt.dao;

import club.banyuan.zgMallMgt.dao.entity.PmsProductAttributeCategory;

import java.util.List;

public interface PmsProductAttributeCategoryDao {
    int deleteByPrimaryKey(Long id);

    int insert(PmsProductAttributeCategory record);

    int insertSelective(PmsProductAttributeCategory record);

    PmsProductAttributeCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmsProductAttributeCategory record);

    int updateByPrimaryKey(PmsProductAttributeCategory record);

    List<PmsProductAttributeCategory> selectAll();

}