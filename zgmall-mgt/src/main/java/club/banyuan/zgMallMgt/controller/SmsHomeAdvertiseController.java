package club.banyuan.zgMallMgt.controller;

import club.banyuan.zgMallMgt.common.ResponseResult;
import club.banyuan.zgMallMgt.dao.entity.SmsHomeAdvertise;
import club.banyuan.zgMallMgt.service.SmsHomeAdvertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/home/advertise")
public class SmsHomeAdvertiseController {

    @Autowired
    private SmsHomeAdvertiseService smsHomeAdvertiseService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult list(@RequestParam("pageNum") Integer pageNum,
                               @RequestParam("pageSize") Integer pageSize,
                               @RequestParam(value = "name",required = false) String name,
                               @RequestParam(value = "type",required = false) Integer type,
                               @RequestParam(value = "endTime",required = false) Date endTime){
        return ResponseResult.success(smsHomeAdvertiseService.list(pageNum,pageSize,name,type,endTime));
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult create(@RequestBody @Valid SmsHomeAdvertise smsHomeAdvertise){
        return ResponseResult.success(smsHomeAdvertiseService.create(smsHomeAdvertise));
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getInfo(@PathVariable("id") Long id){
        return ResponseResult.success(smsHomeAdvertiseService.getInfo(id));
    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult update(@PathVariable("id") Long id,
                                 @RequestBody @Valid SmsHomeAdvertise smsHomeAdvertise){
        return ResponseResult.success(smsHomeAdvertiseService.update(id,smsHomeAdvertise));
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult delete(@RequestParam("ids") List<Long> ids){
        return ResponseResult.success(smsHomeAdvertiseService.delete(ids));
    }

    @RequestMapping(value = "/update/status/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult updateStatus(@PathVariable("id") Long id,
                                 @RequestParam("status") Integer status){
        return ResponseResult.success(smsHomeAdvertiseService.updateStatus(id,status));
    }

}
