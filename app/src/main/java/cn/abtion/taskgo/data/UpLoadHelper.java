package cn.abtion.taskgo.data;

import android.util.Log;

import com.qiniu.android.common.FixedZone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.data.DataCallBack;

/**
 * @author FanHongyu.
 * @since 18/3/9 19:23.
 * email fanhongyu@hrsoft.net.
 */

public class UpLoadHelper {

    public static String token ="V4bRlWyjq34V1c2WVvd39rm4WR7qAcaTtQ0Zs7rL:yqnN9yrv6um26wANvEOy-086nEs" +
            "=:eyJzY29wZSI6InBpY3R1cmUiLCJkZWFkbGluZSI6MTUxNzU1NzY1M30=";

    public static void upLoadImgToQiNiu(String imagePath, final DataCallBack.Callback<String> callback) {

        if(imagePath != null) {

            Configuration config = new Configuration.Builder().zone(FixedZone.zone1).build();
            UploadManager uploadManager = new UploadManager(config);
            // 设置图片路径、上传后文件名、token
            uploadManager.put(imagePath, +System.currentTimeMillis()+".png", token, new UpCompletionHandler() {
                @Override
                public void complete(String key, ResponseInfo info, JSONObject res) {
                    // info.error中包含了错误信息，可打印调试
                    // 上传成功后将key值上传到自己的服务器
                    if (info.isOK()) {
                        String imgUrl = "http://p0y1qzu73.bkt.clouddn.com/"+key;

                        callback.onDataLoaded(imgUrl);

                    } else {
                        Log.d("qiniu", "complete: "+info.error);
                        callback.onFailedLoaded(R.string.error_upload_image_filed);
                    }
                }
            }, null);

        } else {
            callback.onFailedLoaded(R.string.error_upload_image_filed);
        }
    }
}
