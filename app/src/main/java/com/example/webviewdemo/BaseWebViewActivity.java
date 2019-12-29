package com.example.webviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BaseWebViewActivity extends AppCompatActivity {

    private WebView mBaseWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_web_view);
        mBaseWebView = findViewById(R.id.base_webview);
        WebSettings settings = mBaseWebView.getSettings();
        //webView可以执行js代码
        settings.setJavaScriptEnabled(true);

        //打开网页时，不用系统浏览器，在当前WebView中显式
        mBaseWebView.setWebViewClient(new WebViewClient());

        mBaseWebView.loadUrl("https://www.baidu.com/");
    }


}
