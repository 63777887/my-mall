package club.banyuan.zgMallMgt.controller;

import club.banyuan.zgMallMgt.common.ResponseResult;
import club.banyuan.zgMallMgt.dao.entity.OmsOrderSetting;
import club.banyuan.zgMallMgt.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderSetting")
public class OmsOrderSettingController {


    @Autowired
    private OrderSettingService orderSettingService;

    @RequestMapping(value = "/{orderSettingId}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult showOrderSetting(@PathVariable("orderSettingId") Long orderSettingId){
        return ResponseResult.success(orderSettingService.showOrderSetting(orderSettingId));
    }

    @RequestMapping(value = "/update/{orderSettingId}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult update(@PathVariable("orderSettingId") Long orderSettingId,
                                 @RequestBody OmsOrderSetting omsOrderSetting){
        return ResponseResult.success(orderSettingService.update(orderSettingId,omsOrderSetting));
    }
}
