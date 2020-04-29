package club.banyuan.zgMallMgt.controller;

import club.banyuan.zgMallMgt.common.ResponseResult;
import club.banyuan.zgMallMgt.service.CmsPrefrenceAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prefrenceArea")
public class CmsPrefrenceAreaController {

    @Autowired
    private CmsPrefrenceAreaService cmsPrefrenceAreaService;

    @ResponseBody
    @RequestMapping(value = "/listAll",method = RequestMethod.GET)
    public ResponseResult list(){
        return ResponseResult.success(cmsPrefrenceAreaService.listAll());
    }
}
