package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.dao.UmsMemberLevelDao;
import club.banyuan.zgMallMgt.dao.entity.UmsMemberLevel;
import club.banyuan.zgMallMgt.dto.UmsMemberLevelResp;
import club.banyuan.zgMallMgt.service.UmsMemberService;
import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UmsMemberServiceImpl implements UmsMemberService {

    @Autowired
    private UmsMemberLevelDao umsMemberLevelDao;

    @Override
    public List<UmsMemberLevelResp> list(Integer defaultStatus) {
        List<UmsMemberLevel> umsMemberLevels = umsMemberLevelDao.selectByDefaultStatus(defaultStatus);
        return umsMemberLevels.stream().map(t->{
            UmsMemberLevelResp umsMemberLevelResp = new UmsMemberLevelResp();
            BeanUtil.copyProperties(t, umsMemberLevelResp);
            return umsMemberLevelResp;
        }).collect(Collectors.toList());
    }
}
