package club.banyuan.zgMallMgt.controller;

import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.common.ResponseResult;
import club.banyuan.zgMallMgt.dao.entity.UmsAdmin;
import club.banyuan.zgMallMgt.dto.AdminLoginReq;
import club.banyuan.zgMallMgt.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

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
    public ResponseResult list(@RequestParam("pageNum") Integer pageNum,
                               @RequestParam("pageSize") Integer pageSize,
                               @RequestParam(required = false,value = "keyword") String keyword){
        return ResponseResult.success(adminService.list(pageNum,pageSize,keyword));

    }

    @ResponseBody
    @RequestMapping(value = "/role/{adminId}",method = RequestMethod.GET)
    public ResponseResult role(@PathVariable("adminId") Long adminId){
        return ResponseResult.success(adminService.getRoleByAdminId(adminId));
    }

    @RequestMapping(value = "role/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult updateRole(@RequestParam("adminId") Long adminId,
                                     @RequestParam("roleIds") List<Long> roleIds){
        return ResponseResult.success(adminService.updateRole(adminId,roleIds));
    }

    @RequestMapping(value = "/update/{adminId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult update(@RequestBody UmsAdmin admin ,
                                 @PathVariable("adminId") Long adminId){
        return ResponseResult.success(adminService.update(admin,adminId));
    }
    @RequestMapping(value = "/updateStatus/{adminId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult updateStatus(@RequestParam("status") Integer status ,
                                 @PathVariable("adminId") Long adminId){
        return ResponseResult.success(adminService.updateStatus(status,adminId));
    }

    @RequestMapping(value = "/delete/{adminId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult delete(@PathVariable("adminId") Long adminId){
        return ResponseResult.success(adminService.delete(adminId));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult register(@RequestBody UmsAdmin admin){
        return ResponseResult.success(adminService.register(admin));
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
//    @ResponseBody
    public ResponseResult logout(){
        adminService.logout();
        return ResponseResult.success();
    }
}
