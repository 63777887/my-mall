package club.banyuan.zgMallMgt.controller;

import club.banyuan.zgMallMgt.common.ResponseResult;
import club.banyuan.zgMallMgt.dao.entity.SmsHomeNewProduct;
import club.banyuan.zgMallMgt.dao.entity.SmsHomeRecommendProduct;
import club.banyuan.zgMallMgt.service.SmsHomeNewProductService;
import club.banyuan.zgMallMgt.service.SmsHomeRecommendProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/home/newProduct")
public class SmsHomeNewProductController {

    @Autowired
    private SmsHomeNewProductService smsHomeNewProductService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult list(@RequestParam("pageNum") Integer pageNum,
                               @RequestParam("pageSize") Integer pageSize,
                               @RequestParam(value = "productName",required = false) String productName,
                               @RequestParam(value = "recommendStatus",required = false) Integer recommendStatus){
        return ResponseResult.success(smsHomeNewProductService.list(pageNum,pageSize,productName,recommendStatus));
    }


    @RequestMapping(value = "/update/sort/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult updateSort(@RequestParam("sort") Integer sort,
                               @PathVariable("id") Long id){
        return ResponseResult.success(smsHomeNewProductService.updateSort(sort,id));
    }

    @RequestMapping(value = "/update/recommendStatus",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult updateRecommendStatus(@RequestParam("ids") List<Long> ids,
                                     @RequestParam("recommendStatus") Integer recommendStatus){
        return ResponseResult.success(smsHomeNewProductService.updateRecommendStatus(ids,recommendStatus));
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult delete(@RequestParam("ids") List<Long> ids){
        return ResponseResult.success(smsHomeNewProductService.delete(ids));
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult create(@RequestBody @Valid List<SmsHomeNewProduct> smsHomeNewProducts){
        return ResponseResult.success(smsHomeNewProductService.create(smsHomeNewProducts));
    }

}
