package club.banyuan.demo.authorization.service;

import club.banyuan.demo.authorization.dao.entity.UmsAdmin;
import club.banyuan.demo.authorization.dto.AdminLoginReq;
import club.banyuan.demo.authorization.dto.AdminLoginResp;
import org.springframework.security.core.userdetails.UserDetails;

public interface AdminService {


    AdminLoginResp login(AdminLoginReq adminLoginReq);


    UserDetails getUserDetailsByToken(String token);
}
