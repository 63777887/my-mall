package club.banyuan.demo.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

public interface OssFileService {
    String save(String objectName, InputStream stream, String contentType) throws IOException;

    InputStream download(String objectName) throws IOException;
}
