package com.bearslovebeer.tifitiappp.activity

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.bearslovebeer.tifitiappp.Constants
import com.bearslovebeer.tifitiappp.R

class WebViewActivity : AppCompatActivity() {

    private var webView: WebView? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        webView = findViewById<View>(R.id.webView) as WebView
        webView!!.settings.javaScriptEnabled = true

        val fragment = intent.getStringExtra("FRAGMENT")

        if(fragment.equals("TWITCH")) {
            webView!!.loadUrl(loadOAuthUrl())
        } else if(fragment.equals("NEWS")) {
            val webPage = intent.getStringExtra("WEB_PAGE")
            webView!!.loadUrl(webPage.toString())
       }


    }

    private fun loadOAuthUrl(): String {

            val uri = Uri.parse("https://id.twitch.tv/oauth2/authorize")
                .buildUpon()
                .appendQueryParameter("client_id", Constants.OAUTH_CLIENT_ID)
                .appendQueryParameter("redirect_uri", Constants.OAUTH_REDIRECT_URI)
                .appendQueryParameter("response_type", "code")
                .appendQueryParameter("scope", listOf("user:edit", "user:read:email").joinToString { " " })

        return uri.toString()
    }
}