package club.banyuan.zgMallMgt.service;


import club.banyuan.zgMallMgt.dao.entity.UmsResource;
import club.banyuan.zgMallMgt.dto.UmsResourceResp;

import java.util.List;


public interface UmsResourceService {

    List<UmsResource> getAllResource();
    List<UmsResource> getResourcesByAdminId(Long id);

    List<UmsResourceResp> list(Integer pageSize, Integer pageNum, String nameKeyword, String urlKeyword, Long categoryId);

}
