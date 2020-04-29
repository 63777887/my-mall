package club.banyuan.zgMallMgt.controller;

import club.banyuan.zgMallMgt.common.ReqFailException;
import club.banyuan.zgMallMgt.common.ResponseResult;
import club.banyuan.zgMallMgt.service.OssFileService;
import club.banyuan.zgMallMgt.utils.MinioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

import static club.banyuan.zgMallMgt.common.FailReason.OSS_UPLOAD_FAIL;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private OssFileService ossFileService;


    @RequestMapping(method = RequestMethod.POST,value = "/image/upload")
    @ResponseBody
    public ResponseResult upload(@RequestParam("file") MultipartFile file, Principal principal){
        String subject = principal.getName();
        String filename = file.getOriginalFilename();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        String objectName = simpleDateFormat.format(new Date())+"/"+subject+"/"+filename;

        try {
            return ResponseResult.success(ossFileService.save(objectName, file.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new ReqFailException(OSS_UPLOAD_FAIL);
        }
    }

    @RequestMapping(value = "/image/download",method = RequestMethod.GET)
    public String download(@RequestParam("filename")String filename) throws IOException {

        InputStream download = ossFileService.download(filename);
        if (download==null){
            return "没有该文件";
        }else {
            Files.copy(download, Paths.get(MinioUtil.SAVE_IMG_TO_LOACL, filename));
            return "success";
        }

    }

    @RequestMapping(method = RequestMethod.POST,value = "/image/delete")
    @ResponseBody
    public String upload(@RequestParam("objectName") String objectName){

        try {
        ossFileService.delete(objectName);
        return "success";
        }catch (IOException e){
            e.printStackTrace();
        }

        return "fail";

    }


}
