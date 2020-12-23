package com.siddheswar.newearthnews;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class WebNewsPortal extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_news_portal);
        webView = findViewById(R.id.WebNews);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(Boolean.parseBoolean("true"));
        Intent intent = getIntent();
        String url = intent.getStringExtra("urlvalue"); // recives the url value fr web viewing it.
         webView.loadUrl(url);// loads the url


    }
}