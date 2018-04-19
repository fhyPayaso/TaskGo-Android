package cn.abtion.taskgo.mvp.view.task.activity.release;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseToolBarPresenterActivity;
import cn.abtion.taskgo.mvp.contract.task.ReleaseTaskContract;
import cn.abtion.taskgo.mvp.model.task.model.LostFoundTaskInfoModel;
import cn.abtion.taskgo.mvp.model.task.model.WaterTaskInfoModel;
import cn.abtion.taskgo.mvp.presenter.task.ReleaseTaskPresenter;
import cn.abtion.taskgo.utils.ToastUtil;

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
    private int sTaskType;
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
    public void onViewClicked() {
        ToastUtil.showToast("点击了发布任务");
    }
}
