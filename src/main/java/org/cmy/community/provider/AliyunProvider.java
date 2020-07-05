package org.cmy.community.provider;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectResult;
import org.cmy.community.exception.CustomizedErrorCode;
import org.cmy.community.exception.CustomizedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

@Service
public class AliyunProvider {
    @Value("${aliyun.endpoint}")
    private String endpoint;
    @Value("${aliyun.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.bucketName}")
    private String bucketName;

    public String upload(String fileKey, InputStream inputStream){
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String generatedFileName;
        String[] filePaths = fileKey.split("\\.");
        if (filePaths.length>1) {
            generatedFileName = UUID.randomUUID().toString() + "." + filePaths[filePaths.length -1];
        } else {
            throw new CustomizedException(CustomizedErrorCode.FILE_UPLOAD_FAIL);
        }
        try {
            // 文件存储入OSS，Object的名称为fileKey。详细请参看“SDK手册 > Java-SDK > 上传文件”。
            // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/upload_object.html?spm=5176.docoss/user_guide/upload_object
            ossClient.putObject(bucketName, generatedFileName, inputStream);
            Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 1 );//过期时间为一年
            // 生成URL
            URL url = ossClient.generatePresignedUrl(bucketName, generatedFileName, expiration);
            if (url != null) {
                return url.toString();
            }else{
                throw new CustomizedException(CustomizedErrorCode.FILE_UPLOAD_FAIL);
            }
        } catch (OSSException e) {
            e.printStackTrace();
            throw new CustomizedException(CustomizedErrorCode.FILE_UPLOAD_FAIL);
        } catch (ClientException e) {
            e.printStackTrace();
            throw new CustomizedException(CustomizedErrorCode.FILE_UPLOAD_FAIL);
        } finally {
            ossClient.shutdown();
        }
    }
}
