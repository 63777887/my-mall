package club.banyuan.zgMallMgt.controller;

import club.banyuan.zgMallMgt.common.ResponseResult;
import club.banyuan.zgMallMgt.dao.entity.SmsFlashPromotion;
import club.banyuan.zgMallMgt.service.SmsFlashProMotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/flash")
public class SmsFlashPromotionController {

    @Autowired
    private SmsFlashProMotionService smsFlashProMotionService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult list(@RequestParam("pageNum") Integer pageNum,
                               @RequestParam("pageSize") Integer pageSize,
                               @RequestParam(value = "keyword",required = false) String keyword){
        return ResponseResult.success(smsFlashProMotionService.list(pageNum,pageSize,keyword));
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult create(@RequestBody @Valid SmsFlashPromotion smsFlashPromotion){
        return ResponseResult.success(smsFlashProMotionService.create(smsFlashPromotion));
    }
    @RequestMapping(value = "/update/status/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult updateStatus(@PathVariable("id") Long id,
                                       @RequestParam("status") Integer status){
        return ResponseResult.success(smsFlashProMotionService.updateStatus(id,status));
    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult update(@PathVariable("id") Long id,
                                 @RequestBody @Valid SmsFlashPromotion smsFlashPromotion){
        return ResponseResult.success(smsFlashProMotionService.update(id,smsFlashPromotion));
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult delete(@PathVariable("id") Long id){
        return ResponseResult.success(smsFlashProMotionService.delete(id));
    }

}
