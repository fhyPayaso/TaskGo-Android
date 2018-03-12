package cn.abtion.taskgo.mvp.view.mine.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseToolBarPresenterActivity;
import cn.abtion.taskgo.base.data.DataCallBack;
import cn.abtion.taskgo.data.UpLoadHelper;
import cn.abtion.taskgo.mvp.contract.mine.UpdateInformationContract;
import cn.abtion.taskgo.mvp.model.mine.MineInformationModel;
import cn.abtion.taskgo.mvp.presenter.mine.UpdateInformationPresenter;
import cn.abtion.taskgo.utils.DialogUtil;
import cn.abtion.taskgo.utils.FileUtil;
import cn.abtion.taskgo.utils.ToastUtil;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author：lszr on 2018/1/31 21:54
 * @email：1085963811@qq.com
 */
public class EditDataActivity extends BaseToolBarPresenterActivity<UpdateInformationContract.Presenter> implements UpdateInformationContract.View, DataCallBack.Callback<String> {
    public int mGender = -1;
    @BindView(R.id.txt_mine_status)
    TextView mTxtMineStatus;
    @BindView(R.id.txt_we_chat_number)
    TextView mTxtWeChatNumber;
    @BindView(R.id.txt_qq_number)
    TextView mTxtQqNumber;


    private DatePickerDialog.OnDateSetListener mDateSetListener;
    public MineInformationModel mineInformationModel = null;
    /**
     * 0代表不需要上传图片，1代表需要上传图片
     */
    private Boolean hasImg = false;
    private AlertDialog bottomDialog;
    private DialogUtil.NativeProgressDialog mProgressDialog;
    private TextView btnTakePhoto;
    private TextView btnFromAlbum;
    private TextView btnCancel;
    private String photoPath;

