package cn.abtion.taskgo.mvp.view.task.activity.release;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseToolBarPresenterActivity;
import cn.abtion.taskgo.mvp.contract.task.ReleaseTaskContract;
import cn.abtion.taskgo.mvp.model.task.model.ChooseCardModel;
import cn.abtion.taskgo.mvp.model.task.model.LostFoundTaskInfoModel;
import cn.abtion.taskgo.mvp.model.task.model.WaterTaskInfoModel;
import cn.abtion.taskgo.mvp.presenter.task.ReleaseTaskPresenter;
import cn.abtion.taskgo.utils.DialogUtil;
import cn.abtion.taskgo.utils.ToastUtil;
import cn.abtion.taskgo.widget.cardpager.AlphaTransformer;

/**
 * @author FanHongyu.
 * @since 18/4/19 20:05.
 * email fanhongyu@hrsoft.net.
 */

public class ChooseCardActivity extends BaseToolBarPresenterActivity<ReleaseTaskContract
        .Presenter> implements ReleaseTaskContract.View {


    public static final String BUNDLE_KEY_TASK_TYPE = "taskType";
    public static final String BUNDLE_KEY_WATER_TASK_INFO = "waterTaskInfo";
    public static final String BUNDLE_KEY_LOST_FOUND_TASK_INFO = "waterLostFoundInfo";
    public static final int TASK_WATER = 0;
    public static final int TASK_LOST_FOUND = 1;


    private static Bundle sBundle;
    @BindView(R.id.txt_task_money)
    TextView txtTaskMoney;
    @BindView(R.id.txt_release_task)
    TextView txtReleaseTask;
    @BindView(R.id.vp_card_pager)
    ViewPager vpCardPager;
    @BindView(R.id.txt_set_task_money)
    TextView txtSetTaskMoney;
    private int sTaskType;
    private List<ChooseCardModel> mCardModelList;
    private WaterTaskInfoModel mWaterTaskInfoModel;
    private LostFoundTaskInfoModel mLostFoundTaskInfoModel;


    @Override
    public ReleaseTaskContract.Presenter initPresenter() {
        return new ReleaseTaskPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_card;
    }

    @Override
    protected void initVariable() {
        initModelInfo();
    }

    @Override
    protected void initView() {
        setActivityTitle("卡片选择");
    }

    @Override
    protected void loadData() {
        mPresenter.loadCardInformation();
    }


    @Override
    public void loadCardInfoSuccess(List<ChooseCardModel> models) {
        mCardModelList = models;


        vpCardPager.setOffscreenPageLimit(3);
        vpCardPager.setPageTransformer(false, new AlphaTransformer());
        CardPagerAdapter adapter = new CardPagerAdapter(getSupportFragmentManager(), models);
        vpCardPager.setAdapter(adapter);
    }


    private void initModelInfo() {

        sTaskType = sBundle.getInt(BUNDLE_KEY_TASK_TYPE);
        txtTaskMoney.setText(String.valueOf(0));
        switch (sTaskType) {
            case TASK_WATER:
                mWaterTaskInfoModel = (WaterTaskInfoModel) sBundle.getSerializable(BUNDLE_KEY_WATER_TASK_INFO);
                txtTaskMoney.setText(String.valueOf(mWaterTaskInfoModel.getMoney()));
                break;
            case TASK_LOST_FOUND:
                mLostFoundTaskInfoModel = (LostFoundTaskInfoModel) sBundle.getSerializable
                        (BUNDLE_KEY_LOST_FOUND_TASK_INFO);
                break;
            default:
                break;
        }
    }


    /**
     * 发布成功回调
     */
    @Override
    public void onReleaseSuccess() {
        ToastUtil.showToast("发布成功");
        finish();
    }

    /**
     * 发布失败打印错误信息
     *
     * @param errorMessage
     */
    @Override
    public void onReleaseFailed(String errorMessage) {
        ToastUtil.showToast(errorMessage);
    }


    public static void startActivity(Context context, Bundle bundle) {
        context.startActivity(new Intent(context, ChooseCardActivity.class));
        sBundle = bundle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.txt_release_task)
    public void onTxtReleaseTaskClicked() {

        Log.i("card", "onViewClicked: 卡片1 选择 :" + mCardModelList.get(0).getChooseNumber() + ",剩余 :" + mCardModelList.get
                (0).getHaveNumber());
        Log.i("card", "onViewClicked: 卡片2 选择 :" + mCardModelList.get(1).getChooseNumber() + ",剩余 :" +
                mCardModelList.get(1).getHaveNumber());
        Log.i("card", "onViewClicked: 卡片3 选择 :" + mCardModelList.get(2).getChooseNumber() + ",剩余 :" +
                mCardModelList.get(2).getHaveNumber());
    }

    @OnClick(R.id.txt_set_task_money)
    public void onTxtSetMoneyClicked() {

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        final EditText editText = new EditText(this);
        LinearLayout.LayoutParams editTextViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        editText.setGravity(Gravity.CENTER);
        editTextViewParams.setMargins(50, 0, 50, 0);
        editText.setLayoutParams(editTextViewParams);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        AlertDialog dialog = builder.setTitle("设置金额")
                .setView(editText)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        txtTaskMoney.setText(editText.getText().toString().trim());
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create();

        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams
                .FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        dialog.show();
    }
}