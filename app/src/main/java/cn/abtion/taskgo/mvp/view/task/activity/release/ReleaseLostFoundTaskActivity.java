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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseToolBarPresenterActivity;
import cn.abtion.taskgo.base.data.DataCallBack;
import cn.abtion.taskgo.data.UpLoadHelper;
import cn.abtion.taskgo.mvp.contract.task.ReleaseTaskContract;
import cn.abtion.taskgo.mvp.model.task.request.ReleaseLostFoundTaskRequest;
import cn.abtion.taskgo.mvp.presenter.task.ReleaseTaskPresenter;
import cn.abtion.taskgo.utils.DialogUtil;
import cn.abtion.taskgo.utils.FileUtil;
import cn.abtion.taskgo.utils.ToastUtil;

/**
 * @author FanHongyu.
 * @since 18/1/30 12:47.
 * email fanhongyu@hrsoft.net.
 */

public class ReleaseLostFoundTaskActivity extends BaseToolBarPresenterActivity<ReleaseTaskContract.Presenter>
        implements ReleaseTaskContract.View, DataCallBack.Callback<String> {

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


    /**
     * 0代表丢了东西，1代表捡到东西
     */
    private String lostFoundTaskType = "0";
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


    @Override
    public ReleaseTaskContract.Presenter initPresenter() {
        return new ReleaseTaskPresenter(this);
    }

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
        lostFoundTaskType = "0";
    }

    @OnClick(R.id.txt_type_found)
    public void onTxtTypeFoundClicked() {

        mTxtTypeLost.setSelected(false);
        mTxtTypeFound.setSelected(true);
        lostFoundTaskType = "1";
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


    @OnClick(R.id.btn_release_task)
    public void onBtnReleaseTaskClicked() {

        mProgressDialog = new DialogUtil().new NativeProgressDialog();
        mProgressDialog
                .initDialog(ReleaseLostFoundTaskActivity.this)
                .setMessage("请稍后")
                .showDialog();


        if (!hasImg) {

            mPresenter.releaseLostFoundTask(new ReleaseLostFoundTaskRequest(mEditItemName.getText().toString().trim()
                    , lostFoundTaskType, "no_picture"
                    , mEditItemPlace.getText().toString().trim()
                    , mEditTaskInformation.getText().toString().trim()));
        } else {
            UpLoadHelper.upLoadImgToQiNiu(photoPath, this);
        }
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
                    String picturePath = FileUtil.getSelectedPicturePath(data, ReleaseLostFoundTaskActivity.this);
                    mImgAddPhoto.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                    photoPath = picturePath;
                    hasImg = true;
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onReleaseSuccess() {
        mProgressDialog.hideDialog();
        ToastUtil.showToast("发布成功");
        finish();
    }

    @Override
    public void onReleaseFailed(String errorMessage) {
        ToastUtil.showToast(errorMessage);
    }


    /**
     * 图片上传成功
     *
     * @param s 图片外链
     */
    @Override
    public void onDataLoaded(String s) {

        mPresenter.releaseLostFoundTask(new ReleaseLostFoundTaskRequest(mEditItemName.getText().toString().trim()
                , lostFoundTaskType, s
                , mEditItemPlace.getText().toString().trim()
                , mEditTaskInformation.getText().toString().trim()));
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
}
