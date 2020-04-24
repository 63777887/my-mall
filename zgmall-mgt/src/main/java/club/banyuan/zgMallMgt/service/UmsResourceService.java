package club.banyuan.zgMallMgt.service;


import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dao.entity.UmsResource;
import club.banyuan.zgMallMgt.dto.UmsResourceResp;
import com.github.pagehelper.PageInfo;

import javax.validation.Valid;
import java.util.List;


public interface UmsResourceService {

    List<UmsResource> getAllResource();
    List<UmsResource> getResourcesByAdminId(Long id);

    ResponsePage list(Integer pageNum, Integer pageSize, String nameKeyword, String urlKeyword, Long categoryId);

    List<UmsResourceResp> getlistAll();


    Integer update(@Valid UmsResource umsResource, Long resourceId);

    Integer create(UmsResource umsResource);

    Integer delete(Long resourceId);
}
