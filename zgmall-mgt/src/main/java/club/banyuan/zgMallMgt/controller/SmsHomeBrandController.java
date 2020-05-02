package club.banyuan.zgMallMgt.controller;

import club.banyuan.zgMallMgt.common.ResponseResult;
import club.banyuan.zgMallMgt.dao.entity.SmsHomeBrand;
import club.banyuan.zgMallMgt.dao.entity.SmsHomeNewProduct;
import club.banyuan.zgMallMgt.service.SmsHomeBrandService;
import club.banyuan.zgMallMgt.service.SmsHomeNewProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/home/brand")
public class SmsHomeBrandController {

    @Autowired
    private SmsHomeBrandService smsHomeBrandService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult list(@RequestParam("pageNum") Integer pageNum,
                               @RequestParam("pageSize") Integer pageSize,
                               @RequestParam(value = "brandName",required = false) String brandName,
                               @RequestParam(value = "recommendStatus",required = false) Integer recommendStatus){
        return ResponseResult.success(smsHomeBrandService.list(pageNum,pageSize,brandName,recommendStatus));
    }


    @RequestMapping(value = "/update/sort/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult updateSort(@RequestParam("sort") Integer sort,
                               @PathVariable("id") Long id){
        return ResponseResult.success(smsHomeBrandService.updateSort(sort,id));
    }

    @RequestMapping(value = "/update/recommendStatus",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult updateRecommendStatus(@RequestParam("ids") List<Long> ids,
                                     @RequestParam("recommendStatus") Integer recommendStatus){
        return ResponseResult.success(smsHomeBrandService.updateRecommendStatus(ids,recommendStatus));
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult delete(@RequestParam("ids") List<Long> ids){
        return ResponseResult.success(smsHomeBrandService.delete(ids));
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult create(@RequestBody @Valid List<SmsHomeBrand> smsHomeBrands){
        return ResponseResult.success(smsHomeBrandService.create(smsHomeBrands));
    }

}
