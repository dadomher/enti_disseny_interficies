package com.bearslovebeer.tifitiappp.activity

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bearslovebeer.tifitiappp.Constants
import com.bearslovebeer.tifitiappp.R
import com.bearslovebeer.tifitiappp.models.OAuthTokensResponse
import com.bearslovebeer.tifitiappp.services.NetworkManager
import com.bearslovebeer.tifitiappp.services.UserManager
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class WebViewActivity : AppCompatActivity() {

    private var webView: WebView? = null
    private val TAG: String = "WebViewActivity"

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        webView = findViewById<View>(R.id.webView) as WebView
        webView!!.settings.javaScriptEnabled = true

        val fragment = intent.getStringExtra("FRAGMENT")

        if (fragment.equals("TWITCH")) {
            loadOAuthUrl()
        } else if (fragment.equals("NEWS")) {
            val webPage = intent.getStringExtra("WEB_PAGE")
            webView!!.loadUrl(webPage.toString())
        }


    }

    private fun loadOAuthUrl() {

        val uri = Uri.parse("https://id.twitch.tv/oauth2/authorize")
            .buildUpon()
            .appendQueryParameter("client_id", Constants.OAUTH_CLIENT_ID)
            .appendQueryParameter("redirect_uri", Constants.OAUTH_REDIRECT_URI)
            .appendQueryParameter("response_type", "code")
            .appendQueryParameter("scope", listOf("user:edit", "user:read:email").joinToString(" "))

        webView!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                if (request?.url?.toString()?.startsWith(Constants.OAUTH_REDIRECT_URI) == true) {
                    // Login success!
                    Log.i(TAG, "Login succsess with URL: ${request?.url}")
                    request.url.getQueryParameter("code").let {
                        Log.i(TAG, "Got authentication CODE$it")

                        webView!!.visibility = View.GONE
                        //Exchange copde -> acces token
                        getAccessTokens(it.toString())
                    } ?: run {
                        // TODO: Handle error
                    }
                }
                return super.shouldOverrideUrlLoading(view, request)
            }
        }

        Log.i(TAG, "Starting login with URL: ${uri.toString()}")

        webView!!.loadUrl(uri.toString())
    }

    private fun getAccessTokens(authorizationCode: String) {
        val httpClient = NetworkManager.createHttpClient()

        // Assign to Activity Scope
        lifecycleScope.launch {

            //Change to Background Thread
            withContext(Dispatchers.IO) {
                try {
                    val response: OAuthTokensResponse =
                        httpClient.post<OAuthTokensResponse>("https://id.twitch.tv/oauth2/token") {
                            parameter("client_id", Constants.OAUTH_CLIENT_ID)
                            parameter("client_secret", Constants.OAUTH_CLIENT_SECRET)
                            parameter("code", authorizationCode)
                            parameter("grant_type", "authorization_code")
                            parameter("redirect_uri", Constants.OAUTH_REDIRECT_URI)
                        }
                    Log.i(TAG, "Got response from Twitch: $response")

                    // Save Access token
                    UserManager(this@WebViewActivity).saveAccessToken(response.accessToken)

                    //Close
                    finish()
                } catch (t: Throwable) {
                    // TODO: Handle error

                }
            }
        }
    }
}