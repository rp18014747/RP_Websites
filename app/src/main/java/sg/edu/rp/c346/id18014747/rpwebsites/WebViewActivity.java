package sg.edu.rp.c346.id18014747.rpwebsites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {

    WebView wvMyPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        wvMyPage = findViewById(R.id.WebViewRP);
        WebSettings wvSettings = wvMyPage.getSettings();
        wvSettings.setJavaScriptEnabled(true);
        wvSettings.setAllowFileAccess(false);
        wvSettings.setBuiltInZoomControls(true);

        Intent intentReceived = getIntent();
        String url = intentReceived.getStringExtra("url");
        wvMyPage.setWebViewClient(new WebViewClient()); // new browser fix
        wvMyPage.loadUrl(url);

    }
}
