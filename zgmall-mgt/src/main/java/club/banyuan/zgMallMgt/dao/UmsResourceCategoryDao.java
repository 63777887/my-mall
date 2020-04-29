package club.banyuan.zgMallMgt.dao;

import club.banyuan.zgMallMgt.dao.entity.UmsResourceCategory;
import club.banyuan.zgMallMgt.dto.UmsResourceCategoryResp;

import java.util.List;

public interface UmsResourceCategoryDao {
    int deleteByPrimaryKey(Long id);

    int insert(UmsResourceCategory record);

    int insertSelective(UmsResourceCategory record);

    UmsResourceCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsResourceCategory record);

    int updateByPrimaryKey(UmsResourceCategory record);

    List<UmsResourceCategory> selectAll();
}