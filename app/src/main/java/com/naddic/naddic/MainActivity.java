package com.naddic.naddic;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.naddic.naddic.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView; // 웹뷰 선언
    private WebSettings mWebSettings; //웹뷰세팅

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        // 웹뷰 시작
        mWebView = (WebView) findViewById(R.id.webView);

        // button
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "버튼-활성화", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.fab)
                        .setAction("Action", null).show();

                mWebView.loadUrl("javascript:$('.btn').attr('disabled', false);");
                mWebView.loadUrl("javascript:$('.content-header').html($('#workplaceLayer')[0]);");
                //mWebView.loadUrl("javascript:$('.commute-button-box.row').children(1).click();");
                // bootstrap 5.0
//                mWebView.loadUrl("javascript:document.getElementsByTagName('head')[0].insertAdjacentHTML('beforeend','<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">'");
//                mWebView.loadUrl("javascript:document.getElementsByTagName('head')[0].insertAdjacentHTML('beforeend','<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM\" crossorigin=\"anonymous\"></script>'");

            }
        });

        String url = "https://auth.worksmobile.com/login/login?accessUrl=http%3A%2F%2Fnaddic.ncpworkplace.com%2Fv%2Fhome%2F";

        mWebView.setWebViewClient(new WebViewClient()); // 클릭시 새창 안뜨게
        mWebSettings = mWebView.getSettings();                          // 세부 세팅 등록

        mWebSettings.setJavaScriptEnabled(true);                        // 웹페이지 자바스크립트 허용 여부
        mWebSettings.setSupportMultipleWindows(false);                  // 새창 띄우기 허용 여부
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(false);   // 자바스크립트 새창 띄우기(멀티뷰) 허용 여부
        mWebSettings.setSupportZoom(true);                              // 화면 줌 허용 여부
        mWebSettings.setBuiltInZoomControls(true);                      // 줌컨트롤
        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); // 컨텐츠 사이즈 맞추기
        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);           // 브라우저 캐시 허용 여부
        mWebSettings.setDomStorageEnabled(true);                        // 로컬저장소 허용 여부
        mWebSettings.setLoadWithOverviewMode(true);                     // 메타태그 허용 여부
        mWebSettings.setUseWideViewPort(true);

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}