package club.banyuan.demo.authorization.service.impl;

import club.banyuan.demo.authorization.dao.UmsResourceDao;
import club.banyuan.demo.authorization.dao.entity.UmsResource;
import club.banyuan.demo.authorization.service.UmsResourceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UmsResourceServiceImpl implements UmsResourceService {

    @Autowired
    private UmsResourceDao umsResourceDao;

    @Override
    public List<UmsResource> getAllResource() {
        return umsResourceDao.selectAll();
    }
}
