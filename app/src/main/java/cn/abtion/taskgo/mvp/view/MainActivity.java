package cn.abtion.taskgo.mvp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseNoBarActivity;
import cn.abtion.taskgo.mvp.view.task.fragment.HomeFragment;
import cn.abtion.taskgo.mvp.view.message.fragment.MessageFragment;
import cn.abtion.taskgo.mvp.view.mine.fragment.MineFragment;
import cn.abtion.taskgo.utils.FragmentUtil;
import cn.abtion.taskgo.utils.ToastUtil;


public class MainActivity extends BaseNoBarActivity {


    @BindView(R.id.txt_tab_home)
    TextView mTxtTabHome;
    @BindView(R.id.txt_tab_message)
    TextView mTxtTabMessage;
    @BindView(R.id.txt_tab_mine)
    TextView mTxtTabMine;
    @BindView(R.id.img_tab_home)
    ImageView mImgTabHome;
    @BindView(R.id.img_tab_message)
    ImageView mImgTabMessage;
    @BindView(R.id.img_tab_mine)
    ImageView mImgTabMine;


    private HomeFragment mHomeFragment;
    private MessageFragment mMessageFragment;
    private MineFragment mMineFragment;
    private long startTime=0;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

        onLyMenuHomeClicked();
    }

    @Override
    protected void loadData() {

    }


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }


    @OnClick(R.id.ly_menu_home)
    public void onLyMenuHomeClicked() {

        changeHomeMenuState();
        hideAllFragment();
        if (mHomeFragment == null) {
            mHomeFragment = new HomeFragment();
            FragmentUtil.addFragment(this, R.id.frame_content, mHomeFragment, null);
        } else {
            FragmentUtil.showFragment(this, mHomeFragment);
        }
    }

    @OnClick(R.id.ly_menu_message)
    public void onLyMenuMessageClicked() {

        changeMessageMenuState();
        hideAllFragment();
        if (mMessageFragment == null) {
            mMessageFragment = new MessageFragment();
            FragmentUtil.addFragment(this, R.id.frame_content, mMessageFragment, null);
        } else {
            FragmentUtil.showFragment(this, mMessageFragment);
        }
    }

    @OnClick(R.id.ly_menu_mine)
    public void onLyMenuMineClicked() {

        changeMineMenuState();
        hideAllFragment();
        if (mMineFragment == null) {
            mMineFragment = new MineFragment();
            FragmentUtil.addFragment(this, R.id.frame_content, mMineFragment, null);
        } else {
            FragmentUtil.showFragment(this, mMineFragment);
        }

    }


    /**
     * 隐藏所有fragment
     */
    private void hideAllFragment() {

        if (mHomeFragment != null) {
            FragmentUtil.hideFragment(this, mHomeFragment);
        }
        if (mMessageFragment != null) {
            FragmentUtil.hideFragment(this, mMessageFragment);
        }
        if (mMineFragment != null) {
            FragmentUtil.hideFragment(this, mMineFragment);
        }
    }


    /**
     * 更新home标签状态
     */
    private void changeHomeMenuState() {
        clearChoiceStatus();
        mTxtTabHome.setSelected(true);
        mImgTabHome.setSelected(true);
    }

    /**
     * 更新message标签状态
     */
    private void changeMessageMenuState() {
        clearChoiceStatus();
        mTxtTabMessage.setSelected(true);
        mImgTabMessage.setSelected(true);
    }

    /**
     * 更新mine标签状态
     */
    private void changeMineMenuState() {
        clearChoiceStatus();
        mTxtTabMine.setSelected(true);
        mImgTabMine.setSelected(true);
    }


    /**
     * 清除所有标签状态
     */
    private void clearChoiceStatus() {

        mTxtTabHome.setSelected(false);
        mTxtTabMessage.setSelected(false);
        mTxtTabMine.setSelected(false);

        mImgTabHome.setSelected(false);
        mImgTabMessage.setSelected(false);
        mImgTabMine.setSelected(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    public void onBackPressed() {

        long currentTime=System.currentTimeMillis();
        if((currentTime-startTime)>2000) {
            ToastUtil.showToast("再按一次退出TaskGo");
            startTime=currentTime;
        } else {
            finish();
        }
    }
}
