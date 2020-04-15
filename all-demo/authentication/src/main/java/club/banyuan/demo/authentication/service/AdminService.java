package club.banyuan.demo.authentication.service;

import club.banyuan.demo.authentication.dto.AdminLoginReq;
import club.banyuan.demo.authentication.dto.AdminLoginResp;

public interface AdminService {


    AdminLoginResp login(AdminLoginReq adminLoginReq);

}
