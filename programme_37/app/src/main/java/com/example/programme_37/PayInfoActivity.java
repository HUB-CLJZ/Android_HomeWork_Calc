package com.example.programme_37;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
 * 遗留问题：图片放大后，回到界面，二维码周期改变了
 */
public class PayInfoActivity extends AppCompatActivity {
    private int[] pixels;
    private ImageView iv_refresh;
    private Toolbar toolBar;
    private ImageView iv_qr_code;

    private Bitmap mBitmap;
    private TextView tv_info;

    private Timer timer = new Timer();
    private TimerTask timerTask;

    private String str;
    private String car_numebr;
    private String money;
    private Integer cycle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_info);

        Intent intent = getIntent();
        car_numebr = intent.getStringExtra("car_numebr");
        money = intent.getStringExtra("money");
        cycle = intent.getIntExtra("cycle", 0);

        initView();
        generateCode();
        click();
    }

    private void click() {

        //长按显示二维码显示信息
        iv_qr_code.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tv_info.setText(str);
                return false;
            }
        });


        final View view = View.inflate(this,R.layout.activity_full_screen,null);
        final ImageView photo = view.findViewById(R.id.iv_show_img);
        //单击时全屏显示二维码
        iv_qr_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photo.setImageBitmap(mBitmap);
                //替换为二维码的界面
                setContentView(view);
            }
        });

        //点击后进入全屏
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_pay_info);
                Intent intent = getIntent();
                car_numebr = intent.getStringExtra("car_numebr");
                money = intent.getStringExtra("money");
                cycle = intent.getIntExtra("cycle", 0);

                initView();
                generateCode();
                click();
            }
        });


        //点击左上角图片退出
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void generateCode() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                str = "车辆编号=" + car_numebr + ",付款金额=" + money;
                //生成二维码图片
                mBitmap = generateBitmap(str+UUID.randomUUID(), 240, 240);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //设置二维码
                        iv_qr_code.setImageBitmap(mBitmap);
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, cycle*1000);
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

    private void initView() {
        iv_refresh = (ImageView) findViewById(R.id.iv_refresh);
        toolBar = (Toolbar) findViewById(R.id.toolBar);
        iv_qr_code = (ImageView) findViewById(R.id.iv_qr_code);
        tv_info = (TextView) findViewById(R.id.tv_info);
    }
}
