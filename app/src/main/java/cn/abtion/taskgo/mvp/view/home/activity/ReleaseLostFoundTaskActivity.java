package cn.abtion.taskgo.mvp.view.home.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiniu.android.common.AutoZone;
import com.qiniu.android.common.FixedZone;
import com.qiniu.android.common.Zone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.abtion.taskgo.R;
import cn.abtion.taskgo.base.activity.BaseToolBarPresenterActivity;
import cn.abtion.taskgo.base.contract.BaseContract;
import cn.abtion.taskgo.base.presenter.BasePresenter;
import cn.abtion.taskgo.utils.FileUtil;
import cn.abtion.taskgo.utils.ToastUtil;

/**
 * @author FanHongyu.
 * @since 18/1/30 12:47.
 * email fanhongyu@hrsoft.net.
 */

public class ReleaseLostFoundTaskActivity extends BaseToolBarPresenterActivity {

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


    private AlertDialog bottomDialog;
    private TextView btnTakePhoto;
    private TextView btnFromAlbum;
    private TextView btnCancel;
    private String photoPath;

    @Override
    public BaseContract.Presenter initPresenter() {
        return new BasePresenter<>(this);
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
    }

    @OnClick(R.id.txt_type_found)
    public void onTxtTypeFoundClicked() {

        mTxtTypeLost.setSelected(false);
        mTxtTypeFound.setSelected(true);
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

        uploadImg2QiNiu();
        ToastUtil.showToast(R.string.toast_release_task_successful);
        finish();
    }

    private void uploadImg2QiNiu() {

        Configuration config = new Configuration.Builder()
                .zone(FixedZone.zone1)
                .build();

        UploadManager uploadManager = new UploadManager(config);
        // 设置图片路径、上传后文件名、token
        String token ="V4bRlWyjq34V1c2WVvd39rm4WR7qAcaTtQ0Zs7rL:yqnN9yrv6um26wANvEOy-086nEs=:eyJzY29wZSI6InBpY3R1cmUiLCJkZWFkbGluZSI6MTUxNzU1NzY1M30=";
        uploadManager.put(photoPath, "test.png", token, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject res) {
                // info.error中包含了错误信息，可打印调试
                // 上传成功后将key值上传到自己的服务器
                if (info.isOK()) {


                    String imgUrl = "http://p0y1qzu73.bkt.clouddn.com/"+key;

                    Log.d("qiniu", "complete: "+info.toString());
                    Log.d("qiniu", "complete: "+res.toString());

                } else {
                    Log.d("qiniu", "complete: "+info.error);
                }
            }
        }, null);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            switch (requestCode) {

                case FileUtil.CAMERA_REQUEST:
                    mImgAddPhoto.setImageBitmap(BitmapFactory.decodeFile(photoPath));
                    break;
                case FileUtil.ALBUM_REQUEST:

                    String picturePath = FileUtil.getSelectedPicturePath(data, ReleaseLostFoundTaskActivity.this);
                    mImgAddPhoto.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                    photoPath = picturePath;
                    break;
                default:
                    break;
            }
        }
    }
}
