package club.banyuan.zgMallMgt.service;

import club.banyuan.zgMallMgt.dto.UmsRoleResp;

import java.util.List;

public interface UmsRoleService {

    List<UmsRoleResp> list(Integer pageNum, Integer pageSize, String keyword);

    List<UmsRoleResp> allList();
}
