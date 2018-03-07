package cn.abtion.taskgo.mvp.view.home.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseToolBarPresenterActivity;
import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.mvp.contract.ReleaseWaterTaskContract;
import cn.abtion.taskgo.mvp.model.request.home.ReleaseWaterTaskRequest;
import cn.abtion.taskgo.mvp.presenter.ReleaseWaterTaskPresenter;
import cn.abtion.taskgo.utils.ToastUtil;

/**
 * @author FanHongyu.
 * @since 18/1/26 17:42.
 * email fanhongyu@hrsoft.net.
 */

public class ReleaseWaterTaskActivity extends BaseToolBarPresenterActivity<ReleaseWaterTaskContract.Presenter>
        implements ReleaseWaterTaskContract.View {


    @BindView(R.id.txt_type_send)
    TextView txtTypeSend;
    @BindView(R.id.txt_type_self)
    TextView txtTypeSelf;
    @BindView(R.id.txt_total_money)
    TextView txtTotalMoney;
    @BindView(R.id.edit_address_number)
    EditText editAddressNumber;
    @BindView(R.id.edit_task_information)
    EditText editTaskInformation;


    @Override
    public ReleaseWaterTaskContract.Presenter initPresenter() {
        return new ReleaseWaterTaskPresenter(this);
    }

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    /**
     * 送水上门类型点击事件
     */
    @OnClick(R.id.txt_type_send)
    public void onTxtTypeSendClicked() {

        txtTypeSend.setSelected(true);
        txtTypeSelf.setSelected(false);
        txtTotalMoney.setText(R.string.txt_nine_rmb);
    }

    /**
     * 自取类型点击事件
     */
    @OnClick(R.id.txt_type_self)
    public void onTxtTypeSelfClicked() {

        txtTypeSelf.setSelected(true);
        txtTypeSend.setSelected(false);
        txtTotalMoney.setText(R.string.txt_eight_rmb);
    }

    @OnClick(R.id.btn_release_task)
    public void onViewClicked() {

        String type = "0";

        if (!txtTypeSend.isSelected() && txtTypeSelf.isSelected()) {
            type = "1";
        }
        mPresenter.releaseWaterTask(new ReleaseWaterTaskRequest(editAddressNumber.getText().toString().trim(), type));
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
}
