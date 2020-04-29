package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.dao.CmsPrefrenceAreaDao;
import club.banyuan.zgMallMgt.dao.entity.CmsPrefrenceArea;
import club.banyuan.zgMallMgt.dto.CmsPrefrenceAreaResp;
import club.banyuan.zgMallMgt.service.CmsPrefrenceAreaService;
import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CmsPrefrenceAreaServiceImpl implements CmsPrefrenceAreaService {

    @Autowired
    private CmsPrefrenceAreaDao cmsPrefrenceAreaDao;

    @Override
    public List<CmsPrefrenceAreaResp> listAll() {
        List<CmsPrefrenceArea> cmsPrefrenceAreas = cmsPrefrenceAreaDao.selectAll();
        return cmsPrefrenceAreas.stream().map(t->{
            CmsPrefrenceAreaResp cmsPrefrenceAreaResp = new CmsPrefrenceAreaResp();
            BeanUtil.copyProperties(t, cmsPrefrenceAreaResp);
            return cmsPrefrenceAreaResp;
        }) .collect(Collectors.toList());
    }
}
