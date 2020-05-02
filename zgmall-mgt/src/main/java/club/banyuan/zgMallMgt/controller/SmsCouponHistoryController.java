package club.banyuan.zgMallMgt.controller;

import club.banyuan.zgMallMgt.common.ResponseResult;
import club.banyuan.zgMallMgt.service.SmsCouponHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/couponHistory")
@RestController
public class SmsCouponHistoryController {

    @Autowired
    private SmsCouponHistoryService smsCouponHistoryService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult list(@RequestParam("pageNum") Integer pageNum,
                               @RequestParam("pageSize") Integer pageSize,
                               @RequestParam("couponId") Long couponId,
                               @RequestParam(value = "useStatus",required = false) Integer useStatus,
                               @RequestParam(value = "orderSn",required = false) String orderSn){

        return ResponseResult.success(smsCouponHistoryService.list(pageNum,pageSize,couponId,useStatus,orderSn));
    }
}
