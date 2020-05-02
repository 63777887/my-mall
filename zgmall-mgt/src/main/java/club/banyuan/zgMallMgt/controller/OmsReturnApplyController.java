package club.banyuan.zgMallMgt.controller;


import club.banyuan.zgMallMgt.common.ResponseResult;
import club.banyuan.zgMallMgt.dto.OmsUpdateApplyResp;
import club.banyuan.zgMallMgt.service.OmsOrderReturnApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/returnApply")
public class OmsReturnApplyController {

    @Autowired
    private OmsOrderReturnApplyService omsOrderReturnApplyService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult list(@RequestParam("pageNum") Integer pageNum,
                               @RequestParam("pageSize") Integer pageSize,
                               @RequestParam(value = "id", required = false) Long id,
                               @RequestParam(value = "status", required = false) Integer status,
                               @RequestParam(value = "createTime", required = false) Date createTime,
                               @RequestParam(value = "handleMan", required = false) String handleMan,
                               @RequestParam(value = "handleTime", required = false) Date handleTime){
        return ResponseResult.success(omsOrderReturnApplyService.list(pageNum,pageSize,id,status,createTime,handleMan,handleTime));
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getInfo(@PathVariable("id") Long id){
        return ResponseResult.success(omsOrderReturnApplyService.getInfo(id));
    }

    @RequestMapping(value = "/update/status/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult updateStatus(@PathVariable("id") Long id,
                                       @RequestBody OmsUpdateApplyResp omsUpdateApplyResp){
        return ResponseResult.success(omsOrderReturnApplyService.updateStatus(id,omsUpdateApplyResp));
    }
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult delete(@RequestParam("ids") List<Long> ids){
        return ResponseResult.success(omsOrderReturnApplyService.delete(ids));
    }

}
