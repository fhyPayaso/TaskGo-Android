package cn.abtion.taskgo.mvp.view.task.activity.release;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseToolBarPresenterActivity;
import cn.abtion.taskgo.mvp.contract.task.ReleaseTaskContract;
import cn.abtion.taskgo.mvp.model.task.request.ReleaseWaterTaskRequest;
import cn.abtion.taskgo.mvp.presenter.task.ReleaseTaskPresenter;

import cn.abtion.taskgo.utils.ToastUtil;

/**
 * @author FanHongyu.
 * @since 18/1/26 17:42.
 * email fanhongyu@hrsoft.net.
 */

public class ReleaseWaterTaskActivity extends BaseToolBarPresenterActivity<ReleaseTaskContract.Presenter>
        implements ReleaseTaskContract.View {


    @BindView(R.id.txt_type_send)
    TextView txtTypeSend;
    @BindView(R.id.txt_type_self)
    TextView txtTypeSelf;
    @BindView(R.id.txt_total_money)
    TextView txtTotalMoney;
    @BindView(R.id.edit_address_number)
    EditText editAddressNumber;
    @BindView(R.id.btn_release_task)
    TextView btnReleaseTask;


    /**
     * 默认为0代表送水上门，1代表自取
     */
    private String waterTaskType = "0";

    @Override
    public ReleaseTaskContract.Presenter initPresenter() {
        return new ReleaseTaskPresenter(this);
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

    /**
     * 送水上门类型点击事件
     */
    @OnClick(R.id.txt_type_send)
    public void onTxtTypeSendClicked() {

        txtTypeSend.setSelected(true);
        txtTypeSelf.setSelected(false);
        txtTotalMoney.setText(R.string.txt_nine_rmb);
        waterTaskType = "0";
    }

    /**
     * 自取类型点击事件
     */
    @OnClick(R.id.txt_type_self)
    public void onTxtTypeSelfClicked() {

        txtTypeSelf.setSelected(true);
        txtTypeSend.setSelected(false);
        txtTotalMoney.setText(R.string.txt_eight_rmb);
        waterTaskType = "1";
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


    @OnClick(R.id.btn_release_task)
    public void onViewClicked() {
        mPresenter.releaseWaterTask(new ReleaseWaterTaskRequest(editAddressNumber.getText().toString().trim(),
                waterTaskType));
    }
}
