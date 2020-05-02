package club.banyuan.zgMallMgt.dao;

import club.banyuan.zgMallMgt.dao.entity.CmsSubject;
import club.banyuan.zgMallMgt.dao.entity.CmsSubjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsSubjectDao {
    long countByExample(CmsSubjectExample example);

    int deleteByExample(CmsSubjectExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CmsSubject record);

    int insertSelective(CmsSubject record);

    List<CmsSubject> selectByExample(CmsSubjectExample example);

    CmsSubject selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CmsSubject record, @Param("example") CmsSubjectExample example);

    int updateByExample(@Param("record") CmsSubject record, @Param("example") CmsSubjectExample example);

    int updateByPrimaryKeySelective(CmsSubject record);

    int updateByPrimaryKey(CmsSubject record);

    List<CmsSubject> selectAll();
}