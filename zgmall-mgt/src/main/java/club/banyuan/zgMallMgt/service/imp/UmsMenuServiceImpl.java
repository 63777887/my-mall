package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.common.ReqFailException;
import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dao.UmsMenuDao;
import club.banyuan.zgMallMgt.dao.UmsRoleDao;
import club.banyuan.zgMallMgt.dao.entity.UmsMenu;
import club.banyuan.zgMallMgt.dto.UmsMenuResp;
import club.banyuan.zgMallMgt.dto.UmsMenuTreeNode;
import club.banyuan.zgMallMgt.service.UmsMenuService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static club.banyuan.zgMallMgt.common.FailReason.*;

@Service
public class UmsMenuServiceImpl implements UmsMenuService {

    @Autowired
    private UmsMenuDao umsMenuDao;

    @Autowired
    private UmsRoleDao umsRoleDao;


    @Override
    public ResponsePage list(Integer pageNum, Integer pageSize, Long menuId) {
        PageHelper.startPage(pageNum,pageSize);
        List<UmsMenu> umsMenus = umsMenuDao.selectByParentId(menuId);
        PageInfo<UmsMenu> pageInfo = new PageInfo<>(umsMenus);
        List<UmsMenuResp> collect = umsMenus.stream().map(t -> {
            UmsMenuResp umsMenuResp = new UmsMenuResp();
            BeanUtil.copyProperties(t, umsMenuResp);
            return umsMenuResp;
        }).collect(Collectors.toList());
        return ResponsePage.setPages(pageInfo, collect);
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


    @Override
    public UmsMenuResp getMenuInfoByid(Long id) {
        UmsMenu umsMenu = umsMenuDao.selectByPrimaryKey(id);
        if (ObjectUtil.isEmpty(umsMenu)){
            throw new ReqFailException(UMS_ROLE_MENU_REL_ILLEGAL);
        }
        UmsMenuResp umsMenuResp = new UmsMenuResp();
        BeanUtil.copyProperties(umsMenu, umsMenuResp);
        return umsMenuResp;
    }

    @Override
    public long update(Long id, UmsMenu umsMenu) {
        UmsMenu menu = umsMenuDao.selectByPrimaryKey(id);
        if (ObjectUtil.isEmpty(menu)){
            throw new ReqFailException(UMS_ROLE_MENU_REL_ILLEGAL);
        }
        umsMenu.setId(id);
        return umsMenuDao.updateByPrimaryKey(umsMenu);

    }

    @Override
    public long create(UmsMenu umsMenu) {
        List<UmsMenu> umsMenus = umsMenuDao.selectAll();
        umsMenus.forEach(t->{
            if (t.getTitle().equals(umsMenu.getTitle()) || t.getName().equals(umsMenu.getName())){
                throw new ReqFailException(UMS_ROLE_MENU_DUPLICATE);
            }
        });
        umsMenu.setCreateTime(new Date());
        if(umsMenu.getParentId()==0){
            umsMenu.setLevel(0);
        }else {
            umsMenu.setLevel(1);
        }
        return umsMenuDao.insert(umsMenu);

    }

    @Override
    public Integer delete(Long menuId) {
        UmsMenu menu = umsMenuDao.selectByPrimaryKey(menuId);
        if (ObjectUtil.isEmpty(menu)){
            throw new ReqFailException(UMS_ADMIN_MENU_NOT_EXIST);
        }
        return umsMenuDao.deleteByPrimaryKey(menuId);

    }


}
