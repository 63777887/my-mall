package club.banyuan.demo.authorization.service;

import club.banyuan.demo.authorization.dto.AdminLoginReq;
import club.banyuan.demo.authorization.dto.AdminLoginResp;

public interface AdminService {


    AdminLoginResp login(AdminLoginReq adminLoginReq);

}
