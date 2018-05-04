package com.wesharer.qiniu;

import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.qiniu.android.common.FixedZone;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONException;

public class QiniuManagerModule extends ReactContextBaseJavaModule {

    private static final String REACT_CLASS = "QiniuManager";

    private UploadManager uploadManager;

    public QiniuManagerModule(ReactApplicationContext reactContext) {
        super(reactContext);
        Configuration config = new Configuration.Builder()
                .chunkSize(512 * 1024)        // 分片上传时，每片的大小。 默认256K
                .putThreshhold(1024 * 1024)   // 启用分片上传阀值。默认512K
                .connectTimeout(10)           // 链接超时。默认10秒
                .useHttps(true)               // 是否使用https上传域名
                .responseTimeout(60)          // 服务器响应超时。默认60秒
                //.recorder(recorder)           // recorder分片上传时，已上传片记录器。默认null
                //.recorder(recorder, keyGen)   // keyGen 分片上传时，生成标识符，用于片记录器区分是那个文件的上传记录
//                .zone(FixedZone.zone0)        // 设置区域，指定不同区域的上传域名、备用域名、备用IP。
                .build();
        uploadManager = new UploadManager(config);
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @ReactMethod
    public void uploadFile(ReadableMap params, final Promise promise) {
        String filePath = params.getString("fileUrl").replaceFirst("file://", "");
        final String objectKey = params.getString("objectKey");
        final String token = params.getString("token");
        uploadManager.put(filePath, objectKey, token, (key, info, res) -> {
            if (info.isOK()) {
                Log.i("react-native-qiniu", "Upload Success");
                WritableMap result = Arguments.createMap();
                result.putString("objectKey", objectKey);
                promise.resolve(result);
            } else {
                Log.i("react-native-qiniu", "Upload Fail");
                try {
                    promise.reject("-1", res.getString("info"));
                } catch (JSONException e) {
                    promise.reject("-1", "");
                }
            }
            Log.i("react-native-qiniu", key + ",\r\n " + info + ",\r\n " + res);
        }, null);
    }
}