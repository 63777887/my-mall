package club.banyuan.zgMallMgt.service;

import club.banyuan.zgMallMgt.dto.AdminLoginReq;
import club.banyuan.zgMallMgt.dto.AdminLoginResp;
import org.springframework.security.core.userdetails.UserDetails;

public interface AdminService {


    AdminLoginResp login(AdminLoginReq adminLoginReq);


    UserDetails getUserDetailsByToken(String token);
}
