package club.banyuan.zgMallMgt.service.imp;

import club.banyuan.zgMallMgt.dto.OssFileResp;
import club.banyuan.zgMallMgt.service.OssFileService;
import io.minio.ErrorCode;
import io.minio.MinioClient;
import io.minio.errors.ErrorResponseException;
import io.minio.policy.PolicyType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;


@Service
public class OssFileServiceImp implements OssFileService {

    @Value("${minio.endpoint}")
    private String ENDPOINT;

    @Value("${minio.bucketName}")
    private String BUCKET_NAME;

    @Value("${minio.accessKey}")
    private String ACCESS_KEY;

    @Value("${minio.secretKey}")
    private String SECRET_KEY;



    @Override
    public OssFileResp save(String objectName, InputStream stream) throws IOException {
        try {
            MinioClient minioClient = new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
            if (!minioClient.bucketExists(BUCKET_NAME)) {
                minioClient.makeBucket(BUCKET_NAME);
                minioClient.setBucketPolicy(BUCKET_NAME, "*.*", PolicyType.READ_WRITE);
            }

            minioClient.putObject(BUCKET_NAME, objectName, stream, null);
            String objectUrl = minioClient.getObjectUrl(BUCKET_NAME, objectName);
            OssFileResp ossFileResp = new OssFileResp();
            String[] split = objectName.split("/");
            ossFileResp.setName(objectName.substring(split[0].length()+1));
            ossFileResp.setUrl(objectUrl);
            return ossFileResp;

        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException();
        }
    }

    @Override
    public InputStream download(String objectName) throws IOException {
        try {
            MinioClient minioClient = new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
            return minioClient.getObject(BUCKET_NAME, objectName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException();
        }
    }

    @Override
    public boolean isExist(String objectName) throws IOException {
        try {
            MinioClient minioClient = new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
            minioClient.statObject(BUCKET_NAME, objectName);
            return true;
        } catch (ErrorResponseException e) {
            if (ErrorCode.NO_SUCH_KEY == e.errorResponse().errorCode()){
                return false;
            }else {
                throw new IOException();
            }
        } catch (Exception e) {
        e.printStackTrace();
        throw new IOException();
    }
    }

    @Override
    public void delete(String url) throws IOException {
        try {
            //http://127.0.0.1:9000/dev/20200429/haiyang1.jpg
            int length=ENDPOINT.length()+1+BUCKET_NAME.length()+1;
            String objectName = url.substring(length);
            MinioClient minioClient = new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
            if (isExist(objectName)) {
                minioClient.removeObject(BUCKET_NAME, objectName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException();
        }
    }
}
