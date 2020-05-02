package club.banyuan.zgMallMgt.controller;

import club.banyuan.zgMallMgt.common.ResponseResult;
import club.banyuan.zgMallMgt.dto.DeliveryResp;
import club.banyuan.zgMallMgt.dto.OmsOrderInfoResp;
import club.banyuan.zgMallMgt.service.OmsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OmsOrderController {

    @Autowired
    private OmsOrderService omsOrderService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult list(@RequestParam("pageNum") Integer pageNum,
                               @RequestParam("pageSize") Integer pageSize,
                               @RequestParam(value = "orderSn", required = false) String orderSn,
                               @RequestParam(value = "receiverKeyword", required = false) String receiverKeyword,
                               @RequestParam(value = "status", required = false) Integer status,
                               @RequestParam(value = "orderType", required = false) Integer orderType,
                               @RequestParam(value = "sourceType", required = false) Integer sourceType,
                               @RequestParam(value = "createTime", required = false) Date createTime) {
        return ResponseResult.success(omsOrderService.list(pageNum, pageSize, orderSn, receiverKeyword, status, orderType, sourceType, createTime));
    }

    @RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult showOrderInfo(@PathVariable("orderId") Long orderId) {
        return ResponseResult.success(omsOrderService.showOrderInfo(orderId));
    }

    @RequestMapping(value = "/update/delivery", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult delivery(@RequestBody @Valid List<DeliveryResp> deliveryResp,
                                   Principal principal) {
        return ResponseResult.success(omsOrderService.delivery(deliveryResp, principal));
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseResult delete(@RequestParam("ids") List<Long> ids) {
        return ResponseResult.success(omsOrderService.delete(ids));
    }

    @ResponseBody
    @RequestMapping(value = "/update/close", method = RequestMethod.POST)
    public ResponseResult close(@RequestParam("ids") List<Long> ids,
                                @RequestParam("note") String note,
                                Principal principal) {
        return ResponseResult.success(omsOrderService.close(ids, note, principal));
    }

    @ResponseBody
    @RequestMapping(value = "/update/note", method = RequestMethod.POST)
    public ResponseResult note(@RequestParam("id") Long id,
                               @RequestParam("note") String note,
                               @RequestParam("status") Integer status) {
        return ResponseResult.success(omsOrderService.updateNote(id, note, status));
    }

}
