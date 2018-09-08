package jp.isobe.tomohiro.websitenavigation;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    private ProgressBar mProgressBar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mWebView.loadUrl("https://www.google.co.jp/");

                    return true;
                case R.id.navigation_dashboard:
                    mWebView.loadUrl("http://splax.net/");

                    return true;
                case R.id.navigation_notifications:
                    mWebView.loadUrl("http://flowersburger.com/");

                    return true;

                case R.id.navigation_back:
                    // 戻るボタンが押された時
                    mWebView.goBack();
                    return true;

                case R.id.navigation_next:
                    // 進むボタンが押された時の処理
                    mWebView.goForward();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mWebView = (WebView) findViewById(R.id.webView);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        // 最初は非表示
        mProgressBar.setVisibility(View.INVISIBLE);
        // タップした時にブラウザを起動しない
        mWebView.setWebViewClient(new WebViewClient() {

            // webページの読み込み開始
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mProgressBar.setVisibility(View.VISIBLE);
            }

            // webページの読み込み終了
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });

        // JavaScript有効
        mWebView.getSettings().setJavaScriptEnabled(true);
    }

}
