package com.example.zhangqiaoyu.androidcamera;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends Activity {
    private static final int NONE = 0;
    private static final int PHOTO_GRAPH = 1;// 拍照
    private static final int PHOTO_ZOOM = 2; // 缩放
    private static final int PHOTO_RESOULT = 3;// 结果
    private static final String IMAGE_UNSPECIFIED = "image/*";
    private ImageView imageView = null;
    private Button btnPhone = null;
    private Button btnTakePicture = null;
    private String change_path = "/sdcard/DCIM/Camera";
    private String filename= null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        btnPhone = (Button) findViewById(R.id.btnPhone);
        btnPhone.setOnClickListener(onClickListener);
        btnTakePicture = (Button) findViewById(R.id.btnTakePicture);
        btnTakePicture.setOnClickListener(onClickListener);
    }
    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v==btnPhone){ //从相册获取图片
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_UNSPECIFIED);
                startActivityForResult(intent, PHOTO_ZOOM);
            }else if(v==btnTakePicture){ //从拍照获取图片
                String filePath = change_path;
                File localFile = new File(filePath);
                if (!localFile.exists()) {
                    localFile.mkdir();
                }
                filename = "IMG_"+DateFormat.format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.US))+".jpg";
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(change_path
                        ,filename)));
                startActivityForResult(intent, PHOTO_GRAPH);
            }
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == NONE)
            return;
        // 拍照
        if (requestCode == PHOTO_GRAPH) {
            // 设置文件保存路径
            String filePath = change_path;
            File localFile = new File(filePath);
            if (!localFile.exists()) {
                localFile.mkdir();
            }
            File picture = new File(change_path
             + "/"+filename);
            try {
                MediaStore.Images.Media.insertImage(getContentResolver(), picture.getAbsolutePath() ,"title","description");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,Uri.fromFile(picture)));
            startPhotoZoom(Uri.fromFile(picture));
        }
        if (data == null)
            return;
        // 读取相册缩放图片
        if (requestCode == PHOTO_ZOOM) {
            startPhotoZoom(data.getData());
        }
        // 处理结果
        if (requestCode == PHOTO_RESOULT) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap photo = extras.getParcelable("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.JPEG, 75, stream);// (0-100)压缩文件
                //此处可以把Bitmap保存到sd卡中
                imageView.setImageBitmap(photo); //把图片显示在ImageView控件上
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    /**
     * 收缩图片
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 500);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_RESOULT);
    }
}
