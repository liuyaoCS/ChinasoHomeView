package com.chinaso.test;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.handmark.pulltorefresh.library.PullToRefreshWebView;

public class MainActivity2 extends AppCompatActivity {

    WebView web1,web2,web3;
    RelativeLayout.LayoutParams lp;
    Button change;
    boolean isScollAll=true;
    ViewGroup container;
    PullToRefreshWebView pullToRefreshWebView;

    PullToRefreshScrollView mPullRefreshScrollView;
    ScrollView mScrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        lp=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.BELOW,R.id.web1);


        initView();

    }
    @JavascriptInterface
    public void resize(final float height) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity2.this, height + "", Toast.LENGTH_LONG).show();

                RelativeLayout.LayoutParams lp1=new RelativeLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels,
                        (int) (height * getResources().getDisplayMetrics().density));
                lp1.addRule(RelativeLayout.BELOW,R.id.web1);
                pullToRefreshWebView.setLayoutParams(lp1);
//                web2.setLayoutParams(
//                        new FrameLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels,
//                                (int) (height * getResources().getDisplayMetrics().density)));

                Log.i("ly","pull height->"+pullToRefreshWebView.getHeight()+" web height->"+web2.getHeight());
            }
        });
    }
    private void initView() {

        mPullRefreshScrollView = (PullToRefreshScrollView) findViewById(R.id.pull_refresh_scrollview);
        mPullRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {

            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                mPullRefreshScrollView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullRefreshScrollView.onRefreshComplete();
                    }
                },500);
            }
        });

        mScrollView = mPullRefreshScrollView.getRefreshableView();


        web1= (MyWebView) findViewById(R.id.web1);
        WebViewUtil.init(web1,MainActivity2.this);
        web1.loadUrl("http://m.chinaso365.com/plus/plus_hotsearch.html");//hotsearch


        pullToRefreshWebView= (PullToRefreshWebView) findViewById(R.id.pull_refresh_webview);
        pullToRefreshWebView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<WebView>() {
            @Override
            public void onPullDownToRefresh(final PullToRefreshBase<WebView> refreshView) {
                refreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity2.this,"pull down",Toast.LENGTH_SHORT).show();
                        refreshView.onRefreshComplete();
                    }
                },500);
            }

            @Override
            public void onPullUpToRefresh(final PullToRefreshBase<WebView> refreshView) {
                refreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity2.this,"pull up",Toast.LENGTH_SHORT).show();
                        refreshView.onRefreshComplete();
                    }
                },500);
            }
        });

        web2=pullToRefreshWebView.getRefreshableView();
       // web2=(MyWebView) findViewById(R.id.web2);
        WebViewUtil.init(web2,MainActivity2.this);
        web2.loadUrl("http://m.chinaso365.com/plus/plus_list.html");
        //web2.loadUrl("http://192.168.66.71/server/chinaso.html");
//        int w = View.MeasureSpec.makeMeasureSpec(0,
//                View.MeasureSpec.UNSPECIFIED);
//        int h = View.MeasureSpec.makeMeasureSpec(0,
//                View.MeasureSpec.UNSPECIFIED);
//        //重新测量
//        web2.measure(w, h);
        web3=(MyWebView) findViewById(R.id.web3);
        WebViewUtil.init(web3,MainActivity2.this);
        web2.loadUrl("http://m.chinaso365.com/plus/plus_list.html");
        //web3.loadUrl("http://192.168.66.71/server/chinaso.html");

        container= (ViewGroup) findViewById(R.id.container);
        change= (Button) findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isScollAll){
                    isScollAll=false;
                    web1.setVisibility(View.GONE);
                    mPullRefreshScrollView.setMode(PullToRefreshBase.Mode.DISABLED);
                    web3.setVisibility(View.GONE);

                   // web2.setVisibility(View.VISIBLE);

                }else{
                    isScollAll=true;
                    web3.setVisibility(View.VISIBLE);
                    web1.setVisibility(View.VISIBLE);
                   // web2.setVisibility(View.GONE);
                    mPullRefreshScrollView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
                }
                TestApp.mIsScrollAll=isScollAll;
            }
        });
    }

}
