package cn.abtion.taskgo.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.TextView;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import cn.abtion.taskgo.R;
import cn.abtion.taskgo.utils.ToastUtil;

import static android.content.ContentValues.TAG;

/**
 * @author FanHongyu.
 * @since 18/3/15 20:17.
 * email fanhongyu@hrsoft.net.
 */

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {


    private IWXAPI api;
    private static final String APP_ID = "11111";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        api = WXAPIFactory.createWXAPI(this, APP_ID);
        api.handleIntent(getIntent(), this);


    }

    @Override
    public void onReq(BaseReq baseReq) {

    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);
        api.handleIntent(getIntent(), this);
    }

    @Override
    public void onResp(BaseResp baseResp) {

        int respCode = baseResp.errCode;

        Log.i(TAG, "onResp: "+respCode);

        switch (respCode) {

            //支付成功回调
            case 0:
                ToastUtil.showToast("支付成功");
                break;
            //支付失败
            case -1:
                ToastUtil.showToast("支付失败");
                Log.i(TAG, "onResp:  " + baseResp.errStr);
                break;
            //用户取消支付
            case -2:
                finish();
                break;
            default:
                break;
        }

    }
}
