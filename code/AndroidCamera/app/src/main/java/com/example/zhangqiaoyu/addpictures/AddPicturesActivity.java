package com.example.zhangqiaoyu.addpictures;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.format.DateFormat;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;
import android.widget.Toast;
import android.provider.MediaStore;

public class AddPicturesActivity extends Activity {

    private GridView gridView1;                   //网格显示缩略图
    private TextView submitPictures;
    private TextView shareToWeChat;
    private Bitmap bmp;                               //导入临时图片
    private ArrayList<HashMap<String, Object>> imageItem;
    private ArrayList<Uri> imageUris ;
    private SimpleAdapter simpleAdapter;     //适配器


    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 2;
    private static final int NONE = 0;
    private static final int PHOTO_GRAPH = 1;// 拍照
    private static final int PHOTO_ZOOM = 2; // 缩放
    private static final int PHOTO_RESOULT = 3;// 结果
    private static final int PHOTO_BEAUTIFY = 4;// 美化
    private static final String IMAGE_UNSPECIFIED = "image/*";
    private String change_path="/sdcard/DCIM/Camera";
    private String filename= null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpictures);
        /*
         * 防止键盘挡住输入框
         * 不希望遮挡设置activity属性 android:windowSoftInputMode="adjustPan"
         * 希望动态调整高度 android:windowSoftInputMode="adjustResize"
         */
        getWindow().setSoftInputMode(WindowManager.LayoutParams.
                SOFT_INPUT_ADJUST_PAN);
        //锁定屏幕
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_addpictures);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        }

        change_path=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath()+"/walknshot";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }

        //获取控件对象
        gridView1 = (GridView) findViewById(R.id.gridView1);

        /*
         * 载入默认图片添加图片加号
         * 通过适配器实现
         * SimpleAdapter参数imageItem为数据源 R.layout.gridview_addpic为布局
         */
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.gridview_addpic); //加号
        imageItem = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("itemImage", bmp);
        imageItem.add(map);
        simpleAdapter = new SimpleAdapter(this,
                imageItem, R.layout.gridview_addpic,
                new String[] { "itemImage"}, new int[] { R.id.imageView1});

        imageUris = new ArrayList<>();
        /*
         * HashMap载入bmp图片在GridView中不显示,但是如果载入资源ID能显示 如
         * map.put("itemImage", R.drawable.img);
         * 解决方法:
         *              1.自定义继承BaseAdapter实现
         *              2.ViewBinder()接口实现
         *  参考 http://blog.csdn.net/admin_/article/details/7257901
         */
        simpleAdapter.setViewBinder(new ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data,
                                        String textRepresentation) {
                // TODO Auto-generated method stub
                if(view instanceof ImageView && data instanceof Bitmap){
                    ImageView i = (ImageView)view;
                    i.setImageBitmap((Bitmap) data);
                    return true;
                }
                return false;
            }
        });
        gridView1.setAdapter(simpleAdapter);

        /*
         * 监听GridView点击事件
         * 报错:该函数必须抽象方法 故需要手动导入import android.view.View;
         */
        gridView1.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                if( position == 0 && imageItem.size() == 10) { //第一张为默认图片
                    Toast.makeText(AddPicturesActivity.this, "照片数最多为9张", Toast.LENGTH_SHORT).show();
                }
                else if(position == 0) { //点击图片位置为+ 0对应0张图片
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddPicturesActivity.this);
                    builder.setTitle("选择照片");
                    //    指定下拉列表的显示数据
                    final String[] selects = {"拍取照片", "打开相册"};
                    //    设置一个下拉的列表选择项
                    builder.setItems(selects, new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            switch (which) {
                                case 0 : {
                                    String filePath = change_path;
                                    File localFile = new File(filePath);
                                    if (!localFile.exists()) {
                                        localFile.mkdir();
                                    }
                                    filename = "IMG_" + DateFormat.format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.US)) + ".jpg";
                                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(change_path
                                            , filename)));
                                    startActivityForResult(intent, PHOTO_GRAPH);
                                    break;
                                }
                                case 1 : {
                                    Intent intent = new Intent(Intent.ACTION_PICK, null);
                                    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_UNSPECIFIED);
                                    startActivityForResult(intent, PHOTO_ZOOM);
                                    break;
                                }
                            };
                        }
                    });
                    builder.show();
                }
                else {
                    dialog(position);
                    //Toast.makeText(AddPicturesActivity.this, "点击第" + (position + 1) + " 号图片",
                    //		Toast.LENGTH_SHORT).show();
                }
            }
        });
        //设置Textview监听事件
        submitPictures = (TextView) findViewById(R.id.submitPictures);
        submitPictures.setOnClickListener(onClickListener);
        shareToWeChat = (TextView) findViewById(R.id.shareToWeChat);
        shareToWeChat.setOnClickListener(onClickListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode,grantResults);
    }

    private void doNext(int requestCode, int[] grantResults) {
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
            } else {
                // Permission Denied
            }
        }
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v==submitPictures) { //提交照片

            }
            else if(v==shareToWeChat){ //微信分享
                AlertDialog.Builder builder = new AlertDialog.Builder(AddPicturesActivity.this);
                builder.setTitle("分享");
                //    指定下拉列表的显示数据
                final String[] selects = {"微信朋友圈", "微信好友"};
                //    设置一个下拉的列表选择项
                builder.setItems(selects, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        switch (which) {
                            case 0 : { //分享到微信朋友圈
                                Intent intent = new Intent("android.intent.action.VIEW");
                                ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
                                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setComponent(comp);
                                intent.setAction(Intent.ACTION_SEND_MULTIPLE);
                                intent.setType("image/*");
                                intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
                                intent.putExtra("Kdescription", "wwwwwwwwwwwwwwwwwwww");
                                startActivity(intent);
                                break;
                            }
                            case 1 : { //分享给微信好友
                                break;
                            }
                        };
                    }
                });
                builder.show();
            }
        }
    };

    //获取图片路径 响应startActivityForResult
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == NONE)
            return;
        // 拍照
        if (requestCode == PHOTO_GRAPH) {
            // 设置文件保存路径
            String filePath = change_path;
            File localFile = new File(filePath);
            if (!localFile.exists()) {
                boolean b = localFile.mkdir();
                if(!b) {
                    Toast.makeText(AddPicturesActivity.this, "创建文件夹失败", Toast.LENGTH_SHORT).show();
                }
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
        // 美化照片
        if (requestCode == PHOTO_BEAUTIFY) {
            Intent intent=new Intent(AddPicturesActivity.this,BeautifyPictureActivity.class);
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            Bundle extras = data.getExtras();
            Bitmap newbmp;
            if (extras != null) {
                newbmp = extras.getParcelable("data");
                newbmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            }
            byte [] bitmapByte =baos.toByteArray();
            intent.putExtra("bitmap", bitmapByte);
            startActivityForResult(intent,PHOTO_RESOULT);
        }
        // 处理结果
        if (requestCode == PHOTO_RESOULT) {
            byte [] bis=data.getByteArrayExtra("returnbitmap");
            Bitmap addbmp= BitmapFactory.decodeByteArray(bis, 0, bis.length);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            addbmp.compress(Bitmap.CompressFormat.JPEG, 75, stream);// (0-100)压缩文件
            //此处可以把Bitmap保存到sd卡中
            String filePath = change_path;
            File localFile = new File(filePath);
            if (!localFile.exists()) {
                boolean b = localFile.mkdir();
                if(!b) {
                    Toast.makeText(AddPicturesActivity.this, "创建文件夹失败", Toast.LENGTH_SHORT).show();
                }
            }
            filename = "IMG_" + DateFormat.format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.US)) + ".jpg";
            File picture = new File(change_path + "/"+filename);
            try{
                FileOutputStream out=new FileOutputStream(picture);
                out.write(stream.toByteArray());
                out.flush();
                out.close();}
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            try {
                MediaStore.Images.Media.insertImage(getContentResolver(), picture.getAbsolutePath() ,"title","description");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,Uri.fromFile(picture)));
            imageUris.add(Uri.fromFile(picture));


            HashMap<String, Object> map = new HashMap<>();
            map.put("itemImage", addbmp);
            imageItem.add(map);
            simpleAdapter = new SimpleAdapter(this,
                    imageItem, R.layout.gridview_addpic,
                    new String[] { "itemImage"}, new int[] { R.id.imageView1});
            simpleAdapter.setViewBinder(new ViewBinder() {
                @Override
                public boolean setViewValue(View view, Object data,
                                            String textRepresentation) {
                    // TODO Auto-generated method stub
                    if(view instanceof ImageView && data instanceof Bitmap){
                        ImageView i = (ImageView)view;
                        i.setImageBitmap((Bitmap) data);
                        return true;
                    }
                    return false;
                }
            });
            gridView1.setAdapter(simpleAdapter);
            simpleAdapter.notifyDataSetChanged();
            //刷新后释放防止手机休眠后自动添加

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
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_BEAUTIFY);
    }

    /*
     * Dialog对话框提示用户删除操作
     * position为删除图片位置
     */
    protected void dialog(final int position) {
        AlertDialog.Builder builder = new Builder(AddPicturesActivity.this);
        builder.setMessage("确认移除已添加图片吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                imageItem.remove(position);
                simpleAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

}