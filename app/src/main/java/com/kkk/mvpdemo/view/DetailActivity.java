package com.kkk.mvpdemo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.kkk.mvpdemo.R;
import com.kkk.mvpdemo.model.Article;

public class DetailActivity extends AppCompatActivity {

    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mWebView = (WebView) findViewById(R.id.webview);
        Article article = getIntent().getParcelableExtra("article");

        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWebView.loadUrl(article.link);
    }
}
