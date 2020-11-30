package com.wb.wavebrowser;

import android.os.Bundle;
import com.wb.wavebrowser.webWave.*;
import android.webkit.WebHistoryItem;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;


public class download extends AppCompatActivity {
   WebHistoryItem webHistoryItem;

    WebView webView = (WebView)findViewById(R.id.waveWebView);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

    }
}