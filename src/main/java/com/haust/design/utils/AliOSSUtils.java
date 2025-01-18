package com.haust.design.utils;

import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import java.io.InputStream;
import java.util.Objects;

public class AliOSSUtils {

    // Endpoint，其它Region请按实际情况填写。
    private static final String ENDPOINT = "https://oss-cn-beijing.aliyuncs.com";
    // 填写Bucket所在地域。以华东1（杭州）为例，Region填写为cn-hangzhou。
    private static final  String REGION = "cn-beijing";
    //密匙
    private static final String ACCESS_KEY_ID = "LTAI5tNCBA97rRfrrrWBCqUM";
    private static final String ACCESS_KEY_SECRET = "QEsHGwAGaqyT9rvE14UKziRzza7uqX";

    // Bucket名称。
    private static final String BUCKET_NAME = "maimai-project";

    private static OSS ossClient = getOssClient();

    //单例实现ossClient，缩短请求时间，不调用shutdown()，等JVM停止时释放资源
    public static OSS getOssClient() {
        DefaultCredentialProvider defaultCredentialProvider = CredentialsProviderFactory.newDefaultCredentialProvider(ACCESS_KEY_ID, ACCESS_KEY_SECRET);

        // 创建OSSClient实例。
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = OSSClientBuilder.create()
                .endpoint(ENDPOINT)
                .credentialsProvider(defaultCredentialProvider)
                .clientConfiguration(clientBuilderConfiguration)
                .region(REGION)
                .build();
        return ossClient;
    }

    public static String url = "https://"+BUCKET_NAME+"."+ENDPOINT.substring(ENDPOINT.lastIndexOf("/")+1)+"/";

    /**
     * 上传文件到阿里云
     * @param objectName 上传后的文件名
     * @param inputStream 文件输入流
     * @return 文件的访问地址
     */
    public static String uploadFile(String objectName,InputStream inputStream){

        try {
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, objectName, inputStream);
            // 创建PutObject请求。
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            url += objectName;
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                //ossClient.shutdown();
            }
        }
        return Objects.equals(url, "https://" + BUCKET_NAME + "." + ENDPOINT.substring(ENDPOINT.lastIndexOf("/") + 1) + "/") ? "" : url;
    }


    /**
     *
     * @param objectName 要删除的文件名
     */
    public static void deleteFile(String objectName){

        try {
            // 删除文件或目录。如果要删除目录，目录必须为空。
            ossClient.deleteObject(BUCKET_NAME, objectName);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                //ossClient.shutdown();
            }
        }
    }
}
