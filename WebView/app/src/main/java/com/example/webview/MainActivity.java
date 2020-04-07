package com.example.webview;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * 1.在src目录下的main目录创建assets文件夹
 * 2.添加 webView.getSettings().setAllowContentAccess(true);
 * 3.加载路径file:///android_asset/index.html
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = findViewById(R.id.web_iteam);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);

        webView.getSettings().setAllowContentAccess(true);
//      webView.loadUrl("https://c-ssl.duitang.com/uploads/item/201208/30/20120830173930_PBfJE.thumb.700_0.jpeg");

            webView.loadUrl("file:///android_asset/index.html");

    }
}
