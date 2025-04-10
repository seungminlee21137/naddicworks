package com.naddic.works;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

        List<String> ramdomList = Arrays.asList(
                "/retry/7cif57j77dn172ulk56otfju36m592j0",
                "/retry/2e6r4bb84qnqi7fdmtv06da18k4n7ko0",
                "/retry/aqc0l47ig9v6atvhluqf03dftv9p05dl",
                "/retry/6pseu7tcuhh0q7es4e9tlpucpfivlsfu",
                "/retry/h0eupn0gcj64n2hisghc869tla8httic"
        );

        Random random = new Random();
        int randomIndex = random.nextInt(ramdomList.size());
        //System.out.println(ramdomList.get(randomIndex));

        mWebView.setWebViewClient(new WebViewClient());                 // 클릭시 새창 안뜨게
        mWebSettings = mWebView.getSettings();                          // 세부 세팅 등록
        mWebSettings.setJavaScriptEnabled(true);                        // 웹페이지 자바스크립트 허용 여부
        mWebSettings.setSupportMultipleWindows(false);                  // 새창 띄우기 허용 여부
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(false);   // 자바스크립트 새창 띄우기(멀티뷰) 허용 여부
        mWebSettings.setSupportZoom(false);                             // 화면 줌 허용 여부
        mWebSettings.setBuiltInZoomControls(false);                     // 화면 확대 축소 허용 여부
        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); // 컨텐츠 사이즈 맞추기
        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);           // 브라우저 캐시 허용 여부
        mWebSettings.setDomStorageEnabled(true);                        // 로컬저장소 허용 여부

        // desktopMode
        String newUserAgent = mWebView.getSettings().getUserAgentString();

        mWebSettings.setLoadWithOverviewMode(true);                     // 메타태그 허용 여부
        mWebSettings.setUseWideViewPort(true);                          // 화면 사이즈 맞추기 허용 여부

        // 웹뷰에 표시할 웹사이트 주소, 웹뷰 시작
        mWebView.loadUrl("https://auth.worksmobile.com/login/login?accessUrl=http%3A%2F%2Fnaddic.ncpworkplace.com%2Fv%2Fhome%2F");

        try {
            String ua = mWebView.getSettings().getUserAgentString();
            String androidOSString = mWebView.getSettings().getUserAgentString().substring(ua.indexOf("("), ua.indexOf(")") + 1);
            newUserAgent = mWebView.getSettings().getUserAgentString().replace(androidOSString, "(X11; Linux x86_64)");
            mWebView.getSettings().setUserAgentString(newUserAgent);
            mWebView.reload();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}