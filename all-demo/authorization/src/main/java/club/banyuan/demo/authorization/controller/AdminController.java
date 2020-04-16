package club.banyuan.demo.authorization.controller;

import club.banyuan.demo.authorization.common.ResponResult;
import club.banyuan.demo.authorization.dto.AdminLoginReq;
import club.banyuan.demo.authorization.dto.AdminLoginResp;
import club.banyuan.demo.authorization.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/auth",method = RequestMethod.GET)
    @ResponseBody
    public ResponResult auth(){
        return ResponResult.success("success");
    }
}
