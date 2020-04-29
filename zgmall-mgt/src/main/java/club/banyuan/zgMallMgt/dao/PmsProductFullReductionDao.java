package club.banyuan.zgMallMgt.dao;

import club.banyuan.zgMallMgt.dao.entity.PmsProductFullReduction;

import java.util.List;

public interface PmsProductFullReductionDao {
    int deleteByPrimaryKey(Long id);

    int insert(PmsProductFullReduction record);

    int insertSelective(PmsProductFullReduction record);

    PmsProductFullReduction selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmsProductFullReduction record);

    int updateByPrimaryKey(PmsProductFullReduction record);

    int insertMany(List<PmsProductFullReduction> pmsProductFullReductions);

    int deleteByProductId(Long id);

    List<PmsProductFullReduction> selectByProductId(Long productId);

    int deleteByProductIds(List<Long> ids);
}