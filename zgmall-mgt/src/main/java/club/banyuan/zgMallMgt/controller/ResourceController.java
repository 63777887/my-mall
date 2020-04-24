package club.banyuan.zgMallMgt.controller;

import club.banyuan.zgMallMgt.common.ResponsePage;
import club.banyuan.zgMallMgt.common.ResponseResult;
import club.banyuan.zgMallMgt.dao.entity.UmsResource;
import club.banyuan.zgMallMgt.dto.UmsResourceResp;
import club.banyuan.zgMallMgt.service.UmsResourceService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private UmsResourceService umsResourceService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult list(
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam(required = false) String nameKeyword,
            @RequestParam(required = false) String urlKeyword,
            @RequestParam(required = false) Long categoryId
    ) {
        ResponsePage list = umsResourceService.list(pageNum, pageSize, nameKeyword, urlKeyword, categoryId);
        return ResponseResult.success(list);
    }

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult listAll() {

        return ResponseResult.success(umsResourceService.getlistAll());
    }

    @RequestMapping(value = "/update/{resourceId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult update(@RequestBody @Valid UmsResource umsResource,
                                 @PathVariable Long resourceId) {

        return ResponseResult.success(umsResourceService.update(umsResource,resourceId));
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult create(@RequestBody @Valid UmsResource umsResource) {

        return ResponseResult.success(umsResourceService.create(umsResource));
    }

    @RequestMapping(value = "/delete/{resourceId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult delete(@PathVariable Long resourceId) {

        return ResponseResult.success(umsResourceService.delete(resourceId));
    }

}
