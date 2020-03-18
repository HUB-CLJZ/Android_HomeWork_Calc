package com.example.zxingdemo;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

/***
 *  参考文献：https://blog.csdn.net/xch_yang/article/details/82147461
 *
 * */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEtInput;
    private Button mBtnGenerate;
    private ImageView mImg;
    private Bitmap mBitmap;
    private int[] pixels;
    private long TIME_DELARY = 5000;
    private String value;
    private Timer timer;
    private TimerTask timerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        mEtInput = findViewById(R.id.et_input);
        mBtnGenerate = findViewById(R.id.btn_generate);
        mImg = findViewById(R.id.img);

        mBtnGenerate.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.btn_generate:
                timer = new Timer();
                timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        value = UUID.randomUUID() + "#" + mEtInput.getText().toString();
                        System.out.println(value);
                        mBitmap = generateBitmap(value, 400, 400);
                        //  scanningImage();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mImg.setImageBitmap(mBitmap);
                            }
                        });
                    }
                };
                timer.scheduleAtFixedRate(timerTask, 0, TIME_DELARY);
                break;
        }
    }

    /**
     * 生成固定大小的二维码(不需网络权限)
     *
     * @param content 需要生成的内容
     * @param width   二维码宽度
     * @param height  二维码高度
     * @return
     */
    private Bitmap generateBitmap(String content, int width, int height) {
        //二维码写入类
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        /***
         * EncodeHintType二维码的参数
         *
         * CHARACTER_SET
         * 字符集/字符转码格式，通常使用UTF-8，格式不对可能导致乱码。传null时，默认使用 “ISO-8859-1”
         *
         * ERROR_CORRECTION
         * 容错率，也就是纠错水平，二维码破损一部分也能扫码就归功于容错率，
         * 容错率可分为L、 M、 Q、 H四个等级，其分别占比为：L：7% M：15% Q：25% H：35%。传null时，默认使用 “L”
         * 当然容错率越高，二维码能存储的内容也随之变小。
         *
         * MARGIN
         * 二维码和边框的空白区域宽度
         *
         * */
        Map<EncodeHintType, String> hints = new HashMap<>();

        //设置编码为UTF-8编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

        //设置空白区域的边距
        hints.put(EncodeHintType.MARGIN, "1");

        //设置容错率
        hints.put(EncodeHintType.ERROR_CORRECTION, "L");

        try {

            /**二维码矩阵
             *
             * content：生成的文字
             * BarcodeFormat.QR_CODE：生成的为二维码
             * hints：二维码的属性
             * */
            BitMatrix encode = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);

            //图像矩阵，用于填充颜色
            pixels = new int[width * height];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (encode.get(j, i)) {
                        //有内容部分的颜色
                        pixels[i * width + j] = Color.BLACK;
                    } else {
                        //无内容部分的颜色
                        pixels[i * width + j] = Color.WHITE;
                    }
                }
            }
            //创建图像
            return Bitmap.createBitmap(pixels, 0, width, width, height, Bitmap.Config.RGB_565);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }


    // 解析QR图片
    private void scanningImage() {

        //二维码的属性
        Map<DecodeHintType, String> hints = new HashMap<>();
        hints.put(DecodeHintType.CHARACTER_SET, "utf-8");

        // 获得待解析的二维码数组
        RGBLuminanceSource source = new RGBLuminanceSource(400, 400, pixels);

        //二进制图像
        BinaryBitmap bitmap1 = new BinaryBitmap(new HybridBinarizer(source));

        //二维码读取类
        QRCodeReader reader = new QRCodeReader();

        //读取的结果
        Result result;
        try {

            //获取读取的结果
            result = reader.decode(bitmap1, hints);
            // 得到解析后的文字
            String str = result.getText();
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (ChecksumException e) {
            e.printStackTrace();
        } catch (FormatException e) {
            e.printStackTrace();
        }
    }
}
