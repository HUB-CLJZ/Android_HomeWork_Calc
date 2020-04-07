package com.example.programme_49;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

/***
 * 资料：https://www.jianshu.com/p/3c94ae673e2a
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = findViewById(R.id.web_item);

        final ProgressBar progressBar = findViewById(R.id.progress_bar);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAllowContentAccess(true);

        webView.loadUrl("file:///android_asset/index.html");

        WebSettings settings = webView.getSettings();
        //允许缩放
        settings.setSupportZoom(true);
        //是否使用内部缩放机制
        settings.setBuiltInZoomControls(true);
        //缩放比例
        webView.setInitialScale(200);

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                }
            }
        });
    }
}
