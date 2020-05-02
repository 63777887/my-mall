package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.dao.OmsCompanyAddressDao;
import club.banyuan.zgMallMgt.dao.entity.OmsCompanyAddress;
import club.banyuan.zgMallMgt.dto.OmsCompanyAddressResp;
import club.banyuan.zgMallMgt.service.OmsCompanyAddressService;
import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OmsCompanyAddressServiceImpl implements OmsCompanyAddressService {

    @Autowired
    private OmsCompanyAddressDao omsCompanyAddressDao;

    @Override
    public List<OmsCompanyAddressResp> list() {
        List<OmsCompanyAddress> omsCompanyAddresses = omsCompanyAddressDao.selectAll();
        return omsCompanyAddresses.stream().map(t->{
            OmsCompanyAddressResp omsCompanyAddressResp = new OmsCompanyAddressResp();
            BeanUtil.copyProperties(t,omsCompanyAddressResp);
            return omsCompanyAddressResp;
        }).collect(Collectors.toList());
    }
}
