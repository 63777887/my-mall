package club.banyuan.zgMallMgt.controller;

import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.common.ResponseResult;
import club.banyuan.zgMallMgt.dao.entity.UmsMenu;
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
    @RequestMapping(value = "/list/{menuId}",method = RequestMethod.GET)
    public ResponseResult list(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @PathVariable Long menuId){
            return ResponseResult.success(umsMenuService.list(pageNum,pageSize,menuId));


    }

    @ResponseBody
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseResult MenuInfo(@PathVariable("id") Long id){
        return ResponseResult.success(umsMenuService.getMenuInfoByid(id));
    }



    @ResponseBody
    @RequestMapping(value = "/treeList",method = RequestMethod.GET)
    public ResponseResult treeList(){
        return ResponseResult.success(umsMenuService.treeList());
    }

    @ResponseBody
    @RequestMapping(value = "update/{id}",method = RequestMethod.POST)
    public ResponseResult update(@PathVariable("id") Long id,
                                 @RequestBody UmsMenu umsMenu){
        return ResponseResult.success(umsMenuService.update(id,umsMenu));
    }


    @ResponseBody
    @RequestMapping(value = "create",method = RequestMethod.POST)
    public ResponseResult create(@RequestBody UmsMenu umsMenu){
        return ResponseResult.success(umsMenuService.create(umsMenu));
    }

    @ResponseBody
    @RequestMapping(value = "delete/{menuId}",method = RequestMethod.POST)
    public ResponseResult delete(@PathVariable Long menuId){
        return ResponseResult.success(umsMenuService.delete(menuId));
    }
}
