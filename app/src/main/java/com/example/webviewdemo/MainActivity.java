package com.example.webviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBaseWebViewBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBaseWebViewBt = findViewById(R.id.base_webview_bt);
        mBaseWebViewBt.setOnClickListener(this);
        findViewById(R.id.webview_js_bt).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.base_webview_bt:
                intent = new Intent();
                intent.setClass(this, BaseWebViewActivity.class);
                break;
            case R.id.webview_js_bt:
                intent = new Intent();
                intent.setClass(this, WebViewJsActivity.class);
                break;
                default:
                    intent = new Intent();
                    intent.setClass(this, BaseWebViewActivity.class);
        }
        if(intent != null){
            startActivity(intent);
        }

    }
}
