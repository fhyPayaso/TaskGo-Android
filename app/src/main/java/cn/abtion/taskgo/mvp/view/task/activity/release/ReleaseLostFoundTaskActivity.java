package cn.abtion.taskgo.mvp.view.task.activity.release;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseToolBarPresenterActivity;
import cn.abtion.taskgo.base.data.DataCallBack;
import cn.abtion.taskgo.data.UpLoadHelper;
import cn.abtion.taskgo.mvp.contract.task.FillTaskInfoContract;
import cn.abtion.taskgo.mvp.contract.task.ReleaseTaskContract;
import cn.abtion.taskgo.mvp.model.task.model.ChooseCardModel;
import cn.abtion.taskgo.mvp.model.task.model.LostFoundTaskInfoModel;
import cn.abtion.taskgo.mvp.model.task.request.ReleaseLostFoundTaskRequest;
import cn.abtion.taskgo.mvp.presenter.task.FillTaskInfoPresenter;
import cn.abtion.taskgo.mvp.presenter.task.ReleaseTaskPresenter;
import cn.abtion.taskgo.utils.DialogUtil;
import cn.abtion.taskgo.utils.FileUtil;
import cn.abtion.taskgo.utils.ToastUtil;

/**
 * @author FanHongyu.
 * @since 18/1/30 12:47.
 * email fanhongyu@hrsoft.net.
 */

public class ReleaseLostFoundTaskActivity extends BaseToolBarPresenterActivity<FillTaskInfoContract.Presenter>
        implements FillTaskInfoContract.View, DataCallBack.Callback<String> {

    @BindView(R.id.edit_item_name)
    EditText mEditItemName;
    @BindView(R.id.img_add_photo)
    ImageView mImgAddPhoto;
    @BindView(R.id.edit_task_information)
    EditText mEditTaskInformation;
    @BindView(R.id.txt_type_lost)
    TextView mTxtTypeLost;
    @BindView(R.id.txt_type_found)
    TextView mTxtTypeFound;
    @BindView(R.id.edit_item_place)
    EditText mEditItemPlace;


    public static final int TASK_LOST = 0;
    public static final int TASK_FOUND = 1;


    /**
     * 0代表丢了东西，1代表捡到东西
     */
    private int lostFoundTaskType = TASK_LOST;
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


    private LostFoundTaskInfoModel mInfoModel;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_release_lost_found_task;
    }

    @Override
    protected void initVariable() {
        //获得权限
        FileUtil.verifyStoragePermissions(ReleaseLostFoundTaskActivity.this);
    }


    @Override
    protected void initView() {

        setActivityTitle(getString(R.string.title_release_task));
        onTxtTypeLostClicked();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public FillTaskInfoContract.Presenter initPresenter() {
        return new FillTaskInfoPresenter(this);
    }


    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, ReleaseLostFoundTaskActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick(R.id.txt_type_lost)
    public void onTxtTypeLostClicked() {
        mTxtTypeLost.setSelected(true);
        mTxtTypeFound.setSelected(false);
        lostFoundTaskType = TASK_LOST;
    }

    @OnClick(R.id.txt_type_found)
    public void onTxtTypeFoundClicked() {
        mTxtTypeLost.setSelected(false);
        mTxtTypeFound.setSelected(true);
        lostFoundTaskType = TASK_FOUND;
    }


    @OnClick(R.id.btn_release_task)
    public void onBtnReleaseTaskClicked() {

        mProgressDialog = new DialogUtil().new NativeProgressDialog();
        mProgressDialog
                .initDialog(ReleaseLostFoundTaskActivity.this)
                .setMessage("请稍后")
                .showDialog();


        mInfoModel = new LostFoundTaskInfoModel();
        mInfoModel.setName(mEditItemName.getText().toString().trim());
        mInfoModel.setPlace(mEditItemPlace.getText().toString().trim());
        mInfoModel.setLostFoundType(lostFoundTaskType);
        mInfoModel.setRemark(mEditTaskInformation.getText().toString().trim());
        if (!hasImg) {
            mPresenter.checkLostFoundTaskInfo(mInfoModel);
        } else {
            UpLoadHelper.upLoadImgToQiNiu(photoPath, this);
        }
    }


    /**
     * 图片上传成功
     *
     * @param s 图片外链
     */
    @Override
    public void onDataLoaded(String s) {
        mInfoModel.setWithImg(true);
        mInfoModel.setImgUrl(s);
        mPresenter.checkLostFoundTaskInfo(mInfoModel);
    }

    /**
     * 图片上传失败
     *
     * @param error
     */
    @Override
    public void onFailedLoaded(int error) {
        ToastUtil.showToast(error);
    }


    /**
     * 信息格式正确
     */
    @Override
    public void onDataTrue() {

        Bundle bundle = new Bundle();
        bundle.putInt(ChooseCardActivity.BUNDLE_KEY_TASK_TYPE, ChooseCardActivity.TASK_LOST_FOUND);
        bundle.putSerializable(ChooseCardActivity.BUNDLE_KEY_WATER_TASK_INFO, mInfoModel);
        ChooseCardActivity.startActivity(this, bundle);
    }

    @Override
    public void onDataFalse(String error) {
        ToastUtil.showToast(error);
    }


    /**
     * 显示底部dialog
     */
    @OnClick(R.id.img_add_photo)
    public void onImgAddPhotoClicked() {

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


    /**
     * 设置底部dialog点击事件
     */
    private void setDialogOnClick() {

        //拍照选项点击事件
        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String filePath = FileUtil.createNewFile(ReleaseLostFoundTaskActivity.this, "TaskGo");
                photoPath = FileUtil.openCamera(ReleaseLostFoundTaskActivity.this, filePath);
                bottomDialog.dismiss();
            }
        });

        //相册选项点击事件
        btnFromAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FileUtil.openAlbum(ReleaseLostFoundTaskActivity.this);
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
                    mImgAddPhoto.setImageBitmap(BitmapFactory.decodeFile(photoPath));
                    hasImg = true;
                    break;
                case FileUtil.ALBUM_REQUEST:
                    photoPath = FileUtil.getSelectedPicturePath(data, ReleaseLostFoundTaskActivity.this);
                    mImgAddPhoto.setImageBitmap(BitmapFactory.decodeFile(photoPath));
                    hasImg = true;
                    break;
                default:
                    break;
            }
        }
    }
}
