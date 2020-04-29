package club.banyuan.zgMallMgt.controller;

import club.banyuan.zgMallMgt.common.ResponseResult;
import club.banyuan.zgMallMgt.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/memberLevel")
public class MemberLevelController {

    @Autowired
    private UmsMemberService umsMemberService;

    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResponseResult list(@RequestParam(value = "defaultStatus") Integer defaultStatus){
        return ResponseResult.success(umsMemberService.list(defaultStatus));
    }
}
