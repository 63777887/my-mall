package club.banyuan.zgMallMgt.controller;

import club.banyuan.zgMallMgt.common.ResponseResult;
import club.banyuan.zgMallMgt.dao.entity.PmsProductCategory;
import club.banyuan.zgMallMgt.dto.PmsProductCategoryWithListResp;
import club.banyuan.zgMallMgt.service.PmsProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/productCategory")
public class PmsProductCategoryController {

    @Autowired
    private PmsProductCategoryService pmsProductCategoryService;

    @RequestMapping(value = "/list/withChildren",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult listWithChildren(){

        return ResponseResult.success(pmsProductCategoryService.listWithChildren());
    }
    @RequestMapping(value = "/list/{parentId}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult list(@RequestParam("pageNum") Integer pageNum,
                               @RequestParam("pageSize") Integer pageSize,
                               @PathVariable("parentId") Long parentId){

        return ResponseResult.success(pmsProductCategoryService.list(pageNum,pageSize,parentId));
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult create(@RequestBody @Valid PmsProductCategory pmsProductCategory){
        return ResponseResult.success(pmsProductCategoryService.create(pmsProductCategory));
    }

    @RequestMapping(value = "/{productCategoryId}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult showInfo(@PathVariable("productCategoryId") Long productCategoryId){
        return ResponseResult.success(pmsProductCategoryService.showInfo(productCategoryId));
    }

    @RequestMapping(value = "/update/{productCategoryId}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult update(@RequestBody @Valid PmsProductCategoryWithListResp pmsProductCategoryWithListResp,
                                 @PathVariable("productCategoryId") Long productCategoryId){
        return ResponseResult.success(pmsProductCategoryService.update(pmsProductCategoryWithListResp,productCategoryId));
    }


    @RequestMapping(value = "/update/navStatus",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult updateNavStatus(@RequestParam("ids") Long ids,
                                          @RequestParam("navStatus") Integer navStatus){
        return ResponseResult.success(pmsProductCategoryService.updateNavStatus(ids,navStatus));
    }

    @RequestMapping(value = "/update/showStatus",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult updateShowStatus(@RequestParam("ids") Long ids,
                                          @RequestParam("showStatus") Integer showStatus){
        return ResponseResult.success(pmsProductCategoryService.updateShowStatus(ids,showStatus));
    }


    @RequestMapping(value = "/delete/{productCategoryId}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult delete(@PathVariable("productCategoryId") Long productCategoryId){
        return ResponseResult.success(pmsProductCategoryService.delete(productCategoryId));
    }


}
