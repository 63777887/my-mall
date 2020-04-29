package club.banyuan.zgMallMgt.dao;

import club.banyuan.zgMallMgt.dao.entity.UmsMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsMenuDao {
    int deleteByPrimaryKey(Long id);

    int insert(UmsMenu record);

    int insertSelective(UmsMenu record);

    UmsMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsMenu record);

    int updateByPrimaryKey(UmsMenu record);

    List<UmsMenu> selectByRoleIds(List<Long> roleIds);

    List<UmsMenu> selectAll();

    List<UmsMenu> selectByParentId(Long menuId);

    int updateHiddenByMenuId(@Param("menuId") Long menuId,@Param("hidden") Integer hidden);
}