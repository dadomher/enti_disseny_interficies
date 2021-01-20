package com.bearslovebeer.tifitiappp

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class WebViewActivity : AppCompatActivity() {

    private var webView: WebView? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val webPage = intent.getStringExtra("WEB_PAGE")

        setContentView(R.layout.activity_webview)

        webView = findViewById<View>(R.id.webView) as WebView
        webView!!.settings.javaScriptEnabled = true
        webView!!.loadUrl(webPage.toString())
    }
}