package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.dao.UmsResourceDao;
import club.banyuan.zgMallMgt.dao.entity.UmsResource;
import club.banyuan.zgMallMgt.service.UmsResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmsResourceServiceImpl implements UmsResourceService {

    @Autowired
    private UmsResourceDao umsResourceDao;

    @Override
    public List<UmsResource> getAllResource() {
        return umsResourceDao.selectAll();
    }

    @Override
    public List<UmsResource> getResourcesByAdminId(Long id) {
        return umsResourceDao.selectByAdminId(id);
    }
}
