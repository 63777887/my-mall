package club.banyuan.zgMallMgt.dao;

import club.banyuan.zgMallMgt.dao.entity.UmsRole;
import club.banyuan.zgMallMgt.dao.entity.UmsRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsRoleDao {
    long countByExample(UmsRoleExample example);

    int deleteByExample(UmsRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsRole record);

    int insertSelective(UmsRole record);

    List<UmsRole> selectByExample(UmsRoleExample example);

    UmsRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsRole record, @Param("example") UmsRoleExample example);

    int updateByExample(@Param("record") UmsRole record, @Param("example") UmsRoleExample example);

    int updateByPrimaryKeySelective(UmsRole record);

    int updateByPrimaryKey(UmsRole record);

    List<UmsRole> selectByAdminId(Long adminId);

    List<UmsRole> selectAll();
    void updateRoleMenuRelationByRoleId(Long roleId, List menuIds);

    void deleteRoleMenuRelationByRoleId(Long roleId);

    void deleteRoleResourceRelationByRoleId(Long roleId);

    void insertRoleResourceRelationByRoleId(Long roleId, List resourceIds);

    void updateStatusByRoleId(Long roleId, Integer status);

    void deleteAdminROleRelationByAdminId(Long adminId);

    void insertAdminRoleRelationByAdminId(@Param("adminId") Long adminId,@Param("roleIds") List<Long> roleIds);

}