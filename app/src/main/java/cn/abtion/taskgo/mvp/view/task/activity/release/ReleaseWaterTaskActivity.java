package cn.abtion.taskgo.mvp.view.task.activity.release;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseToolBarActivity;
import cn.abtion.taskgo.base.activity.BaseToolBarPresenterActivity;
import cn.abtion.taskgo.mvp.contract.task.FillTaskInfoContract;
import cn.abtion.taskgo.mvp.contract.task.ReleaseTaskContract;
import cn.abtion.taskgo.mvp.model.task.model.WaterTaskInfoModel;
import cn.abtion.taskgo.mvp.model.task.request.ReleaseWaterTaskRequest;
import cn.abtion.taskgo.mvp.presenter.task.FillTaskInfoPresenter;
import cn.abtion.taskgo.mvp.presenter.task.ReleaseTaskPresenter;

import cn.abtion.taskgo.utils.RegexpUtils;
import cn.abtion.taskgo.utils.ToastUtil;

/**
 * @author FanHongyu.
 * @since 18/1/26 17:42.
 * email fanhongyu@hrsoft.net.
 */
public class ReleaseWaterTaskActivity extends BaseToolBarPresenterActivity<FillTaskInfoContract.Presenter> implements FillTaskInfoContract.View {


    @BindView(R.id.txt_type_send)
    TextView txtTypeSend;
    @BindView(R.id.txt_type_self)
    TextView txtTypeSelf;
    @BindView(R.id.edit_address_number)
    EditText editAddressNumber;
    @BindView(R.id.btn_release_task)
    TextView btnReleaseTask;

    /**
     * 送水上门类型
     */
    public static final int WATER_TASK_SEND = 0;

    /**
     * 自取类型
     */
    public static final int WATER_TASK_SELF = 1;

    /**
     * 当前水任务类型
     */
    private int mCurrentType = 0;

    /**
     * 任务信息
     */
    private WaterTaskInfoModel mInfoModel;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_release_water_task;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

        setActivityTitle(getString(R.string.title_release_task));
        onTxtTypeSendClicked();
    }

    @Override
    protected void loadData() {

    }

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, ReleaseWaterTaskActivity.class));
    }

    /**
     * 送水上门类型点击事件
     */
    @OnClick(R.id.txt_type_send)
    public void onTxtTypeSendClicked() {

        txtTypeSend.setSelected(true);
        txtTypeSelf.setSelected(false);
        mCurrentType = WATER_TASK_SEND;
    }

    /**
     * 自取类型点击事件
     */
    @OnClick(R.id.txt_type_self)
    public void onTxtTypeSelfClicked() {

        txtTypeSelf.setSelected(true);
        txtTypeSend.setSelected(false);
        mCurrentType = WATER_TASK_SELF;
    }


    @OnClick(R.id.btn_release_task)
    public void onViewClicked() {
        mInfoModel = new WaterTaskInfoModel();
        mInfoModel.setAddressNumber(editAddressNumber.getText().toString().trim());
        mInfoModel.setWaterType(mCurrentType);
        mInfoModel.setMoney(mCurrentType == WATER_TASK_SEND ? 9 : 8);
        mPresenter.checkWaterTaskInfo(mInfoModel);
    }


    @Override
    public void onDataTrue() {
        Bundle bundle = new Bundle();
        bundle.putInt(ChooseCardActivity.BUNDLE_KEY_TASK_TYPE, ChooseCardActivity.TASK_WATER);
        bundle.putSerializable(ChooseCardActivity.BUNDLE_KEY_WATER_TASK_INFO, mInfoModel);
        ChooseCardActivity.startActivity(this, bundle);
    }

    @Override
    public void onDataFalse(String error) {
        ToastUtil.showToast(error);
    }

    @Override
    public FillTaskInfoContract.Presenter initPresenter() {
        return new FillTaskInfoPresenter(this);
    }
}
