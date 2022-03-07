package com.mkao.newsgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.mkao.newsgo.databinding.ActivityMainBinding;

public class Internet_news extends AppCompatActivity {
private ActivityMainBinding binding;
private WebView NwebView;
private String  url;
private static final String EXTRA_URL="com.mkao.newsgo.Internet_news.EXTRA_URL";

public static Intent getIntent(Context context, String url){
    Intent intent = new Intent(context,Internet_news.class);
    intent.putExtra(EXTRA_URL,url);
    return intent;
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        url = getIntent().getStringExtra(EXTRA_URL);
        setContentView(binding.getRoot());
        NwebView= binding.web;

        NwebView.loadUrl(url);
        binding.progressBar.setMax(100); binding.progressBar.setProgress(1);
        /*set bars progress according to the url load*/
        NwebView.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView view,int newProgress){
                binding.progressBar.setProgress(newProgress);
            }

        });
        NwebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                binding.progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                binding.progressBar.setVisibility (View.GONE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),R.string.error_web, Toast.LENGTH_SHORT).show();

                onBackPressed();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode==KeyEvent.KEYCODE_BACK && NwebView.canGoBack()){
        NwebView.goBack();
        return true;
    }
        return super.onKeyDown(keyCode, event);

    }
}