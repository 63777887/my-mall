package club.banyuan.zgMallMgt.dao;

import club.banyuan.zgMallMgt.dao.entity.PmsProduct;
import club.banyuan.zgMallMgt.dao.entity.PmsProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsProductDao {
    long countByExample(PmsProductExample example);

    int deleteByExample(PmsProductExample example);

    int deleteByPrimaryKey(List<Long> id);
    Integer deleteByPrimaryKeys(List<Long> ids);

    long insert(PmsProduct record);

    int insertSelective(PmsProduct record);

    List<PmsProduct> selectByExample(PmsProductExample example);

    PmsProduct selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PmsProduct record, @Param("example") PmsProductExample example);

    int updateByExample(@Param("record") PmsProduct record, @Param("example") PmsProductExample example);

    int updateByPrimaryKeySelective(PmsProduct record);

    int updateByPrimaryKey(PmsProduct record);

    List<PmsProduct> selectAll();

    List<PmsProduct> selectByPrimaryKeys(List<Long> ids);


    int updatePublishStatusByIds(@Param("ids") List<Long> ids, @Param("publishStatus") Integer publishStatus);

    int updateRecommendStatusByIds(@Param("ids") List<Long> ids,@Param("recommendStatus") Integer recommendStatus);

    int updateNewStatusByIds(@Param("ids") List<Long> ids,@Param("newStatus") Integer newStatus);
}