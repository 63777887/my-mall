package club.banyuan.zgMallMgt.service;

import club.banyuan.zgMallMgt.dao.entity.UmsAdmin;
import club.banyuan.zgMallMgt.dto.AdminInfoResp;
import club.banyuan.zgMallMgt.dto.AdminLoginReq;
import club.banyuan.zgMallMgt.dto.AdminLoginResp;
import club.banyuan.zgMallMgt.dto.UmsAdminResp;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface AdminService {


    AdminLoginResp login(AdminLoginReq adminLoginReq);


    UserDetails getUserDetailsByToken(String token);

    AdminInfoResp getInfo(long adminId);

    List<UmsAdminResp> list(Integer pageNum, Integer pageSize, String keyword);
}
