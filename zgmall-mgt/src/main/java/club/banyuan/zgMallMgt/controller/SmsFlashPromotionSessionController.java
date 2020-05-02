package club.banyuan.zgMallMgt.controller;

import club.banyuan.zgMallMgt.common.ResponseResult;
import club.banyuan.zgMallMgt.dao.entity.SmsFlashPromotionSession;
import club.banyuan.zgMallMgt.service.SmsFlashPromotionSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/flashSession")
public class SmsFlashPromotionSessionController {

    @Autowired
    private SmsFlashPromotionSessionService smsFlashPromotionSessionService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult list(){
        return ResponseResult.success(smsFlashPromotionSessionService.list());
    }


    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult create(@RequestBody @Valid SmsFlashPromotionSession smsFlashPromotionSession){
        return ResponseResult.success(smsFlashPromotionSessionService.create(smsFlashPromotionSession));
    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult update(@RequestBody @Valid SmsFlashPromotionSession smsFlashPromotionSession,
                                 @PathVariable("id") Long id){
        return ResponseResult.success(smsFlashPromotionSessionService.update(smsFlashPromotionSession,id));
    }

    @RequestMapping(value = "/update/status/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult updateStatus(@RequestParam("status") Integer status,
                                 @PathVariable("id") Long id){
        return ResponseResult.success(smsFlashPromotionSessionService.updateStatus(status,id));
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult delete(@PathVariable("id") Long id){
        return ResponseResult.success(smsFlashPromotionSessionService.delete(id));
    }

    @RequestMapping(value = "/selectList",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult selectList(@RequestParam("flashPromotionId") Long flashPromotionId){
        return ResponseResult.success(smsFlashPromotionSessionService.selectList(flashPromotionId));
    }


}
