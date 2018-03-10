package cn.abtion.taskgo.mvp.view.task.activity.my;

import android.content.Context;
import android.content.Intent;

import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseToolBarPresenterActivity;
import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.mvp.model.task.model.BaseTaskModel;
import cn.abtion.taskgo.utils.ToastUtil;

/**
 * @author fhyPayaso
 * @since 2018/3/10 on 下午4:23
 * fhyPayaso@qq.com
 */
public class ChooseFinishUserListActivity extends BaseToolBarPresenterActivity{


    private static BaseTaskModel mLostFoundTaskModel;

    @Override
    public BaseContract.Presenter initPresenter() {
        return new BasePresenter<>(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_finish_user_list;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        ToastUtil.showToast("请选择想要完成该任务的用户");
        initToolBar();
    }

    @Override
    protected void loadData() {

    }


    private void initToolBar() {
        setActivityTitle("用户选择");
    }


    public static void startActivity(Context context, BaseTaskModel taskModel) {
        context.startActivity(new Intent(context,ChooseFinishUserListActivity.class));
        mLostFoundTaskModel = taskModel;
    }
}
