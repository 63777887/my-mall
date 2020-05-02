package club.banyuan.zgMallMgt.service;

import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.dao.entity.UmsAdmin;
import club.banyuan.zgMallMgt.dto.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface AdminService {


    AdminLoginResp login(AdminLoginReq adminLoginReq);


    UserDetails getUserDetailsByToken(String token);

    AdminInfoResp getInfo(long adminId);

    ResponsePage list(Integer pageNum, Integer pageSize, String keyword);

    List<UmsRoleResp> getRoleByAdminId(Long adminId);

    Integer updateRole(Long adminId, List<Long> roleIds);

    Long update(UmsAdmin adminLoginReq, Long adminId);

    Long delete(Long adminId);

    UmsAdminResp register(UmsAdmin admin);

    Integer updateStatus(Integer status, Long adminId);

    void logout();

}
