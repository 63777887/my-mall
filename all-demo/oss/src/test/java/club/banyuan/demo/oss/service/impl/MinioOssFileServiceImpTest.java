package club.banyuan.demo.oss.service.impl;

import club.banyuan.demo.oss.service.OssFileService;
import club.banyuan.demo.oss.utils.MinioUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MinioOssFileServiceImpTest {

    private final String OBJECT_NAME = "unit_test/unit_test.jpg";

    @Autowired
    private OssFileService ossFileService;

    @Test
    public void uploadTest() throws IOException {
        InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream("http-client/test.jpg");

        String url = ossFileService.save(OBJECT_NAME, inputStream);

        Assert.assertTrue(url.endsWith(OBJECT_NAME));
        Assert.assertTrue(ossFileService.isExist(OBJECT_NAME));
    }
    @Test
    public void deleteTest() throws IOException {
        ossFileService.delete("20200414/test.jpg");
        Assert.assertFalse(ossFileService.isExist(OBJECT_NAME));
    }

    @Test
    public void downloadTest() throws IOException {
        Files.copy(ossFileService.download("20200414/test.jpg"), Paths.get(MinioUtil.SAVE_IMG_TO_LOACL, "down"));

//        Assert.assertFalse(ossFileService.isExist(OBJECT_NAME));
    }

}
