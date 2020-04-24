package club.banyuan.zgMallMgt.controller;

import club.banyuan.zgMallMgt.common.ResponseResult;
import club.banyuan.zgMallMgt.dao.entity.UmsResourceCategory;
import club.banyuan.zgMallMgt.service.UmsResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/resourceCategory")
public class ResourceCategoryController {

    @Autowired
    private UmsResourceCategoryService umsResourceCategoryService;

    @ResponseBody
    @RequestMapping(value = "/listAll",method = RequestMethod.GET)
    public ResponseResult allList(){
        return ResponseResult.success(umsResourceCategoryService.allList());
    }


    @ResponseBody
    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    public ResponseResult update(@RequestBody UmsResourceCategory umsResourceCategory,
                                 @PathVariable("id") Long id){
        return ResponseResult.success(umsResourceCategoryService.update(umsResourceCategory,id));
    }

    @ResponseBody
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseResult create(@RequestBody @Valid UmsResourceCategory umsResourceCategory){
        return ResponseResult.success(umsResourceCategoryService.insert(umsResourceCategory));
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    public ResponseResult delete(@PathVariable("id") Long id){
        return ResponseResult.success(umsResourceCategoryService.delete(id));
    }

}
