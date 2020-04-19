package club.banyuan.zgMallMgt.dao;


import club.banyuan.zgMallMgt.dao.entity.UmsResource;

import java.util.List;

public interface UmsResourceDao {
    int deleteByPrimaryKey(Long id);

    int insert(UmsResource record);

    int insertSelective(UmsResource record);

    UmsResource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsResource record);

    int updateByPrimaryKey(UmsResource record);
    List<UmsResource> selectAll();

    List<UmsResource> selectByAdminId(Long id);
}