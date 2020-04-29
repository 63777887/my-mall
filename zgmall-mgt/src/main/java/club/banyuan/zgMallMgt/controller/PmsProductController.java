package club.banyuan.zgMallMgt.controller;

import club.banyuan.zgMallMgt.common.ResponseResult;
import club.banyuan.zgMallMgt.dao.entity.*;
import club.banyuan.zgMallMgt.dto.CreateProductReq;
import club.banyuan.zgMallMgt.service.PmsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/product")
public class PmsProductController {

    @Autowired
    private PmsProductService pmsProductService;

    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResponseResult list(@RequestParam("pageNum") Integer pageNum,
                               @RequestParam("pageSize") Integer pageSize,
                               @RequestParam(value = "keyword",required=false) String keyword,
                               @RequestParam(value = "productSn",required = false) String productSn,
                               @RequestParam(value = "productCategoryId",required = false) Long productCategoryId,
                               @RequestParam(value = "brandId",required = false) Long brandId){
            return ResponseResult.success(pmsProductService.list(pageNum,pageSize,keyword,productSn,productCategoryId,brandId));
    }

    @ResponseBody
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseResult create(@RequestBody CreateProductReq createProductReq){
        return ResponseResult.success(pmsProductService.create(createProductReq));
    }
    @ResponseBody
    @RequestMapping(value = "/update/deleteStatus",method = RequestMethod.POST)
    public ResponseResult deleteStatu(@RequestParam("ids") List<Long> ids,
                                      @RequestParam("deleteStatus") Integer deleteStatus){
        return ResponseResult.success(pmsProductService.delete(ids,deleteStatus));
    }


    @RequestMapping(value = "/updateInfo/{productId}",method = RequestMethod.GET)
    public ResponseResult updateInfo(@PathVariable("productId") Long productId){
        return ResponseResult.success(pmsProductService.updateInfo(productId));
    }

    @ResponseBody
    @RequestMapping(value = "/update/{productId:^[0-9]+$}",method = RequestMethod.POST)
    public ResponseResult update(@RequestBody CreateProductReq createProductReq,
                                 @PathVariable("productId") Long productId) throws IOException {
        return ResponseResult.success(pmsProductService.update(createProductReq,productId));
    }

    @ResponseBody
    @RequestMapping(value = "update/publishStatus",method = RequestMethod.POST)
    public ResponseResult publishStatus(@RequestParam("ids") List<Long> ids,
                                      @RequestParam("publishStatus") Integer publishStatus){
        return ResponseResult.success(pmsProductService.publishStatus(ids,publishStatus));
    }

    @ResponseBody
    @RequestMapping(value = "update/recommendStatus",method = RequestMethod.POST)
    public ResponseResult recommendStatus(@RequestParam("ids") List<Long> ids,
                                      @RequestParam("recommendStatus") Integer recommendStatus){
        return ResponseResult.success(pmsProductService.recommendStatus(ids,recommendStatus));
    }

    @ResponseBody
    @RequestMapping(value = "update/newStatus",method = RequestMethod.POST)
    public ResponseResult newStatus(@RequestParam("ids") List<Long> ids,
                                      @RequestParam("newStatus") Integer newStatus){
        return ResponseResult.success(pmsProductService.newStatus(ids,newStatus));
    }


}
