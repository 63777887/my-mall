package club.banyuan.zgMallMgt.controller;

import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.common.ResponseResult;
import club.banyuan.zgMallMgt.dto.UmsRoleResp;
import club.banyuan.zgMallMgt.service.UmsMenuService;
import club.banyuan.zgMallMgt.service.UmsResourceService;
import club.banyuan.zgMallMgt.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

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
        return ResponseResult.success(umsRoleService.showList(pageNum,pageSize,keyword));
    }

    @ResponseBody
    @RequestMapping(value = "/listAll",method = RequestMethod.GET)
    public ResponseResult allList(){
        return ResponseResult.success(umsRoleService.showAllList());
    }

    @ResponseBody
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseResult create(@RequestBody @Valid UmsRoleResp umsRoleResp){
        return ResponseResult.success(umsRoleService.createUmsRole(umsRoleResp));
    }

    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ResponseResult delete(@RequestParam @NotNull Long ids){
        return ResponseResult.success(umsRoleService.deleteUmsRoleById(ids));
    }

    @ResponseBody
    @RequestMapping(value = "/update/{roleId}",method = RequestMethod.POST)
    public ResponseResult update(@RequestBody @Valid UmsRoleResp umsRoleResp,
                                 @PathVariable("roleId") Long roleId){
        return ResponseResult.success(umsRoleService.updateUmsRole(umsRoleResp,roleId));
    }
    @ResponseBody
    @RequestMapping(value = "/listResource/{roleId}",method = RequestMethod.GET)
    public ResponseResult listResource(@PathVariable Long roleId){
        return ResponseResult.success(umsRoleService.showListResource(roleId));
    }
    @ResponseBody
    @RequestMapping(value = "/listMenu/{roleId}",method = RequestMethod.GET)
    public ResponseResult listMenu(@PathVariable Long roleId){
        return ResponseResult.success(umsRoleService.listMenu(roleId));
    }

    @ResponseBody
    @RequestMapping(value = "/allocMenu",method = RequestMethod.POST)
    public ResponseResult allocMenu(@RequestParam("roleId") Long roleId,
                                    @RequestParam("menuIds")List menuIds){
        return ResponseResult.success(umsRoleService.allocMenu(roleId,menuIds));
    }

    @ResponseBody
    @RequestMapping(value = "/allocResource",method = RequestMethod.POST)
    public ResponseResult allocResource(@RequestParam("roleId") Long roleId,
                                    @RequestParam("resourceIds")List resourceIds){
        return ResponseResult.success(umsRoleService.allocResource(roleId,resourceIds));
    }

    @ResponseBody
    @RequestMapping(value = "/updateStatus/{roleId}",method = RequestMethod.POST)
    public ResponseResult updateStatus(@PathVariable("roleId") Long roleId,
                                        @RequestParam("status")Integer status){
        return ResponseResult.success(umsRoleService.updateStatus(roleId,status));
    }


}
