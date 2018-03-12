package cn.abtion.taskgo.mvp.view.mine.activity;

import android.content.Context;
import android.content.Intent;

import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseToolBarPresenterActivity;
import cn.abtion.taskgo.mvp.contract.mine.ChangePhotoContract;
import cn.abtion.taskgo.mvp.presenter.mine.ChangePhotoPresenter;

/**
 * @author：lszr on 2018/3/12 21:24
 * @email：1085963811@qq.com
 */
class ChangePhotoActivity extends BaseToolBarPresenterActivity<ChangePhotoContract.Presenter>implements ChangePhotoContract.View{



    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_photo;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

        Intent intent=getIntent();


    }

    @Override
    protected void loadData() {

    }

    @Override
    public ChangePhotoContract.Presenter initPresenter() {
        return new ChangePhotoPresenter(this);
    }

    public static void startChangePhotoActivity(Context context,String avatar) {
        Intent intent=new Intent(context,ChangeNameActivity.class);
        intent.putExtra("photo",avatar);
        context.startActivity(intent);

    }
}
