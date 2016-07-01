package com.chinaso.test;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
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

    EditText input;
    Button home;

    boolean isScrollAll =true;
    private String URL1="http://www.baidu.com";//"http://m.chinaso365.com/plus/plus_hotsearch.html"
    private String URL2="http://m.chinaso.com/";//"http://m.chinaso365.com/plus/plus_list.html"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {

        Button height= (Button) findViewById(R.id.height);
        height.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("height","web2 content height->"+web2.getContentHeight()
                        +" web2 height->"+web2.getHeight()
                        +" pullToRefreshWebView->"+pullToRefreshWebView.getHeight());
            }
        });
        input= (EditText) findViewById(R.id.input);
        home= (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                home.setVisibility(View.GONE);

                ((MyPullToRefreshScrollView)mPullRefreshScrollView).setDisable(false);
                mPullRefreshScrollView.setDisableInternalScrollView(false);

                mScrollView.scrollTo(0,0);
                web2.scrollTo(0,0);
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
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) (input.getLayoutParams());
                int  y= 420-newScrollValue;
                Log.i("ly","newScrollValue->"+newScrollValue);
                if (y  < 9)
                    y = 9;
                lp.setMargins(lp.leftMargin, y, lp.rightMargin, 0);
                input.setLayoutParams(lp);
            }
        });

        mScrollView = mPullRefreshScrollView.getRefreshableView();
        ((PullToRefreshScrollView.InternalScrollViewSDK9)mScrollView).setScrollListener(new ScrollListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {

                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) (input.getLayoutParams());
                int  y= 420-t;
                Log.i("ly","oldt->"+oldt+" t->"+t+" lp.topMargin->"+lp.topMargin);
                if (y  < 9)
                    y = 9;
                lp.setMargins(lp.leftMargin, y, lp.rightMargin, 0);
                input.setLayoutParams(lp);

                int web1Height=web1.getHeight();
                int headerHeight=200*3;
                int d=web1Height+headerHeight-(9+60*3+50*3);
                if(t>=d){

                    home.setVisibility(View.VISIBLE);

                    ((MyPullToRefreshScrollView)mPullRefreshScrollView).setDisable(true);
                    mPullRefreshScrollView.setDisableInternalScrollView(true);

                    mScrollView.scrollTo(0,d);

                }
            }
        });


        web1= (MyWebView) findViewById(R.id.web1);
        WebViewUtil.init(web1,MainActivity.this);
        web1.loadUrl(URL1);

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
        web2.loadUrl(URL2);
    }
    @JavascriptInterface
    public void resize(final float height) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int h=getResources().getDisplayMetrics().heightPixels-getStatusBarHeight();
                Toast.makeText(MainActivity.this, "web2 page height:"+height+" screen height:"+h, Toast.LENGTH_LONG).show();

                setPullToRefreshWebViewHeight(h-(3+60+50)*3);
            }
        });
    }
    private void setPullToRefreshWebViewHeight(int h){
        RelativeLayout.LayoutParams lp1=new RelativeLayout.LayoutParams(
                getResources().getDisplayMetrics().widthPixels,
                h);
        lp1.addRule(RelativeLayout.BELOW,R.id.web1);
        pullToRefreshWebView.setLayoutParams(lp1);
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
