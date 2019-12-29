package com.example.webviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class WebViewJsActivity extends Activity implements View.OnClickListener {
    private WebView mWebView;
    private Button mLoadUrlBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_js);
        mLoadUrlBt = findViewById(R.id.load_url);
        mLoadUrlBt.setOnClickListener(this);
        mWebView = findViewById(R.id.webview_js);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl("file:///android_asset/demo.html");
        mWebView.setWebChromeClient(new WebChromeClient());



    }

    //通过loadurl调用js方法
    private void loadUrlToJS(){
        mWebView.loadUrl("javascript:callJS()");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.load_url:
                loadUrlToJS();
                break;
        }

    }
}
