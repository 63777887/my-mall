package club.banyuan.zgMallMgt.controller;

import club.banyuan.zgMallMgt.common.ResponseResult;
import club.banyuan.zgMallMgt.dto.CreateCouponReq;
import club.banyuan.zgMallMgt.service.SmsCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupon")
public class SmsCouponController {

    @Autowired
    private SmsCouponService smsCouponService;

    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResponseResult list(@RequestParam("pageNum") Integer pageNum,
                               @RequestParam("pageSize") Integer pageSize,
                               @RequestParam(value = "name",required = false) String name,
                               @RequestParam(value = "type",required = false) Integer type){
        return ResponseResult.success(smsCouponService.list(pageNum,pageSize,name,type));
    }
    @ResponseBody
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseResult create(@RequestBody CreateCouponReq createCouponReq){
        return ResponseResult.success(smsCouponService.create(createCouponReq));
    }

    @ResponseBody
    @RequestMapping(value = "/{couponId}",method = RequestMethod.GET)
    public ResponseResult getInfo(@PathVariable("couponId") Long couponId){
        return ResponseResult.success(smsCouponService.getInfo(couponId));
    }

    @ResponseBody
    @RequestMapping(value = "/update/{couponId}",method = RequestMethod.POST)
    public ResponseResult update(@PathVariable("couponId") Long couponId,
                                 @RequestBody CreateCouponReq createCouponReq){
        return ResponseResult.success(smsCouponService.update(couponId,createCouponReq));
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{couponId}",method = RequestMethod.POST)
    public ResponseResult delete(@PathVariable("couponId") Long couponId){
        return ResponseResult.success(smsCouponService.delete(couponId));
    }

}
