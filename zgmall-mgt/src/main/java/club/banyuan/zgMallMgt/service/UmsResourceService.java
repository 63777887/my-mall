package club.banyuan.zgMallMgt.service;


import club.banyuan.zgMallMgt.dao.entity.UmsResource;

import java.util.List;


public interface UmsResourceService {
    List<UmsResource> getAllResource();
    List<UmsResource> getResourcesByAdminId(Long id);
}
