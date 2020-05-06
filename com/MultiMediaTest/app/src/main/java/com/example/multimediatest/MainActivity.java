package com.example.multimediatest;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private String mImagePath;                   //用于存储最终目录，即根目录 / 要操作（存储文件）的文件夹

    public static final String SD_APP_DIR_NAME = "TestDir"; //存储程序在外部SD卡上的根目录的名字
    public static final String PHOTO_DIR_NAME = "photo";    //存储照片在根目录下的文件夹名字
    public static final String VOICE_DIR_NAME = "voice";    //存储音频在根目录下的文件夹名字
    public static final String VIDEO_DIR_NAME = "video";    //存储视频在根目录下的文件夹名字

    public static final int PHOTO_RESULT_CODE = 100;        //标志符，图片的结果码，判断是哪一个Intent
    public static final int VOICE_RESULT_CODE = 101;        //标志符，音频的结果码，判断是哪一个Intent
    public static final int VIDEO_RESULT_CODE = 102;        //标志符，视频的结果码，判断是哪一个Intent

    private Uri mImageUri;                                  //指定的uri

    private String mImageName;                              //保存的图片的名字

    private File mImageFile;                                //图片文件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "开始...");

        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

        //按钮的点击事件
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //检查是否获得写入权限，未获得则向用户请求
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    //未获得，向用户请求
                    Log.d(TAG, "无读写权限，开始请求权限。");
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]
                            {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
                } else {
                    Log.d(TAG, "有读写权限，准备启动相机。");
                    //启动照相机
                    startCamera();
                }
            }
        });
    }

    /**
     * 返回用户是否允许权限的结果，并处理
     */
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResult) {
        if (requestCode == 200) {
            //用户允许权限
            if (grantResult[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "用户已允许权限，准备启动相机。");
                //启动照相机
                startCamera();
            } else {  //用户拒绝
                Log.d(TAG, "用户已拒绝权限，程序终止。");
                Toast.makeText(this, "程序需要写入权限才能运行", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 启动相机，创建文件，并要求返回uri
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void startCamera() {
        Intent intent = new Intent();
        //指定动作，启动相机
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        Log.d(TAG, "指定启动相机动作，完成。");
        //创建文件
        createImageFile();
        Log.d(TAG, "创建图片文件结束。");
        //添加权限
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        Log.d(TAG, "添加权限。");
        //获取uri
        mImageUri = FileProvider.getUriForFile(this, "com.my.example.multimediatest.provider", mImageFile);
        Log.d(TAG, "根据图片文件路径获取uri。");
        //将uri加入到额外数据
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
        Log.d(TAG, "将uri加入启动相机的额外数据。");
        Log.d(TAG, "启动相机...");
        //启动相机并要求返回结果
        startActivityForResult(intent, PHOTO_RESULT_CODE);
        Log.d(TAG, "拍摄中...");
    }

    /**
     * 创建图片文件
     */
    private void createImageFile(){
        Log.d(TAG, "开始创建图片文件...");
        //设置图片文件名（含后缀），以当前时间的毫秒值为名称
        mImageName = Calendar.getInstance().getTimeInMillis() + ".jpg";
        Log.d(TAG, "设置图片文件的名称为："+mImageName);
        //创建图片文件
        mImageFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/" + SD_APP_DIR_NAME + "/" + PHOTO_DIR_NAME + "/", mImageName);
        //将图片的绝对路径设置给mImagePath，后面会用到
        mImagePath = mImageFile.getAbsolutePath();
        //按设置好的目录层级创建
        mImageFile.getParentFile().mkdirs();
        Log.d(TAG, "按设置的目录层级创建图片文件，路径："+mImagePath);
        //不加这句会报Read-only警告。且无法写入SD
        mImageFile.setWritable(true);
        Log.d(TAG, "将图片文件设置可写。");
    }

    /**
     * 处理返回结果。
     * 1、图片
     * 2、音频
     * 3、视频
     *
     * @param requestCode 请求码
     * @param resultCode  结果码 成功 -1 失败 0
     * @param data        返回的数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "拍摄结束。");
        if (resultCode == Activity.RESULT_OK) {
            Log.d(TAG, "返回成功。");
            Log.d(TAG, "请求码：" + requestCode + "  结果码：" + resultCode + "  data：" + data);
            switch (requestCode) {
                case PHOTO_RESULT_CODE: {
                    Bitmap bitmap = null;
                    try {
                        //根据uri设置bitmap
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mImageUri);
                        Log.d(TAG, "根据uri设置bitmap。");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //将图片保存到SD的指定位置
                    savePhotoToSD(bitmap);
                    //更新系统图库
                    updateSystemGallery();
                    Log.d(TAG, "结束。");
                    break;
                }
                case VOICE_RESULT_CODE: {
//                    saveVoiceToSD();
                    break;
                }
                case VIDEO_RESULT_CODE: {
//                    saveVideoTOSD();
                    break;
                }
            }
        }
    }

    /**
     * 保存照片到SD卡的指定位置
     */
    private void savePhotoToSD(Bitmap bitmap) {
        Log.d(TAG, "将图片保存到指定位置。");
        //创建输出流缓冲区
        BufferedOutputStream os = null;
        try {
            //设置输出流
            os = new BufferedOutputStream(new FileOutputStream(mImageFile));
            Log.d(TAG, "设置输出流。");
            //压缩图片，100表示不压缩
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            Log.d(TAG, "保存照片完成。");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    //不管是否出现异常，都要关闭流
                    os.flush();
                    os.close();
                    Log.d(TAG, "刷新、关闭流");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 更新系统图库
     */
    private void updateSystemGallery() {
        //把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(this.getContentResolver(),
                    mImageFile.getAbsolutePath(), mImageName, null);
            Log.d(TAG, "将图片文件插入系统图库。");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + mImagePath)));
        Log.d(TAG, "通知系统图库更新。");
    }

    /**
     * 保存音频到SD卡的指定位置
     */
    private void saveVoiceToSD() {

    }

    /**
     * 保存视频到SD卡的指定位置
     */
    private void saveVideoTOSD() {

    }

}
