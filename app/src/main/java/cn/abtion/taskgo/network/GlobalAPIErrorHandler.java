package cn.abtion.taskgo.network;

import cn.abtion.taskgo.network.response.ApiException;
import cn.abtion.taskgo.utils.ToastUtil;

/**
 * @author FanHongyu.
 * @since 18/1/18 19:44.
 * email fanhongyu@hrsoft.net.
 */

public class GlobalAPIErrorHandler {

    public static void handler(int code){
        switch (code){


            default:
                ToastUtil.showToast("请求不被允许，请确定是否有权进行该操作");
                break;
        }
    }

    public static void handler(ApiException e){
        switch (e.getCode()){

            default:
                ToastUtil.showToast(e.getMsg());
                break;
        }
    }
}
