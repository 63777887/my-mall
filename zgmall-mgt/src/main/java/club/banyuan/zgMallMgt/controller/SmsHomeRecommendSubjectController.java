package club.banyuan.zgMallMgt.controller;

import club.banyuan.zgMallMgt.common.ResponseResult;
import club.banyuan.zgMallMgt.dao.entity.SmsHomeRecommendSubject;
import club.banyuan.zgMallMgt.service.SmsHomeRecommendSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/home/recommendSubject")
public class SmsHomeRecommendSubjectController {

    @Autowired
    private SmsHomeRecommendSubjectService smsHomeRecommendSubjectService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult list(@RequestParam("pageNum") Integer pageNum,
                               @RequestParam("pageSize") Integer pageSize,
                               @RequestParam(value = "subjectName",required = false) String subjectName,
                               @RequestParam(value = "recommendStatus",required = false) Integer recommendStatus){
        return ResponseResult.success(smsHomeRecommendSubjectService.list(pageNum,pageSize,subjectName,recommendStatus));
    }


    @RequestMapping(value = "/update/sort/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult updateSort(@RequestParam("sort") Integer sort,
                               @PathVariable("id") Long id){
        return ResponseResult.success(smsHomeRecommendSubjectService.updateSort(sort,id));
    }

    @RequestMapping(value = "/update/recommendStatus",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult updateRecommendStatus(@RequestParam("ids") List<Long> ids,
                                     @RequestParam("recommendStatus") Integer recommendStatus){
        return ResponseResult.success(smsHomeRecommendSubjectService.updateRecommendStatus(ids,recommendStatus));
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult delete(@RequestParam("ids") List<Long> ids){
        return ResponseResult.success(smsHomeRecommendSubjectService.delete(ids));
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult create(@RequestBody @Valid List<SmsHomeRecommendSubject> smsHomeRecommendSubjects){
        return ResponseResult.success(smsHomeRecommendSubjectService.create(smsHomeRecommendSubjects));
    }

}
