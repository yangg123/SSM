package com.render.kit;

import java.io.File;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

/**
 * Created by yg on 17/2/17.
 */
public class QiniuKit {

    private static String qiniuAccessKey = "MQMeKe9ZdkFydG5ASbifI8yPtddWUsYCNB5qKdtP";
    private static String qiniuSecretKey = "CL438rMkBJCWyKv3Qv3qs6CP41tgyILu4kz9hte_";
    private static String bucket = "yanggao";

    private static Auth auth = Auth.create(qiniuAccessKey, qiniuSecretKey);
    private static BucketManager bucketManager = new BucketManager(auth);

    private Zone z = Zone.zone1();
    private static UploadManager uploadManager = new UploadManager();

    private static String FilePath = "/Users/yg/Desktop/activityDetailRine.png";  //本地要上传文件路径
    private static String key = "daimo6.png";           //上传到七牛后保存的文件名

    public static String getToken() {
        return auth.uploadToken(bucket);
    }

    public static String getToken(String key) {
        return auth.uploadToken(bucket, key);
    }

    public static void move(String strFromKey, String strToKey) throws QiniuException {
        bucketManager.move(bucket, strFromKey, bucket, strToKey);
    }

    public static void copy(String strFromKey, String strToKey) throws QiniuException {
        bucketManager.copy(bucket, strFromKey, bucket, strToKey);
    }

    public static boolean uploadFile(File file, String key) throws QiniuException {
        Response res = uploadManager.put(file, key, getToken(key));
        return res.isOK();
    }

    public static void uploadFilea(String filePath, String key) throws QiniuException {

        try {
            //调用put方法上传
            Response res = uploadManager.put(FilePath, key, getToken(key));
            //打印返回的信息
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
    }
}
