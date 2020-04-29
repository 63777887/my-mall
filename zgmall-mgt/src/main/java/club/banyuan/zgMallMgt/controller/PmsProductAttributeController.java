package club.banyuan.zgMallMgt.controller;

import club.banyuan.zgMallMgt.common.ResponseResult;
import club.banyuan.zgMallMgt.dao.entity.PmsProductAttribute;
import club.banyuan.zgMallMgt.service.PmsProductAttributeCategoryService;
import club.banyuan.zgMallMgt.service.PmsProductAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/productAttribute")
public class PmsProductAttributeController {

    @Autowired
    private PmsProductAttributeService productAttributeService;

    @ResponseBody
    @RequestMapping(value = "/list/{productAttributeCategoryId}",method = RequestMethod.GET)
    public ResponseResult list(@RequestParam("pageNum") Integer pageNum,
                               @RequestParam("pageSize") Integer pageSize,
                               @RequestParam("type") Integer type,
                               @PathVariable("productAttributeCategoryId") Long productAttributeCategoryId){
        return ResponseResult.success(productAttributeService.list(pageNum,pageSize,type,productAttributeCategoryId));
    }

    @ResponseBody
    @RequestMapping(value = "/attrInfo/{productCategoryId}",method = RequestMethod.GET)
    public ResponseResult attrInfo(@PathVariable("productCategoryId") Long productCategoryId){
        return ResponseResult.success(productAttributeService.attrInfo(productCategoryId));
    }

    @ResponseBody
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseResult create(@RequestBody @Valid PmsProductAttribute pmsProductAttribute){
        return ResponseResult.success(productAttributeService.create(pmsProductAttribute));
    }

    @ResponseBody
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseResult showAttr(@PathVariable("id") Long id){
        return ResponseResult.success(productAttributeService.showAttr(id));
    }

    @ResponseBody
    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    public ResponseResult update(@PathVariable("id") Long id,
                                 @RequestBody @Valid PmsProductAttribute pmsProductAttribute){
        return ResponseResult.success(productAttributeService.update(id,pmsProductAttribute));
    }

    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ResponseResult delete(@RequestParam("ids") List<Long> ids){
        return ResponseResult.success(productAttributeService.delete(ids));
    }
}
