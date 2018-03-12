package cn.abtion.taskgo.mvp.view.mine.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.OnClick;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseNoBarPresenterActivity;
import cn.abtion.taskgo.mvp.contract.mine.PersonalPageContract;
import cn.abtion.taskgo.mvp.model.mine.PersonalPageModel;
import cn.abtion.taskgo.mvp.presenter.mine.PersonalPagePresenter;
import cn.abtion.taskgo.utils.DialogUtil;
import cn.abtion.taskgo.utils.ToastUtil;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author：lszr on 2018/3/11 20:37
 * @email：1085963811@qq.com
 */
public class PersonalPageActivity extends BaseNoBarPresenterActivity<PersonalPageContract.Presenter> implements PersonalPageContract.View {


    @BindView(R.id.img_portrait)
    CircleImageView imgPortrait;
    @BindView(R.id.txt_mine_follower)
    TextView txtMineFollower;
    @BindView(R.id.txt_mine_fans)
    TextView txtMineFans;
    @BindView(R.id.txt_personal_phone)
    TextView txtPersonalPhone;
    @BindView(R.id.txt_personal_sex)
    TextView txtPersonalSex;
    @BindView(R.id.txt_personal_name)
    TextView txtPersonalName;
    @BindView(R.id.btn_chat)
    Button btnChat;
    @BindView(R.id.btn_concentrate)
    Button btnConcentrate;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_page;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        mPresenter.requestPersonalInformation(intent.getStringExtra("userId"));

    }

    @Override
    protected void loadData() {

    }

    public static void startPersonalPageActivity(Context context, String userId) {
        Intent intent = new Intent(context, PersonalPageActivity.class);
        intent.putExtra("userId", userId);
        context.startActivity(intent);
    }

    @Override
    protected PersonalPageContract.Presenter initPresenter() {
        return new PersonalPagePresenter(this);
    }

    @Override
    public void getPersonalInformationsuccess(PersonalPageModel personalPageModel) {
        Glide.with(PersonalPageActivity.this).load(personalPageModel.getAvatar()).into(imgPortrait);
        txtMineFans.setText(String.valueOf(personalPageModel.getFollowings_count()));
        txtMineFollower.setText(String.valueOf(personalPageModel.getFollowers_count()));
        if (personalPageModel.getSex() == null) {
            txtPersonalSex.setText("未知");
        } else if (personalPageModel.getSex().equals("男")) {
            txtPersonalSex.setText("男");

        } else if (personalPageModel.getSex().equals("女")) {
            txtPersonalSex.setText("女");

        } else {
            txtPersonalSex.setText("未知");
        }
        txtPersonalName.setText(personalPageModel.getName());
        txtPersonalPhone.setText(personalPageModel.getMobile());
        if (personalPageModel.isIs_following()){
            btnConcentrate.setText("已关注");
        } else {
            btnConcentrate.setText("关注");
        }
    }

    @Override
    public void followSBSuccess() {
        btnConcentrate.setText("已关注");
    }

    @Override
    public void cancelFollowSuccess() {
        btnConcentrate.setText("关注");

    }

    @OnClick({R.id.btn_chat, R.id.btn_concentrate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_chat:
                ToastUtil.showToast("功能暂未开放，敬请期待");
                break;
            case R.id.btn_concentrate:
                if(btnConcentrate.getText().equals("已关注")){
                    DialogUtil.NativeDialog nativeDialog=new DialogUtil().new NativeDialog().singleInit(PersonalPageActivity.this);
                    nativeDialog
                            .setMessage("确定取消关注？")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = getIntent();
                                    mPresenter.requestCancelFollow(intent.getStringExtra("userId"));
                                }
                            })
                            .setNegativeButton("取消")
                            .showNativeDialog();
                }
                else{
                    Intent intent = getIntent();
                    mPresenter.requestFollowSB(intent.getStringExtra("userId"));
                }

                break;
            default:
                break;
        }
    }


}
