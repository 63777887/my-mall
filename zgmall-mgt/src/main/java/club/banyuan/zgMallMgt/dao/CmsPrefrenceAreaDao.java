package club.banyuan.zgMallMgt.dao;

import club.banyuan.zgMallMgt.dao.entity.CmsPrefrenceArea;

import java.util.List;

public interface CmsPrefrenceAreaDao {
    int deleteByPrimaryKey(Long id);

    int insert(CmsPrefrenceArea record);

    int insertSelective(CmsPrefrenceArea record);

    CmsPrefrenceArea selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmsPrefrenceArea record);

    int updateByPrimaryKeyWithBLOBs(CmsPrefrenceArea record);

    int updateByPrimaryKey(CmsPrefrenceArea record);

    List<CmsPrefrenceArea> selectAll();

}