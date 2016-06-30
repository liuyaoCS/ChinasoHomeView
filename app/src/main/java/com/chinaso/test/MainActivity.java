package com.chinaso.test;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.handmark.pulltorefresh.library.PullToRefreshWebView;

public class MainActivity extends AppCompatActivity {

    MyWebView web1,web2;
    SwipeRefreshLayout swipeLayout;
    RelativeLayout.LayoutParams lp;
    Button change;
    boolean isScollAll=true;
    ViewGroup container;
    PullToRefreshWebView pullToRefreshWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lp=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.BELOW,R.id.web1);
        swipeLayout= new SwipeRefreshLayout(MainActivity.this);
        setSwipeRefreshLayout(swipeLayout);

        web1= (MyWebView) findViewById(R.id.web1);
        web1.loadUrl("http://www.baidu.com");
        web1.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        web1.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });


        web2= (MyWebView) findViewById(R.id.web2);
        web2.loadUrl("http://m.chinaso.com");
        web2.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });


        container= (ViewGroup) findViewById(R.id.container);
        change= (Button) findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainActivity2.class));
//                if(isScollAll){
//                    isScollAll=false;
//                    container.removeView(web2);
//                    swipeLayout.addView(web2);
//                    container.addView(swipeLayout,1,lp);
//
//                }else{
//                    isScollAll=true;
//                    container.removeView(swipeLayout);
//                    swipeLayout.removeView(web2);
//                    container.addView(web2,1,lp);
//                }
            }
        });

    }
    private void setSwipeRefreshLayout(final SwipeRefreshLayout swipeLayout){
        swipeLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeLayout.setRefreshing(false);
                    }
                },1000);
            }
        });
    }
}
