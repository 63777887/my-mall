package club.banyuan.zgMallMgt.controller;

import club.banyuan.zgMallMgt.common.ResponseResult;
import club.banyuan.zgMallMgt.dto.AdminLoginReq;
import club.banyuan.zgMallMgt.dto.AdminLoginResp;
import club.banyuan.zgMallMgt.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult login(@RequestBody @Valid AdminLoginReq adminLoginReq ){
        return ResponseResult.success(adminService.login(adminLoginReq));
    }

    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult auth(Principal principal){

        return ResponseResult.success(adminService.getInfo(Long.parseLong(principal.getName())));
    }
    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResponseResult list(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam(required = false) String keyword){
        return ResponseResult.setPages(pageNum,pageSize,adminService.list(pageNum, pageSize, keyword));
    }

}
