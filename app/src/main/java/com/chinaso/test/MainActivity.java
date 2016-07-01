package com.chinaso.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.handmark.pulltorefresh.library.PullToRefreshWebView;
import com.handmark.pulltorefresh.library.ScrollListener;

public class MainActivity extends AppCompatActivity {

    PullToRefreshScrollView mPullRefreshScrollView;
    ScrollView mScrollView;

    PullToRefreshWebView pullToRefreshWebView;
    WebView web1,web2;

    Button change;
    boolean isScrollAll =true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {

        Button btn= (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("height","web2 content height->"+web2.getContentHeight()
                        +" web2 height->"+web2.getHeight()
                        +" pullToRefreshWebView->"+pullToRefreshWebView.getHeight());
            }
        });

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

        mPullRefreshScrollView.setPullListener(new PullListener() {
            @Override
            public void onPulledChanged(int newScrollValue) {
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) (change.getLayoutParams());
                int  y= 420-newScrollValue;
                Log.i("ly","newScrollValue->"+newScrollValue);
                if (y  < 10)
                    y = 10;
                lp.setMargins(lp.leftMargin, y, lp.rightMargin, 0);
                change.setLayoutParams(lp);
            }
        });

        mScrollView = mPullRefreshScrollView.getRefreshableView();
        ((PullToRefreshScrollView.InternalScrollViewSDK9)mScrollView).setScrollListener(new ScrollListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {

                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) (change.getLayoutParams());
                int  y= 420-t;
                Log.i("ly","oldt->"+oldt+" t->"+t+" lp.topMargin->"+lp.topMargin);
                if (y  < 10)
                    y = 10;
                lp.setMargins(lp.leftMargin, y, lp.rightMargin, 0);
                change.setLayoutParams(lp);
            }
        });



        web1= (MyWebView) findViewById(R.id.web1);
        WebViewUtil.init(web1,MainActivity.this);
        web1.loadUrl("http://m.chinaso365.com/plus/plus_hotsearch.html");//hotsearch

        pullToRefreshWebView= (PullToRefreshWebView) findViewById(R.id.pull_refresh_webview);
        pullToRefreshWebView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<WebView>() {
            @Override
            public void onPullDownToRefresh(final PullToRefreshBase<WebView> refreshView) {
                refreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,"pull down",Toast.LENGTH_SHORT).show();
                        refreshView.onRefreshComplete();
                    }
                },500);
            }

            @Override
            public void onPullUpToRefresh(final PullToRefreshBase<WebView> refreshView) {
                refreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,"pull up",Toast.LENGTH_SHORT).show();
                        refreshView.onRefreshComplete();
                    }
                },500);
            }
        });

        web2=pullToRefreshWebView.getRefreshableView();
        WebViewUtil.init(web2,MainActivity.this);
        web2.loadUrl("http://m.chinaso365.com/plus/plus_list.html");

        change= (Button) findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isScrollAll){
                    isScrollAll =false;

                    ((MyPullToRefreshScrollView)mPullRefreshScrollView).setDisable(true);
                    mPullRefreshScrollView.setDisableInternalScrollView(true);

                    setPullToRefreshWebViewHeight(1920);

                }else{
                    isScrollAll =true;

                    ((MyPullToRefreshScrollView)mPullRefreshScrollView).setDisable(false);
                    mPullRefreshScrollView.setDisableInternalScrollView(false);

                    mScrollView.scrollTo(0,0);
                    web2.scrollTo(0,0);
                }
            }
        });
    }
    @JavascriptInterface
    public void resize(final float height) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "web2 page height:"+height, Toast.LENGTH_LONG).show();
                setPullToRefreshWebViewHeight(1920);
            }
        });
    }
    private void setPullToRefreshWebViewHeight(int h){
        RelativeLayout.LayoutParams lp1=new RelativeLayout.LayoutParams(
                getResources().getDisplayMetrics().widthPixels,
                h);//height* getResources().getDisplayMetrics().density
        lp1.addRule(RelativeLayout.BELOW,R.id.web1);
        pullToRefreshWebView.setLayoutParams(lp1);
    }
}
