package cn.abtion.taskgo.mvp.view.initiate;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseNoBarActivity;
import cn.abtion.taskgo.mvp.view.account.LoginActivity;

/**
 * 启动页
 *
 * @author FanHongyu.
 * @since 18/3/5 15:25.
 * email fanhongyu@hrsoft.net.
 */

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean isFirstOpen = false;

        // TODO: 18/3/5 判断是否第一次登录

        if (isFirstOpen) {
            GuideActivity.startActivity(this);
            finish();
        }

        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LoginActivity.startActivity(SplashActivity.this);
                finish();
            }
        }, 1000);
    }
}
