package club.banyuan.zgMallMgt.service;

import club.banyuan.zgMallMgt.dto.OssFileResp;

import java.io.IOException;
import java.io.InputStream;

public interface OssFileService {
    OssFileResp save(String objectName, InputStream stream) throws IOException;

    InputStream download(String objectName) throws IOException;

    boolean isExist(String objectName) throws IOException;

    void delete(String url) throws IOException;
}
