package com.example.zhangqiaoyu.addpictures;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

/**
 * Created by zhangqiaoyu on 2017/7/12.
 */

public class BeautifyPictureActivity extends MyAppCompatActivity{

    private TextView oldRemember;
    private TextView sunShine;
    private TextView sketch;
    private TextView cancelBeautify;
    private TextView savePicture;
    private Bitmap bmp;
    private Bitmap newbmp;
    private ImageView tempImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beautify);

        oldRemember = (TextView) findViewById(R.id.oldRemember);
        oldRemember.setOnClickListener(onClickListener);
        sunShine = (TextView) findViewById(R.id.sunShine);
        sunShine.setOnClickListener(onClickListener);
        sketch = (TextView) findViewById(R.id.sketch);
        sketch.setOnClickListener(onClickListener);
        cancelBeautify = (TextView) findViewById(R.id.cancelBeautify);
        cancelBeautify.setOnClickListener(onClickListener);
        savePicture = (TextView) findViewById(R.id.savePicture);
        savePicture.setOnClickListener(onClickListener);
        tempImage = (ImageView) findViewById(R.id.tempImage);

        Intent intent =getIntent();
        byte [] bis=intent.getByteArrayExtra("bitmap");
        bmp = BitmapFactory.decodeByteArray(bis, 0, bis.length);
        newbmp = bmp;
        tempImage.setImageBitmap(bmp);
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v == savePicture) { //提交照片
                Intent intent = new Intent();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                newbmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte [] bitmapByte = baos.toByteArray();
                intent.putExtra("returnbitmap", bitmapByte);
                setResult(RESULT_OK,intent);
                finish();
            }
            else if(v == cancelBeautify) { //恢复原状
                newbmp = bmp;
                tempImage.setImageBitmap(newbmp);
            }
            else if(v == oldRemember){ //怀旧效果
                OldRemeberImage();
                tempImage.setImageBitmap(newbmp);
            }
            else if(v == sunShine){ //光照效果
                SunshineImage();
                tempImage.setImageBitmap(newbmp);
            }
            else if(v == sketch){ //素描效果
                SketchImage();
                tempImage.setImageBitmap(newbmp);
            }
        }
    };

    //图片怀旧处理
    private void OldRemeberImage() {
        /*
         * 怀旧处理算法即设置新的RGB
         * R=0.393r+0.769g+0.189b
         * G=0.349r+0.686g+0.168b
         * B=0.272r+0.534g+0.131b
         */
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        int pixColor;
        int pixR;
        int pixG;
        int pixB;
        int newR;
        int newG;
        int newB;
        int[] pixels = new int[width * height];
        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < height; i++)
        {
            for (int k = 0; k < width; k++)
            {
                pixColor = pixels[width * i + k];
                pixR = Color.red(pixColor);
                pixG = Color.green(pixColor);
                pixB = Color.blue(pixColor);
                newR = (int) (0.393 * pixR + 0.769 * pixG + 0.189 * pixB);
                newG = (int) (0.349 * pixR + 0.686 * pixG + 0.168 * pixB);
                newB = (int) (0.272 * pixR + 0.534 * pixG + 0.131 * pixB);
                int newColor = Color.argb(255, newR > 255 ? 255 : newR, newG > 255 ? 255 : newG, newB > 255 ? 255 : newB);
                pixels[width * i + k] = newColor;
            }
        }
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        newbmp = bitmap;
    }

    private void SunshineImage() {
        /*
         * 算法原理：(前一个像素点RGB-当前像素点RGB+127)作为当前像素点RGB值
         * 在ABC中计算B点浮雕效果(RGB值在0~255)
         * B.r = C.r - B.r + 127
         * B.g = C.g - B.g + 127
         * B.b = C.b - B.b + 127
         * 光照中心取长宽较小值为半径,也可以自定义从左上角射过来
         */
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        int pixColor;
        int pixR;
        int pixG;
        int pixB;
        int newR;
        int newG;
        int newB;
        //围绕圆形光照
        int centerX = width / 2;
        int centerY = height / 2;
        int radius = Math.min(centerX, centerY);
        float strength = 150F;  //光照强度100-150
        int[] pixels = new int[width * height];
        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 1; i < height-1; i++)
        {
            for (int k = 1; k < width-1; k++)
            {
                //获取前一个像素颜色
                pixColor = pixels[width * i + k];
                pixR = Color.red(pixColor);
                pixG = Color.green(pixColor);
                pixB = Color.blue(pixColor);
                newR = pixR;
                newG = pixG;
                newB = pixB;
                //计算当前点到光照中心的距离,平面坐标系中两点之间的距离
                int distance = (int) (Math.pow((centerY-i), 2) + Math.pow((centerX-k), 2));
                if(distance < radius*radius)
                {
                    //按照距离大小计算增强的光照值
                    int result = (int)(strength*( 1.0-Math.sqrt(distance) / radius ));
                    newR = pixR + result;
                    newG = newG + result;
                    newB = pixB + result;
                }
                newR = Math.min(255, Math.max(0, newR));
                newG = Math.min(255, Math.max(0, newG));
                newB = Math.min(255, Math.max(0, newB));
                pixels[width * i + k] = Color.argb(255, newR, newG, newB);
            }
        }
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        newbmp = bitmap;
    }

    private void SketchImage()
    {
        //创建新Bitmap
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        int[] pixels = new int[width * height];    //存储变换图像
        int[] linpix = new int[width * height];     //存储灰度图像
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
        int pixColor = 0;
        int pixR = 0;
        int pixG = 0;
        int pixB = 0;
        int newR = 0;
        int newG = 0;
        int newB = 0;
        //灰度图像
        for (int i = 1; i < width - 1; i++)
        {
            for (int j = 1; j < height - 1; j++)   //拉普拉斯算子模板 { 0, -1, 0, -1, -5, -1, 0, -1, 0
            {
                //获取前一个像素颜色
                pixColor = pixels[width * i + j];
                pixR = Color.red(pixColor);
                pixG = Color.green(pixColor);
                pixB = Color.blue(pixColor);
                //灰度图像
                int gray=(int)(0.3*pixR+0.59*pixG+0.11*pixB);
                linpix[width * i + j] = Color.argb(255, gray, gray, gray);
                //图像反向
                gray=255-gray;
                pixels[width * i + j] = Color.argb(255, gray, gray, gray);
            }
        }
        int[] copixels = gaussBlur(pixels, width, height, 10, 10/3);   //高斯模糊 采用半径10
        int[] result = colorDodge(linpix, copixels);   //素描图像 颜色减淡
        bitmap.setPixels(result, 0, width, 0, 0, width, height);
        newbmp = bitmap;
    }

    //高斯模糊
    public static int[] gaussBlur(int[] data, int width, int height, int radius,
                                  float sigma) {

        float pa = (float) (1 / (Math.sqrt(2 * Math.PI) * sigma));
        float pb = -1.0f / (2 * sigma * sigma);

        // generate the Gauss Matrix
        float[] gaussMatrix = new float[radius * 2 + 1];
        float gaussSum = 0f;
        for (int i = 0, x = -radius; x <= radius; ++x, ++i) {
            float g = (float) (pa * Math.exp(pb * x * x));
            gaussMatrix[i] = g;
            gaussSum += g;
        }

        for (int i = 0, length = gaussMatrix.length; i < length; ++i) {
            gaussMatrix[i] /= gaussSum;
        }

        // x direction
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                float r = 0, g = 0, b = 0;
                gaussSum = 0;
                for (int j = -radius; j <= radius; ++j) {
                    int k = x + j;
                    if (k >= 0 && k < width) {
                        int index = y * width + k;
                        int color = data[index];
                        int cr = (color & 0x00ff0000) >> 16;
                        int cg = (color & 0x0000ff00) >> 8;
                        int cb = (color & 0x000000ff);

                        r += cr * gaussMatrix[j + radius];
                        g += cg * gaussMatrix[j + radius];
                        b += cb * gaussMatrix[j + radius];

                        gaussSum += gaussMatrix[j + radius];
                    }
                }

                int index = y * width + x;
                int cr = (int) (r / gaussSum);
                int cg = (int) (g / gaussSum);
                int cb = (int) (b / gaussSum);

                data[index] = cr << 16 | cg << 8 | cb | 0xff000000;
            }
        }

        // y direction
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                float r = 0, g = 0, b = 0;
                gaussSum = 0;
                for (int j = -radius; j <= radius; ++j) {
                    int k = y + j;
                    if (k >= 0 && k < height) {
                        int index = k * width + x;
                        int color = data[index];
                        int cr = (color & 0x00ff0000) >> 16;
                        int cg = (color & 0x0000ff00) >> 8;
                        int cb = (color & 0x000000ff);

                        r += cr * gaussMatrix[j + radius];
                        g += cg * gaussMatrix[j + radius];
                        b += cb * gaussMatrix[j + radius];

                        gaussSum += gaussMatrix[j + radius];
                    }
                }

                int index = y * width + x;
                int cr = (int) (r / gaussSum);
                int cg = (int) (g / gaussSum);
                int cb = (int) (b / gaussSum);
                data[index] = cr << 16 | cg << 8 | cb | 0xff000000;
            }
        }

        return data;
    }

    //颜色减淡
    public static int[] colorDodge(int[] baseColor, int[] mixColor) {

        for (int i = 0, length = baseColor.length; i < length; ++i) {
            int bColor = baseColor[i];
            int br = (bColor & 0x00ff0000) >> 16;
            int bg = (bColor & 0x0000ff00) >> 8;
            int bb = (bColor & 0x000000ff);

            int mColor = mixColor[i];
            int mr = (mColor & 0x00ff0000) >> 16;
            int mg = (mColor & 0x0000ff00) >> 8;
            int mb = (mColor & 0x000000ff);

            int nr = colorDodgeFormular(br, mr);
            int ng = colorDodgeFormular(bg, mg);
            int nb = colorDodgeFormular(bb, mb);

            baseColor[i] = nr << 16 | ng << 8 | nb | 0xff000000;
        }
        return baseColor;
    }

    private static int colorDodgeFormular(int base, int mix) {

        int result = base + (base * mix) / (255 - mix);
        result = result > 255 ? 255 : result;
        return result;

    }

}
