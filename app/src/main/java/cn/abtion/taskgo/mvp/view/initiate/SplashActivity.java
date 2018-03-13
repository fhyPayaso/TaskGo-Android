package cn.abtion.taskgo.mvp.view.initiate;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import cn.abtion.taskgo.R;
import cn.abtion.taskgo.TaskGoApplication;
import cn.abtion.taskgo.base.activity.BaseNoBarActivity;
import cn.abtion.taskgo.base.activity.BaseNoBarPresenterActivity;
import cn.abtion.taskgo.common.constants.CacheKey;
import cn.abtion.taskgo.mvp.contract.account.SplashContract;
import cn.abtion.taskgo.mvp.presenter.account.SplashPresenter;
import cn.abtion.taskgo.mvp.view.MainActivity;
import cn.abtion.taskgo.mvp.view.account.LoginActivity;

/**
 * 启动页
 *
 * @author FanHongyu.
 * @since 18/3/5 15:25.
 * email fanhongyu@hrsoft.net.
 */

public class SplashActivity extends BaseNoBarPresenterActivity<SplashContract.Presenter> implements SplashContract
        .View {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkToken();
            }
        }, 1000);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected SplashContract.Presenter initPresenter() {
        return new SplashPresenter(this);
    }

    /**
     * 检查token
     */
    private void checkToken() {

        //判断是否第一次登录,
        boolean isFirstOpen = true;
        isFirstOpen = TaskGoApplication.getInstance().getCacheUtil().getBoolean(CacheKey.IS_FIRST_OPEN, isFirstOpen);

        if (isFirstOpen) {
            //第一次登录直接进入引导页
            TaskGoApplication.getInstance().getCacheUtil().putBoolean(CacheKey.IS_FIRST_OPEN, false);
            GuideActivity.startActivity(this);
            finish();
        } else {

            String token = TaskGoApplication.getInstance().getCacheUtil().getString(CacheKey.TOKEN);
            if (token == null || token.length()<6) {
                invalidToken();
            } else {
                //不是第一次登录检查token是否过期
                mPresenter.checkToken(token);
            }
        }
    }

    /**
     * token有效直接进入主活动
     */
    @Override
    public void effectiveToken() {
        MainActivity.startActivity(SplashActivity.this);
        finish();
    }

    /**
     * token过期进入登录界面
     */
    @Override
    public void invalidToken() {
        LoginActivity.startActivity(SplashActivity.this);
        finish();
    }
}
