package club.banyuan.zgMallMgt.service;

import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dao.entity.UmsMenu;
import club.banyuan.zgMallMgt.dto.UmsMenuResp;
import club.banyuan.zgMallMgt.dto.UmsMenuTreeNode;

import java.util.List;

public interface UmsMenuService {

    ResponsePage list(Integer pageNum, Integer pageSize, Long menuId);

    List<UmsMenuTreeNode> treeList();



    UmsMenuResp getMenuInfoByid(Long id);

    long update(Long id, UmsMenu umsMenu);

    long create(UmsMenu umsMenu);

    Integer delete(Long menuId);

    Integer updateHidden(Long menuId, Integer hidden);
}
