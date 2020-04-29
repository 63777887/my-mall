package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.dao.CmsSubjectDao;
import club.banyuan.zgMallMgt.dao.entity.CmsSubject;
import club.banyuan.zgMallMgt.dto.CmsSubjectResp;
import club.banyuan.zgMallMgt.service.CmsSubjectService;
import cn.hutool.core.bean.BeanUtil;
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
}
