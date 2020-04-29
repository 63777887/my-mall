package club.banyuan.zgMallMgt.service.impl;


import club.banyuan.zgMallMgt.service.OssFileService;
import club.banyuan.zgMallMgt.utils.MinioUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MinioOssFileServiceImpTest {

    private final String OBJECT_NAME = "unit_test/unit_test.jpg";

    @Autowired
    private OssFileService ossFileService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void uploadTest() throws IOException {
        InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream("http-client/test.jpg");

        ossFileService.save(OBJECT_NAME, inputStream);

//        Assert.assertTrue(url.endsWith(OBJECT_NAME));
        Assert.assertTrue(ossFileService.isExist(OBJECT_NAME));
    }
    @Test
    public void uploadTest1() throws IOException {
        InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream("http-client/test.jpg");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        String objectName = simpleDateFormat.format(new Date())+"/"+"ceshi";

        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writer().writeValueAsString(ossFileService.save(objectName, inputStream));
        System.out.println(s);

//        Assert.assertTrue(url.endsWith(OBJECT_NAME));
//        Assert.assertTrue(ossFileService.isExist(OBJECT_NAME));
    }
    @Test
    public void deleteTest() throws IOException {
//        ossFileService.delete("20200414/test.jpg");
//        Assert.assertFalse(ossFileService.isExist(OBJECT_NAME));
        String filename = "aa.jpg";

        String[] split = filename.split("\\.");
        System.out.println(Arrays.toString(split));

        String newFilename=passwordEncoder.encode(split[0])+"."+split[1];
        System.out.println(newFilename);
    }

    @Test
    public void downloadTest() throws IOException {
        Files.copy(ossFileService.download("20200414/test.jpg"), Paths.get(MinioUtil.SAVE_IMG_TO_LOACL, "down"));

//        Assert.assertFalse(ossFileService.isExist(OBJECT_NAME));

    }

    @Test
    public void test() throws IOException {
        String url="http://127.0.0.1:9000/dev/20200429/haiyang1.jpg";
        ossFileService.delete(url);
    }

}
