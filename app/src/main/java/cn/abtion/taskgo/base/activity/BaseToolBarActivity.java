package cn.abtion.taskgo.base.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.abtion.taskgo.R;

/**
 * @author FanHongyu.
 * @since 18/1/16 15:32.
 * email fanhongyu@hrsoft.net.
 */

public abstract class BaseToolBarActivity extends BaseActivity {


    private Toolbar mToolBar;
    Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getToolbarView());
        initActivity();
    }




    /**
     * 获取带toolbar的基类页面View
     *
     * @return View
     */
    private View getToolbarView() {
        LayoutInflater inflater = getLayoutInflater();
        RelativeLayout viewRoot = (RelativeLayout) inflater.inflate(R.layout.view_toolbar_base, null);
        FrameLayout viewContainer = viewRoot.findViewById(R.id.view_container);
        viewContainer.addView(inflater.inflate(getLayoutId(), null));
        initToolbar(viewRoot);
        return viewRoot;
    }

    /**
     * 初始化设置toolbar.
     *
     * @param root 页面rootView
     */
    private void initToolbar(View root) {
        mToolBar = root.findViewById(R.id.toolbar);
        mToolBar.setTitle(getString(R.string.empty));
        //在NoActionBar主题下，用ToolBar代替ActionBar
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //设置左上角的图标可以点击
            actionBar.setHomeButtonEnabled(true);
            //显示左上角的返回图标
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    /**
     * 响应菜单项的点击事件
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackBtnOnclick();
                // TODO: 18/1/17  菜单点击事件的注释
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * 获取当前页面toolbar
     *
     * @return toolbar
     */
    protected Toolbar getToolBar() {
        return mToolBar;
    }


    /**
     * 设置activity 页面标题
     *
     * @param charSequence 页面标题
     */
    protected void setActivityTitle(CharSequence charSequence) {
        if (mToolBar != null) {

            TextView view = mToolBar.findViewById(R.id.txt_toolbar_title);
            view.setText(charSequence);

        }
    }


    /**
     * 自定义右上角图标
     * @param resId
     */
    protected void setToolBarMenu(int resId) {
        if(mToolBar != null) {
            ImageView imageView = mToolBar.findViewById(R.id.img_toolbar_menu);
            imageView.setImageResource(resId);
        }
    }

    /**
     * Toolbar返回按钮的监听事件
     */
    protected void onBackBtnOnclick() {
        this.finish();
    }


}
