package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.dao.UmsRoleDao;
import club.banyuan.zgMallMgt.dao.entity.UmsRole;
import club.banyuan.zgMallMgt.dao.entity.UmsRoleExample;
import club.banyuan.zgMallMgt.dto.UmsRoleResp;
import club.banyuan.zgMallMgt.service.UmsRoleService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UmsRoleServiceImpl implements UmsRoleService {

    @Autowired
    private UmsRoleDao umsRoleDao;

    @Override
    public List<UmsRoleResp> list(Integer pageNum, Integer pageSize, String keyword) {

        UmsRoleExample umsRoleExample = new UmsRoleExample();
        if (keyword != null) {
            UmsRoleExample.Criteria criteria = umsRoleExample.createCriteria();
            criteria.andNameLike(StrUtil.concat(true, "%",keyword,"%"));

        }
        PageHelper.startPage(pageNum,pageSize);
        List<UmsRole> umsRoles = umsRoleDao.selectByExample(umsRoleExample);
        return umsRoles.stream().map(t-> {
            UmsRoleResp umsRoleResp = new UmsRoleResp();
            BeanUtil.copyProperties(t, umsRoleResp);
            return umsRoleResp;
        }).collect(Collectors.toList());
    }

    @Override
    public List<UmsRoleResp> allList() {
        List<UmsRole> umsRoles = umsRoleDao.selectAll();
        return umsRoles.stream().map(t-> {
            UmsRoleResp umsRoleResp = new UmsRoleResp();
            BeanUtil.copyProperties(t, umsRoleResp);
            return umsRoleResp;
        }).collect(Collectors.toList());
    }
}
