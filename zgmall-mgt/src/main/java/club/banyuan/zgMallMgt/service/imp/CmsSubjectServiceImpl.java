package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dao.CmsSubjectDao;
import club.banyuan.zgMallMgt.dao.entity.CmsSubject;
import club.banyuan.zgMallMgt.dao.entity.CmsSubjectExample;
import club.banyuan.zgMallMgt.dto.CmsSubjectResp;
import club.banyuan.zgMallMgt.service.CmsSubjectService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CmsSubjectServiceImpl implements CmsSubjectService {

    @Autowired
    private CmsSubjectDao cmsSubjectDao;

    @Override
    public List<CmsSubjectResp> listAll() {
        List<CmsSubject> cmsSubjects = cmsSubjectDao.selectAll();
        return cmsSubjects.stream().map(t->{
            CmsSubjectResp cmsSubjectResp = new CmsSubjectResp();
            BeanUtil.copyProperties(t, cmsSubjectResp);
            return cmsSubjectResp;
        }).collect(Collectors.toList());

    }

    @Override
    public ResponsePage list(Integer pageNum, Integer pageSize, String keyword) {
        PageHelper.startPage(pageNum, pageSize);
        CmsSubjectExample cmsSubjectExample = new CmsSubjectExample();
        CmsSubjectExample.Criteria criteria = cmsSubjectExample.createCriteria();
        if (StrUtil.isNotBlank(keyword)){
            criteria.andTitleLike(StrUtil.concat(true, "%",keyword,"%"));
        }
        List<CmsSubject> cmsSubjects = cmsSubjectDao.selectByExample(cmsSubjectExample);
        PageInfo<CmsSubject> pageInfo = new PageInfo<>(cmsSubjects);
        List<CmsSubjectResp> collect = cmsSubjects.stream().map(t -> {
            CmsSubjectResp cmsSubjectResp = new CmsSubjectResp();
            BeanUtil.copyProperties(t, cmsSubjectResp);
            return cmsSubjectResp;
        }).collect(Collectors.toList());
        return ResponsePage.setPages(pageInfo,collect);
    }
}
