package club.banyuan.demo.authentication.service.impl;

import club.banyuan.demo.authentication.dto.AdminLoginReq;
import club.banyuan.demo.authentication.dto.AdminLoginResp;
import club.banyuan.demo.authentication.service.AdminService;
import club.banyuan.demo.jwt.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class AdminServiceImpl implements AdminService {

    private static final String SCHEMA="Bearer";
    private static final String TOKEN_HEAD_KEY="Authorization";

    @Autowired
    private TokenService tokenService;

    @Override
    public AdminLoginResp login(AdminLoginReq adminLoginReq) {
        AdminLoginResp adminLoginResp = new AdminLoginResp();
        String token = tokenService.generateToken(adminLoginReq.getUsername());
        adminLoginResp.setToken(token);
        adminLoginResp.setTokenHead(SCHEMA);
        return adminLoginResp;
    }
}
