package club.banyuan.demo.oss.service;

import java.io.IOException;
import java.io.InputStream;

public interface OssFileService {
    String save(String objectName, InputStream stream) throws IOException;

    InputStream download(String objectName) throws IOException;

    boolean isExist(String objectName) throws IOException;

    void delete(String objectName) throws IOException;
}
