package club.banyuan.zgMallMgt.controller;

import club.banyuan.zgMallMgt.common.ResponseResult;
import club.banyuan.zgMallMgt.dao.entity.SmsFlashPromotionProductRelation;
import club.banyuan.zgMallMgt.dto.SmsFlashPromotionProductRelationReqWithProduct;
import club.banyuan.zgMallMgt.service.SmsFlashPromotionProductRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/flashProductRelation")
@RestController
public class SmsFlashPromotionProductRelationController {

    @Autowired
    private SmsFlashPromotionProductRelationService smsFlashPromotionProductRelationService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult list(@RequestParam("pageNum") Integer pageNum,
                               @RequestParam("pageSize") Integer pageSize,
                               @RequestParam("flashPromotionId") Long flashPromotionId,
                               @RequestParam("flashPromotionSessionId") Long flashPromotionSessionId){
        return ResponseResult.success(smsFlashPromotionProductRelationService.list(pageNum,pageSize,flashPromotionId,flashPromotionSessionId));
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult create(@RequestBody List<SmsFlashPromotionProductRelation> smsFlashPromotionProductRelations){
        return ResponseResult.success(smsFlashPromotionProductRelationService.create(smsFlashPromotionProductRelations));
    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult update(@RequestBody SmsFlashPromotionProductRelationReqWithProduct smsFlashPromotionProductRelationReqWithProduct,
                                 @PathVariable("id") Long id){
        return ResponseResult.success(smsFlashPromotionProductRelationService.update(id, smsFlashPromotionProductRelationReqWithProduct));
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult delete(@PathVariable("id") Long id){
        return ResponseResult.success(smsFlashPromotionProductRelationService.delete(id));
    }
}
