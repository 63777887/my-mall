package club.banyuan.zgMallMgt.service;

import club.banyuan.zgMallMgt.dto.UmsMenuResp;
import club.banyuan.zgMallMgt.dto.UmsResourceResp;
import club.banyuan.zgMallMgt.dto.UmsRoleResp;

import java.util.List;

public interface UmsRoleService {

    List<UmsRoleResp> showList(Integer pageNum, Integer pageSize, String keyword);

    List<UmsRoleResp> showAllList();

    Long createUmsRole(UmsRoleResp umsRole);

    Long deleteUmsRoleById(Long ids);

    Long updateUmsRole(UmsRoleResp umsRoleResp, Long roleId);

    List<UmsResourceResp> showListResource(Long roleId);

    List<UmsMenuResp> listMenu(Long roleId);

    Integer allocMenu(Long roleId, List menuIds);

    Integer allocResource(Long roleId, List resourceIds);
}
