package club.banyuan.zgMallMgt.dao;

import club.banyuan.zgMallMgt.dao.entity.PmsProductCategory;
import club.banyuan.zgMallMgt.dao.entity.PmsProductCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsProductCategoryDao {
    long countByExample(PmsProductCategoryExample example);

    int deleteByExample(PmsProductCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PmsProductCategory record);

    int insertSelective(PmsProductCategory record);

    List<PmsProductCategory> selectByExample(PmsProductCategoryExample example);

    PmsProductCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PmsProductCategory record, @Param("example") PmsProductCategoryExample example);

    int updateByExample(@Param("record") PmsProductCategory record, @Param("example") PmsProductCategoryExample example);

    int updateByPrimaryKeySelective(PmsProductCategory record);

    int updateByPrimaryKey(PmsProductCategory record);

    List<PmsProductCategory> selectAll();

    int updateNavStatusById(@Param("ids") Long ids, @Param("navStatus") Integer navStatus);

    int updateShowStatusById(@Param("ids") Long ids, @Param("showStatus") Integer showStatus);
}