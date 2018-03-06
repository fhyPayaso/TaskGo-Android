package cn.abtion.taskgo.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.ContentValues.TAG;

/**
 * @author fhyPayaso
 * @since 2018/1/6 on 下午6:49
 * fhyPayaso@qq.com
 */
public class FileUtil {


    private static final int REQUEST_EXTERNAL_STORAGE = 100;


    private static final String[] PERMISSIONS_STORAGE = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE
            , Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA};


    /**
     * 相机和相册的请求码
     */
    public final static int CAMERA_REQUEST = 0;
    public final static int ALBUM_REQUEST = 1;

    /**
     * 动态申请SD卡读写、相机等权限
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {

        int permissionWrite = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionWrite != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }


    /**
     * 打开系统相册
     *
     * @param activity
     */
    public static void openAlbum(Activity activity) {

        Intent albumIntent = new Intent(Intent.ACTION_PICK, null);
        albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        activity.startActivityForResult(albumIntent, ALBUM_REQUEST);
    }

    /**
     * 获取从相册选择图片的路径
     *
     * @return
     */
    public static String getSelectedPicturePath(Intent data, Context context) {


        Uri selectedImage = data.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();

        return picturePath;
    }

    /**
     * 打开系统相机并指定拍摄相片的存储路径
     *
     * @param activity
     */
    public static String openCamera(Activity activity, String savePath) {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

        File out = new File(savePath, System.currentTimeMillis() + ".jpg");
        Log.i(TAG, "openCamera: "+ out.getPath());
        Uri uri = Uri.fromFile(out);
        Log.i(TAG, "openCamera: debug ");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        activity.startActivityForResult(intent, CAMERA_REQUEST);

        return out.getPath();
    }


    /**
     * 检查是否已挂载SD卡镜像（是否存在SD卡）
     *
     * @return
     */
    public static boolean isMountedSDCard() {

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 获取SD卡剩余容量（单位Byte）
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public static long getSDFreeSize() {
        if (isMountedSDCard()) {
            // 取得SD卡文件路径
            File path = Environment.getExternalStorageDirectory();
            StatFs sf = new StatFs(path.getPath());
            // 获取单个数据块的大小(Byte)
            long blockSize = sf.getBlockSize();
            // 空闲的数据块的数量
            long freeBlocks = sf.getAvailableBlocks();

            // 返回SD卡空闲大小
            return freeBlocks * blockSize;
            // 单位Byte
        } else {
            return 0;
        }
    }

    /**
     * 获取SD卡总容量（单位Byte）
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public static long getSDAllSize() {
        if (isMountedSDCard()) {
            // 取得SD卡文件路径
            File path = Environment.getExternalStorageDirectory();
            StatFs sf = new StatFs(path.getPath());
            // 获取单个数据块的大小(Byte)
            long blockSize = sf.getBlockSize();
            // 获取所有数据块数
            long allBlocks = sf.getBlockCount();
            // 返回SD卡大小（Byte）
            return allBlocks * blockSize;
        } else {
            return 0;
        }
    }


    /**
     * 打开视频
     *
     * @param mContext
     * @param videoPath
     */
    public static void openVideo(Context mContext, String videoPath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = Uri.fromFile(new File(videoPath));
        intent.setDataAndType(uri, "video/*");
        mContext.startActivity(Intent.createChooser(intent, "打开视频"));
    }


    /**
     * 判断文件是否存在
     *
     * @param filePath 文件路径
     * @return 是否存在
     * @throws Exception
     */
    public static Boolean isExsit(String filePath) {
        Boolean flag = false;
        try {
            File file = new File(filePath);
            if (file.exists()) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }


    /**
     * 新建文件夹
     *
     * @param context
     * @param fileName
     * @return 文件路径
     */
    public static String createNewFile(Context context, String fileName) {

        String filePath;
        // 如SD卡已存在，则存储；反之存在data目录下
        if (isMountedSDCard()) {
            // SD卡路径
            filePath = Environment.getExternalStorageDirectory().toString() + "/" + fileName;
        } else {
            filePath = context.getCacheDir().getPath() + File.separator + "/" + fileName;
        }

        File newFile = new File(filePath);
        if (!newFile.exists()) {
            boolean isCreate = newFile.mkdirs();
            Log.i(TAG, filePath + " has created. " + isCreate);
        } else {
            Log.i(TAG, "createNewFile: 该文件已经存在");
        }

        return filePath;
    }


    /**
     * 图片保存JPEG
     *
     * @param filePath 文件路径
     * @param bitmap   文件内容
     * @throws IOException
     */
    public static String saveAsJPEG(Bitmap bitmap, String filePath) throws IOException {

        FileOutputStream fos = null;
        String imgPath = null;


        String imgName = System.currentTimeMillis() + ".jpg";

        try {

            File file = new File(filePath, imgName);
            imgPath = file.getPath();

            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (fos != null) {
                fos.close();
            }
        }

        return imgPath;
    }

    /**
     * 保存图片PNG
     *
     * @param filePath 文件路径+文件名
     * @param bitmap   文件内容
     * @throws IOException
     */
    public static void saveAsPNG(Bitmap bitmap, String filePath)
            throws IOException {
        FileOutputStream fos = null;

        try {
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }


}
