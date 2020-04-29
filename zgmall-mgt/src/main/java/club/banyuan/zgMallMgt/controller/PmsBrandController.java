package club.banyuan.zgMallMgt.controller;

import club.banyuan.zgMallMgt.common.ResponseResult;
import club.banyuan.zgMallMgt.dao.entity.PmsBrand;
import club.banyuan.zgMallMgt.service.PmsBrandService;
import club.banyuan.zgMallMgt.service.PmsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/brand")
public class PmsBrandController {

    @Autowired
    private PmsBrandService pmsBrandService;

    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResponseResult list(@RequestParam(value = "keyword",required = false) String keyword,
                               @RequestParam("pageNum") Integer pageNum,
                               @RequestParam("pageSize") Integer pageSize){
            return ResponseResult.success(pmsBrandService.list(pageNum,pageSize,keyword));
    }

    @ResponseBody
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseResult create(@RequestBody @Valid PmsBrand pmsBrand){
            return ResponseResult.success(pmsBrandService.create(pmsBrand));
    }
    @ResponseBody
    @RequestMapping(value = "/{brandId}",method = RequestMethod.GET)
    public ResponseResult pmsBrandInfo(@PathVariable("brandId") Long brandId){
        return ResponseResult.success(pmsBrandService.pmsBrandInfo(brandId));
    }

    @ResponseBody
    @RequestMapping(value = "/update/{brandId:^[0-9]+$}",method = RequestMethod.POST)
    public ResponseResult update(@PathVariable("brandId") Long brandId,
                                 @RequestBody @Valid PmsBrand pmsBrand){
        return ResponseResult.success(pmsBrandService.update(brandId,pmsBrand));
    }

    @ResponseBody
    @RequestMapping(value = "/update/showStatus",method = RequestMethod.POST)
    public ResponseResult showStatus(@RequestParam("ids") List<Long> brandIds,
                                     @RequestParam("showStatus") Integer showStatus){
        return ResponseResult.success(pmsBrandService.showStatus(brandIds,showStatus));
    }

    @ResponseBody
    @RequestMapping(value = "/update/factoryStatus",method = RequestMethod.POST)
    public ResponseResult factoryStatus(@RequestParam("ids") List<Long> brandIds,
                                     @RequestParam("factoryStatus") Integer factoryStatus){
        return ResponseResult.success(pmsBrandService.factoryStatus(brandIds,factoryStatus));
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{brandId}",method = RequestMethod.GET)
    public ResponseResult delete(@PathVariable("brandId") Long brandId) throws IOException {
        return ResponseResult.success(pmsBrandService.delete(brandId));
    }



}
