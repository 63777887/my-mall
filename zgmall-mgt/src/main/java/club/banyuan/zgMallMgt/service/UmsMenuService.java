package club.banyuan.zgMallMgt.service;

import club.banyuan.zgMallMgt.dto.UmsMenuResp;
import club.banyuan.zgMallMgt.dto.UmsMenuTreeNode;

import java.util.List;

public interface UmsMenuService {

    List<UmsMenuResp> list(long adminId, Integer pageNum, Integer pageSize);

    List<UmsMenuTreeNode> treeList();



}
