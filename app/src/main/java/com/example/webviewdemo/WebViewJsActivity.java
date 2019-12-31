package com.example.webviewdemo;

import android.app.Activity;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import java.net.URL;
import java.util.Set;

public class WebViewJsActivity extends Activity implements View.OnClickListener {
    private WebView mWebView;
    private Button mLoadUrlBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_js);
        mLoadUrlBt = findViewById(R.id.load_url);
        mLoadUrlBt.setOnClickListener(this);
        findViewById(R.id.evaluate_bt).setOnClickListener(this);

        mWebView = findViewById(R.id.webview_js);
        WebSettings settings = mWebView.getSettings();
        //settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl("file:///android_asset/demo.html");
        shouldOverrideUrlLoading();
        addJavascriptInterface();
        onWebChromeClient();
    }

    public void androidToJS(){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT){
            loadUrlToJS();
        }else {
            evaluateJavascript();
        }
    }

    //通过loadurl调用js方法
    private void loadUrlToJS(){
        mWebView.loadUrl("javascript:callJS()");
    }

    //通过evaluateJavascript与js交互
    private void evaluateJavascript(){
        mWebView.evaluateJavascript("javascript:callJS()", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {

            }
        });
    }

    public void addJavascriptInterface(){
        mWebView.addJavascriptInterface(new MyObject(), "toast");
    }

    class MyObject{
        @JavascriptInterface
        public void toast(String message){
            Log.d("djfkj",message);
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    public void shouldOverrideUrlLoading(){
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
               Uri url =  request.getUrl();
               if(url.getScheme().equals("demo")){
                   if(url.getAuthority().equals("webview")){
                       Set<String> paramNames = url.getQueryParameterNames();
                       StringBuilder builder = new StringBuilder();
                       for (String paramName: paramNames){
                           String value = url.getQueryParameter(paramName);
                           builder.append(paramName);
                           builder.append("=");
                           builder.append(value);
                           builder.append("; ");
                       }
                       String value =builder.toString();
                       Log.d("WebView", value);
//                       mWebView.loadUrl("javascript:callbackValue('dkjkdj')");

                       mWebView.evaluateJavascript("javascript:callbackValue('dkjkdj')", new ValueCallback<String>() {
                           @Override
                           public void onReceiveValue(String value) {

                           }
                       });
                   }
               }
                return true;
            }
        });
    }

    public void onWebChromeClient(){
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                return super.onJsConfirm(view, url, message, result);
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                Log.d("WebView", "url = "+ url + "message = " +message);
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.load_url:
                loadUrlToJS();
                break;
            case R.id.evaluate_bt:
                evaluateJavascript();
                break;
        }

    }
}
