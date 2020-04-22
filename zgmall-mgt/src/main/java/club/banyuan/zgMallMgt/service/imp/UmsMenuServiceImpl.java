package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.common.ReqFailException;
import club.banyuan.zgMallMgt.common.ReqFailMessage;
import club.banyuan.zgMallMgt.dao.UmsMenuDao;
import club.banyuan.zgMallMgt.dao.UmsRoleDao;
import club.banyuan.zgMallMgt.dao.entity.UmsMenu;
import club.banyuan.zgMallMgt.dao.entity.UmsRole;
import club.banyuan.zgMallMgt.dto.UmsMenuResp;
import club.banyuan.zgMallMgt.dto.UmsMenuTreeNode;
import club.banyuan.zgMallMgt.service.UmsMenuService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UmsMenuServiceImpl implements UmsMenuService {

    @Autowired
    private UmsMenuDao umsMenuDao;

    @Autowired
    private UmsRoleDao umsRoleDao;


    @Override
    public List<UmsMenuResp> list(long adminId,Integer pageNum, Integer pageSize) {

        List<UmsRole> umsRoles = umsRoleDao.selectByAdminId(adminId);
        PageHelper.startPage(pageNum,pageSize);
        if (CollUtil.isEmpty(umsRoles)){
            throw new ReqFailException(ReqFailMessage.ROLE_IS_NULL);
        }
        List<UmsMenu> umsMenus = umsMenuDao.selectByRoleIdsGroupByLevel(umsRoles.stream().map(UmsRole::getId).collect(Collectors.toList()));
        return umsMenus.stream().map(t->{
            UmsMenuResp umsMenuResp = new UmsMenuResp();
            BeanUtil.copyProperties(t, umsMenuResp);
            return umsMenuResp;
        }).collect(Collectors.toList());
    }

    @Override
    public List<UmsMenuTreeNode> treeList() {
        List<UmsMenu> umsMenus = umsMenuDao.selectAll();
        ArrayList<UmsMenuTreeNode> rlt = new ArrayList<>();
        umsMenus.forEach(t->{
            if (t.getParentId() ==0){
                UmsMenuTreeNode umsMenuTreeNode = new UmsMenuTreeNode();
                BeanUtil.copyProperties(t, umsMenuTreeNode);
                umsMenuTreeNode.setChildren(new ArrayList<>());
                rlt.add(umsMenuTreeNode);
            }
        });

        rlt.forEach(parent->{
            umsMenus.forEach(sunNode->{
                if (parent.getId().equals(sunNode.getParentId())){
                    UmsMenuTreeNode umsMenuTreeNode = new UmsMenuTreeNode();
                    BeanUtil.copyProperties(sunNode, umsMenuTreeNode);
                    parent.getChildren().add(umsMenuTreeNode);
                }
            });
        });
        return rlt;
    }



}
