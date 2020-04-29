package club.banyuan.zgMallMgt.controller;

import club.banyuan.zgMallMgt.common.ResponseResult;
import club.banyuan.zgMallMgt.service.CmsSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subject")
public class CmsSubjectController {

    @Autowired
    private CmsSubjectService cmsSubjectService;

    @ResponseBody
    @RequestMapping(value = "/listAll",method = RequestMethod.GET)
    public ResponseResult list(){
        return ResponseResult.success(cmsSubjectService.listAll());
    }
}
