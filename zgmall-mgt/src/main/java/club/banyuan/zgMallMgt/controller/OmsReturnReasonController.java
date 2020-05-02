package club.banyuan.zgMallMgt.controller;


import club.banyuan.zgMallMgt.common.ResponseResult;
import club.banyuan.zgMallMgt.dao.entity.OmsOrderReturnReason;
import club.banyuan.zgMallMgt.service.OmsOrderReturnReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/returnReason")
public class OmsReturnReasonController {

    @Autowired
    private OmsOrderReturnReasonService omsOrderReturnReasonService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult list(@RequestParam("pageNum") Integer pageNum,
                               @RequestParam("pageSize") Integer pageSize){
        return ResponseResult.success(omsOrderReturnReasonService.list(pageNum,pageSize));
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult create(@RequestBody @Valid OmsOrderReturnReason omsOrderReturnReason){
        return ResponseResult.success(omsOrderReturnReasonService.create(omsOrderReturnReason));
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getInfo(@PathVariable("id") Long id){
        return ResponseResult.success(omsOrderReturnReasonService.getInfo(id));
    }
    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult update(@RequestBody @Valid OmsOrderReturnReason omsOrderReturnReason,
                                 @PathVariable("id") Long id){
        return ResponseResult.success(omsOrderReturnReasonService.update(omsOrderReturnReason,id));
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult delete(@RequestParam("ids") List<Long> ids){
        return ResponseResult.success(omsOrderReturnReasonService.delete(ids));
    }
    @RequestMapping(value = "/update/status",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult updateStatus(@RequestParam("ids") List<Long> ids,
                                       @RequestParam("status") Integer status){
        return ResponseResult.success(omsOrderReturnReasonService.updateStatus(ids,status));
    }


}
