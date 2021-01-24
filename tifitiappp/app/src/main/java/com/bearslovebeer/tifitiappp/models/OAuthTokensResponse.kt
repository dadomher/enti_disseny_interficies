package com.bearslovebeer.tifitiappp.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OAuthTokensResponse (
    @SerialName("access_token") val accessToken: String,
    @SerialName("refresh_token")val refreshToken: String? = null,
    @SerialName("expires_in")val expiresIn: Int? = null,
    val scopes: List<String>? = null,
    @SerialName("token_type")val tokenType: String? = null
)