    @BindView(R.id.txt_mine_phone_number)
    TextView mTxtMinePhoneNumber;
    @BindView(R.id.img_portrait)
    CircleImageView imgPortrait;
    @BindView(R.id.txt_mine_name)
    TextView mTxtMineName;
    @BindView(R.id.txt_mine_edit_birth)
    TextView mTxtMineEditBirth;
    @BindView(R.id.txt_mine_edit_data_gender)
    TextView mTxtMineEditDataGender;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_data;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        initInformation();
    }

    @Override
    protected void loadData() {

    }

    public static void startEditDataActivity(Context context, MineInformationModel mineInformationModel) {
        Intent intent = new Intent(context, EditDataActivity.class);
        String jsonData = new Gson().toJson(mineInformationModel);
        intent.putExtra("MineInformationModel", jsonData);
        context.startActivity(intent);
    }

    public static void startEditDataActivity(Context context) {
        Intent intent = new Intent(context, EditDataActivity.class);
        context.startActivity(intent);
    }

    @OnClick({R.id.rlayout_mine_edit_photo, R.id.rlayout_mine_edit_name, R.id.rlayout_mine_edit_gender, R.id.rlayout_mine_edit_brith})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rlayout_mine_edit_photo:
                updatePhoto();
                break;
            case R.id.rlayout_mine_edit_name:
                ChangeNameActivity.startChangeNameActivity(EditDataActivity.this, EditDataActivity.this);
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

    private void updatePhoto() {
        bottomDialog = new AlertDialog.Builder(this, R.style.dialog_bottom).create();
        bottomDialog.show();
        Window window = bottomDialog.getWindow();
        window.setContentView(R.layout.dialog_upload_image);
        window.setGravity(Gravity.BOTTOM);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);


        btnTakePhoto = bottomDialog.findViewById(R.id.btn_take_photo);
        btnFromAlbum = bottomDialog.findViewById(R.id.btn_from_album);
        btnCancel = bottomDialog.findViewById(R.id.btn_cancel);
        setDialogOnClick();

    }

    public void selectBirthDate() {
        TimePickerView timePickerView = new TimePickerView.Builder(EditDataActivity.this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String format = simpleDateFormat.format(date);
                mTxtMineEditBirth.setText(format);
                mPresenter.requestUpdateInformation(mineInformationModel.getName()
                        ,mineInformationModel.getAvatar(),mineInformationModel.getSex(),format);
                mineInformationModel.setBirth(format);
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})
                .build();
        timePickerView.show();


    }

    public void setGender() {
        DialogUtil.NativeDialog dialog = new DialogUtil().new NativeDialog().singleInit(EditDataActivity.this);
        dialog.setSingleChoice(new String[]{"男", "女", "未知"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        mGender = 0;
                        break;
                    case 1:
                        mGender = 1;
                        break;
                    case 2:
                        mGender = 2;
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
                                mPresenter.requestUpdateInformation(mineInformationModel.getName()
                                        ,mineInformationModel.getAvatar(),"男",mineInformationModel.getBirth());
                                mineInformationModel.setSex("男");
                                break;
                            case 1:
                                mTxtMineEditDataGender.setText(getString(R.string.txt_gender_woman));
                                mPresenter.requestUpdateInformation(mineInformationModel.getName(),mineInformationModel.getAvatar(),"女",mineInformationModel.getBirth());
                                mineInformationModel.setSex("女");
                                break;
                            case 2:
                                mTxtMineEditDataGender.setText(getString(R.string.txt_gender_unknown));
                                mPresenter.requestUpdateInformation(mineInformationModel.getName(),mineInformationModel.getAvatar(),"未知",mineInformationModel.getBirth());
                                mineInformationModel.setSex("未知");
                                break;
                            default:
                                break;
                        }
                    }
                })
                .showNativeDialog();
    }

    /**
     * 初始化个人页面信息
     */
    private void initInformation() {
        Intent intent = getIntent();
        mineInformationModel = new Gson().fromJson(intent.getStringExtra("MineInformationModel"), MineInformationModel.class);

        Glide.with(EditDataActivity.this).load(mineInformationModel.getAvatar()).into(imgPortrait);
        mTxtMineName.setText(mineInformationModel.getName());

        mTxtMineEditBirth.setText(mineInformationModel.getBirth());

        if (mineInformationModel.getSex().equals("男")) {
            mTxtMineEditDataGender.setText("男");
        }  else if(mineInformationModel.getSex().equals("女")){
            mTxtMineEditDataGender.setText("女");
        }else {
            mTxtMineEditDataGender.setText("未知");
        }
        mTxtMinePhoneNumber.setText(mineInformationModel.getMobile());
        mTxtMineEditBirth.setText(mineInformationModel.getBirth());
        if (mineInformationModel.getStatus() == 0) {
            mTxtMineStatus.setText("未认证");
        } else {
            mTxtMineStatus.setText("已认证");
        }
        mTxtWeChatNumber.setText("无");
        mTxtQqNumber.setText("无");

        setActivityTitle(getString(R.string.txt_mine_edit_data));
    }


    @Override
    public UpdateInformationContract.Presenter initPresenter() {
        return new UpdateInformationPresenter(this);
    }


    private void updateNew(){
        mTxtMineName.setText(mineInformationModel.getName());

        mTxtMineEditBirth.setText(mineInformationModel.getBirth());

        if (mineInformationModel.getSex().equals("男")) {
            mTxtMineEditDataGender.setText("男");
        }  else if(mineInformationModel.getSex().equals("女")){
            mTxtMineEditDataGender.setText("女");
        }else {
            mTxtMineEditDataGender.setText("未知");
        }
        mTxtMinePhoneNumber.setText(mineInformationModel.getMobile());
        mTxtMineEditBirth.setText(mineInformationModel.getBirth());
        if (mineInformationModel.getStatus() == 0) {
            mTxtMineStatus.setText("未认证");
        } else {
            mTxtMineStatus.setText("已认证");
        }
        mTxtWeChatNumber.setText("无");
        mTxtQqNumber.setText("无");
    }
    @Override
    public void updateInformationSuccess() {
        ToastUtil.showToast(mineInformationModel.getName());
        updateNew();


    }








    /**
     * 设置底部dialog点击事件
     */
    private void setDialogOnClick() {

        //拍照选项点击事件
        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String filePath = FileUtil.createNewFile(EditDataActivity.this, "TaskGo");
                photoPath = FileUtil.openCamera(EditDataActivity.this, filePath);
                bottomDialog.dismiss();
            }
        });

        //相册选项点击事件
        btnFromAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FileUtil.openAlbum(EditDataActivity.this);
                bottomDialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomDialog.dismiss();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            switch (requestCode) {

                case FileUtil.CAMERA_REQUEST:
                    imgPortrait.setImageBitmap(BitmapFactory.decodeFile(photoPath));
                    UpLoadHelper.upLoadImgToQiNiu(photoPath, this);
                    mProgressDialog = new DialogUtil().new NativeProgressDialog();
                    mProgressDialog
                            .initDialog(EditDataActivity.this)
                            .setMessage("请稍后")
                            .showDialog();
                    hasImg = true;
                    break;
                case FileUtil.ALBUM_REQUEST:
                    String picturePath = FileUtil.getSelectedPicturePath(data, EditDataActivity.this);
                    imgPortrait.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                    photoPath = picturePath;
                    UpLoadHelper.upLoadImgToQiNiu(photoPath, this);
                    mProgressDialog = new DialogUtil().new NativeProgressDialog();
                    mProgressDialog
                            .initDialog(EditDataActivity.this)
                            .setMessage("请稍后")
                            .showDialog();
                    hasImg = true;
                    break;
                case 10:
                    if (resultCode == RESULT_OK) {
                        String newName=data.getStringExtra("newName");
                        mTxtMineName.setText(newName);
                        mPresenter.requestUpdateInformation(newName,mineInformationModel.getAvatar(),mineInformationModel.getSex(),mineInformationModel.getBirth());
                        mineInformationModel.setName(newName);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onDataLoaded(String s) {
        mPresenter.requestUpdateInformation(mineInformationModel.getName(),s,mineInformationModel.getSex(),mineInformationModel.getBirth());
        ToastUtil.showToast("更新头像成功！");
        mProgressDialog.hideDialog();
    }

    @Override
    public void onFailedLoaded(int error) {
        ToastUtil.showToast(error);
        mProgressDialog.hideDialog();
    }
}
