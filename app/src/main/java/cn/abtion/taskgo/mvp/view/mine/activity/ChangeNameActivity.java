package cn.abtion.taskgo.mvp.view.mine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseToolBarActivity;

/**
 * @author：lszr on 2018/1/28 22:16
 * @email：1085963811@qq.com
 */
public class ChangeNameActivity extends BaseToolBarActivity {
    @BindView(R.id.edit_change_name)
    EditText editChangeName;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_name;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

        setActivityTitle(getString(R.string.txt_mine_edit_data_name));
    }

    @Override
    protected void loadData() {

    }

    public static void startChangeNameActivity(Activity activity, Context context) {
        Intent intent = new Intent(context, ChangeNameActivity.class);
        activity.startActivityForResult(intent, 10);

    }





    @OnClick(R.id.btn_submmit_new_name)
    public void onViewClicked() {


        Intent intent = new Intent();
        intent.putExtra("newName", editChangeName.getText().toString().trim());
        setResult(RESULT_OK, intent);
        finish();
    }
}
