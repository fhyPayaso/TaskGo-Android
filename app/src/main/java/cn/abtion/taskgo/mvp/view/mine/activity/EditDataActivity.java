package cn.abtion.taskgo.mvp.view.mine.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseToolBarPresenterActivity;
import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.utils.DialogUtil;

/**
 * @author：lszr on 2018/1/31 21:54
 * @email：1085963811@qq.com
 */
public class EditDataActivity extends BaseToolBarPresenterActivity {
    public int mGender = -1;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private int mYear;
    private int mMonth;
    private int mDay;






    @BindView(R.id.txt_mine_edit_birth)
    TextView mTxtMineEditBirth;
    @BindView(R.id.txt_mine_edit_data_gender)
    TextView mTxtMineEditDataGender;

    @Override
    public BaseContract.Presenter initPresenter() {
        return new BasePresenter<>(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_data;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        setActivityTitle(getString(R.string.txt_mine_edit_data));

    }

    @Override
    protected void loadData() {

    }

    public static void startEditDataActivity(Context context) {
        Intent intent = new Intent(context, EditDataActivity.class);
        context.startActivity(intent);
    }

    @OnClick({R.id.rlayout_mine_edit_photo, R.id.rlayout_mine_edit_name, R.id.rlayout_mine_edit_gender, R.id.rlayout_mine_edit_brith})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rlayout_mine_edit_photo:
                break;
            case R.id.rlayout_mine_edit_name:
                ChangeNameActivity.startChangeNameActivity(EditDataActivity.this);
                break;
            case R.id.rlayout_mine_edit_gender:
                setGender();
                break;
            case R.id.rlayout_mine_edit_brith:
                selectBirthDate();
                break;
            default:
                break;
        }
    }

    public void selectBirthDate() {
        TimePickerView timePickerView=new TimePickerView.Builder(EditDataActivity.this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                String format = simpleDateFormat.format(date);
                mTxtMineEditBirth.setText(format);
            }
        })
                .setType(new boolean[]{true,true,true,false,false,false})
                .build();
        timePickerView.show();




    }

    public void setGender() {
        DialogUtil.NativeDialog dialog = new DialogUtil().new NativeDialog().singleInit(EditDataActivity.this);
        dialog.setSingleChoice(new String[]{"男", "女"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        mGender = 0;
                        break;
                    case 1:
                        mGender = 1;
                        break;
                    default:
                        break;
                }
            }
        })
                .setPositiveButton("完成", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (mGender) {
                            case 0:
                                mTxtMineEditDataGender.setText(getString(R.string.txt_gender_man));
                                break;
                            case 1:
                                mTxtMineEditDataGender.setText(getString(R.string.txt_gender_woman));
                                break;
                            default:
                                break;
                        }
                    }
                })
                .showNativeDialog();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
