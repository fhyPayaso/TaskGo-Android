package cn.abtion.taskgo.mvp.view.task.activity.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseToolBarActivity;
import cn.abtion.taskgo.mvp.model.task.response.LostFoundTaskResponse;
import cn.abtion.taskgo.mvp.view.task.activity.SearchTaskActivity;
import cn.abtion.taskgo.mvp.view.task.activity.release.ReleaseLostFoundTaskActivity;
import cn.abtion.taskgo.mvp.view.task.adapter.pager.LostFoundPagerAdapter;
import cn.abtion.taskgo.utils.DialogUtil;

import static cn.abtion.taskgo.utils.Utility.runOnUiThread;

/**
 * @author fhyPayaso
 * @since 2018/1/26 on 下午11:30
 * fhyPayaso@qq.com
 */
public class LostAndFoundTaskActivity extends BaseToolBarActivity {


    @BindView(R.id.tl_lost_and_found)
    TabLayout mTabLayout;
    @BindView(R.id.vp_lost_and_found)
    ViewPager mViewPager;
    @BindView(R.id.btn_release_task)
    FloatingActionButton btnReleaseTask;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lost_and_find;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        initToolBar();
        initViewPager();
    }

    @Override
    protected void loadData() {

    }


    private void initToolBar() {
        setActivityTitle(getString(R.string.title_lost_and_found_task));
        setToolBarMenu(R.drawable.ic_search);
        getToolBar().findViewById(R.id.img_toolbar_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchTaskActivity.startActivity(LostAndFoundTaskActivity.this);
            }
        });
    }

    private void initViewPager() {
        LostFoundPagerAdapter mPagerAdapter = new LostFoundPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(2);
        mTabLayout.setupWithViewPager(mViewPager);
    }


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, LostAndFoundTaskActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_release_task)
    public void onViewClicked() {
        ReleaseLostFoundTaskActivity.startActivity(this);
    }


    public FloatingActionButton getFloatingButton() {
        return this.btnReleaseTask;
    }


    /**
     * 展示物品任务详细信息
     * @param context
     * @param dialog
     * @param response
     */
    public static void showLostFoundTaskInfo(Context context,final DialogUtil.CustomAlertDialog dialog, LostFoundTaskResponse response) {

        dialog.initDialog(context, R.layout.dialog_lost_found_information);
        dialog.setCanceledOntouchOutside(true);
        dialog.showDialog();

        View view = dialog.getView();
        TextView txtItemName = view.findViewById(R.id.txt_item_name);
        TextView txtPlace = view.findViewById(R.id.txt_item_place);
        TextView txtLostFoundType = view.findViewById(R.id.txt_item_task_type);
        TextView txtRmark = view.findViewById(R.id.txt_remark_information);
        TextView btnConfirm = view.findViewById(R.id.btn_information_confirm);
        ImageView imageView = view.findViewById(R.id.img_item_photo);

        txtItemName.setText(response.getName() == null ? "" : response.getName());
        txtLostFoundType.setText(response.getType() == 0 ? "寻物启事" : "失物招领");
        txtPlace.setText(response.getPlace() == null ? "" : response.getPlace());
        txtRmark.setText(response.getRemarks() == null ? "" : response.getRemarks());
        if(response.getPicture_url()!= null && response.getPicture_url().length() >10) {
            Glide.with(context).load(response.getPicture_url()).into(imageView);
        }

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.hideDialog();
            }
        });
    }
}
