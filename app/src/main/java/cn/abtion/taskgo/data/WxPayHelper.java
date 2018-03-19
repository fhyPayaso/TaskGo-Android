package cn.abtion.taskgo.data;

import android.content.Context;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * @author FanHongyu.
 * @since 18/3/14 21:19.
 * email fanhongyu@hrsoft.net.
 */

public class WxPayHelper {


    private Context mContext;

    private static final String APP_ID = "11111";

    /**
     * app和微信通信的openapi接口
     */
    private static IWXAPI api;


    /**
     * 要使你的程序启动后微信终端能响应你的程序，必须在代码中向微信终端注册你的id
     * 可以在程序入口Activity的onCreate回调函数处，或其他合适的地方将你的应用id注册到微信
     */
    private void regToWx() {
        api = WXAPIFactory.createWXAPI(mContext, APP_ID, true);
        api.registerApp(APP_ID);
    }

    private static void startWXPay() {

        PayReq req = new PayReq();
        req.appId = APP_ID;

        req.partnerId = "";
        req.prepayId = "";
        req.packageValue = "";
        req.nonceStr = "";
        req.timeStamp = "";
        req.sign = "";
        api.sendReq(req);
        //以上代码就是发起微信支付的方法
    }


}
