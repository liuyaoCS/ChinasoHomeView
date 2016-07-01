package com.chinaso.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Administrator on 2016/6/29 0029.
 */
public class WebViewUtil {
    private static Context mContext;
    @SuppressLint("JavascriptInterface")
    public static void init(final WebView web, Context context){
        mContext=context;
        WebViewClient wvc = new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(final WebView view, String url) {
                // TODO Auto-generated method stub
                if(url.equals("http://m.chinaso.com/")){//http://m.chinaso365.com/plus/plus_list.html
                    web.loadUrl("javascript:App.resize(document.body.scrollHeight)");//getBoundingClientRect().height)
                }

                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(final WebView view, String url, Bitmap favicon) {
                // TODO Auto-generated method stub
                super.onPageStarted(view, url, favicon);
            }


        };
        web.setWebViewClient(wvc);

        web.addJavascriptInterface(mContext, "App");

        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setDomStorageEnabled(true);
        web.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

//        web.setVerticalScrollBarEnabled(false);
//        web.setVerticalScrollbarOverlay(false);
    }

}
