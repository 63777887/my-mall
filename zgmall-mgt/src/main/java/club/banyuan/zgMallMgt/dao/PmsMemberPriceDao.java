package club.banyuan.zgMallMgt.dao;

import club.banyuan.zgMallMgt.dao.entity.PmsMemberPrice;

import java.util.List;

public interface PmsMemberPriceDao {
    int deleteByPrimaryKey(Long id);

    int insert(PmsMemberPrice record);

    int insertSelective(PmsMemberPrice record);

    PmsMemberPrice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmsMemberPrice record);

    int updateByPrimaryKey(PmsMemberPrice record);

    int insertMany(List<PmsMemberPrice> pmsMemberPrices);

    int deleteByProductId(Long id);

    List<PmsMemberPrice> selectByProductId(Long productId);

    int deleteByProductIds(List<Long> ids);
}