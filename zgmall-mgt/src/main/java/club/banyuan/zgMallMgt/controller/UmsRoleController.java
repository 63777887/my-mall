package club.banyuan.zgMallMgt.controller;

import club.banyuan.zgMallMgt.common.ResponseResult;
import club.banyuan.zgMallMgt.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class UmsRoleController {

    @Autowired
    private UmsRoleService umsRoleService;


    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResponseResult list(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam(required = false) String keyword){
        return ResponseResult.setPages(pageNum,pageSize,umsRoleService.list(pageNum, pageSize, keyword));
    }

    @ResponseBody
    @RequestMapping(value = "/listAll",method = RequestMethod.GET)
    public ResponseResult allList(){
        return ResponseResult.success(umsRoleService.allList());
    }
}
