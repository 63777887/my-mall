package club.banyuan.zgMallMgt.dao;

import club.banyuan.zgMallMgt.dao.entity.PmsProductLadder;

import java.util.List;

public interface PmsProductLadderDao {
    int deleteByPrimaryKey(Long id);

    int insert(PmsProductLadder record);

    int insertSelective(PmsProductLadder record);

    PmsProductLadder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmsProductLadder record);

    int updateByPrimaryKey(PmsProductLadder record);

    int insertMany(List<PmsProductLadder> pmsProductLadders);

    int deleteByProductId(Long id);

    List<PmsProductLadder> selectByProductId(Long productId);

    int deleteByProductIds(List<Long> ids);
}