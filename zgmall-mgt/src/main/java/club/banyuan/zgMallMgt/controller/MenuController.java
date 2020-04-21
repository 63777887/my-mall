package club.banyuan.zgMallMgt.controller;

import club.banyuan.zgMallMgt.common.ResponseResult;
import club.banyuan.zgMallMgt.service.UmsMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private UmsMenuService umsMenuService;

    @ResponseBody
    @RequestMapping(value = "/list/0",method = RequestMethod.GET)
    public ResponseResult list(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               Principal principal){
        long adminId = Long.parseLong(principal.getName());
        return ResponseResult.setPages(pageNum,pageSize, umsMenuService.list(adminId,pageNum, pageSize));
    }
}
