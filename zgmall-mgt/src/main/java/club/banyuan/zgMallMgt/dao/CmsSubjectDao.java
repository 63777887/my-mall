package club.banyuan.zgMallMgt.dao;

import club.banyuan.zgMallMgt.dao.entity.CmsSubject;

import java.util.List;

public interface CmsSubjectDao {
    int deleteByPrimaryKey(Long id);

    int insert(CmsSubject record);

    int insertSelective(CmsSubject record);

    CmsSubject selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmsSubject record);

    int updateByPrimaryKey(CmsSubject record);

    List<CmsSubject> selectAll();
}