package com.naddic.works;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView; // 웹뷰 선언
    private WebSettings mWebSettings; //웹뷰세팅

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 웹뷰 시작
        mWebView = (WebView) findViewById(R.id.webView);

        String url = "https://auth.worksmobile.com/login/login?accessUrl=http%3A%2F%2Fnaddic.ncpworkplace.com%2Fv%2Fhome%2F";

        mWebView.setWebViewClient(new WebViewClient()); // 클릭시 새창 안뜨게

        // 예
//        mWebView.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                view.loadUrl("javascript: alert('Loading completed.');");
//
//            }
//        });

        mWebSettings = mWebView.getSettings();                          // 세부 세팅 등록
        mWebSettings.setJavaScriptEnabled(true);                        // 웹페이지 자바스크립트 허용 여부
        mWebSettings.setSupportMultipleWindows(false);                  // 새창 띄우기 허용 여부
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(false);   // 자바스크립트 새창 띄우기(멀티뷰) 허용 여부
        mWebSettings.setSupportZoom(false);                             // 화면 줌 허용 여부
        mWebSettings.setBuiltInZoomControls(false);                     // 화면 확대 축소 허용 여부
        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); // 컨텐츠 사이즈 맞추기
        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);           // 브라우저 캐시 허용 여부
        mWebSettings.setDomStorageEnabled(true);                        // 로컬저장소 허용 여부
        mWebSettings.setLoadWithOverviewMode(true);                     // 메타태그 허용 여부
        mWebSettings.setUseWideViewPort(true);                          // 화면 사이즈 맞추기 허용 여부

        // 웹뷰에 크롬 사용 허용, 이 부분이 없으면 크롬에서 alert 뜨지 않음
        mWebView.setWebChromeClient(new WebChromeClient());


        // 웹뷰에 표시할 웹사이트 주소, 웹뷰 시작
        //mWebView.loadUrl("https://auth.worksmobile.com/login/login?accessUrl=http%3A%2F%2Fnaddic.ncpworkplace.com%2Fv%2Fhome%2F");
        mWebView.loadUrl(url);

        // desktopMode
        String newUserAgent = mWebView.getSettings().getUserAgentString();

        try {
            String ua = mWebView.getSettings().getUserAgentString();
            String androidOSString = mWebView.getSettings().getUserAgentString().substring(ua.indexOf("("), ua.indexOf(")") + 1);
            newUserAgent = mWebView.getSettings().getUserAgentString().replace(androidOSString, "(X11; Linux x86_64)");
            mWebView.getSettings().setUserAgentString(newUserAgent);
            mWebView.reload();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        //
//        @Override
//        public boolean onKeyDown(int keyCode, KeyEvent event) { // 뒤로가기 버튼 이벤트
//            if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) { // 웹뷰에서 뒤로가기 버튼을 누르면 뒤로 이동
//                webView.goBack();
//                return true;
//            }
//            return super.onKeyDown(keyCode, event);
//        }

//        private class WebViewClientClass extends WebViewClient { // 페이지 이동시 새창으로 안뜨게
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//
//                view.loadUrl("javascript:window.Android.getHtml(document.getElementsByTagName('html')[0].innerHTML);");
//            }
//        }
//
//        public class WebViewJavascriptInterface {
//            @JavascriptInterface
//            public void getHtml(String html) {
//                Log.d(TAG, html);
//            }
//        }
    }

// 페이지 이동시 새창으로 안뜨게
    private class WebViewClientClass extends WebViewClient {
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            view.loadUrl(url);
//            return true;
//        }

//        @Override
//        public void onPageFinished(WebView view, String url) {
//            super.onPageFinished(view, url);
//
//            //view.loadUrl("javascript:window.Android.getHtml(document.getElementsByTagName('html')[0].innerHTML);");
//        }
    }

}