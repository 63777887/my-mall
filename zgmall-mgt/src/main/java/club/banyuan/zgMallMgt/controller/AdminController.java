package club.banyuan.zgMallMgt.controller;

import club.banyuan.zgMallMgt.common.ResponResult;
import club.banyuan.zgMallMgt.dto.AdminLoginReq;
import club.banyuan.zgMallMgt.dto.AdminLoginResp;
import club.banyuan.zgMallMgt.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponResult login(@RequestBody AdminLoginReq adminLoginReq ){
        AdminLoginResp adminLoginResp=adminService.login(adminLoginReq);
        return ResponResult.success(adminLoginResp);
    }

    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @ResponseBody
    public ResponResult auth(Principal principal){
        long adminId = Long.parseLong(principal.getName());

        return ResponResult.success(adminService.getInfo(adminId));
    }
}
