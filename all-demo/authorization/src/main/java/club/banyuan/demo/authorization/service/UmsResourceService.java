package club.banyuan.demo.authorization.service;

import club.banyuan.demo.authorization.dao.entity.UmsResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UmsResourceService {
    List<UmsResource> getAllResource();
    List<UmsResource> getResourcesByAdminId(Long id);
}
