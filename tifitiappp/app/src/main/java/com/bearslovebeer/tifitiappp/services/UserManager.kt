package com.bearslovebeer.tifitiappp.services

import android.content.Context

class UserManager(context: Context) {
    private val sharePreferencesFileName = "userInfo"
    private val sharedPreferences = context.getSharedPreferences(sharePreferencesFileName, Context.MODE_PRIVATE)

    private val accessTokenKey = "accessToken"

    fun getAccesToken():String? {
        return sharedPreferences.getString(accessTokenKey, null)
    }

    fun saveAccessToken(token: String) {
        sharedPreferences.edit().putString(accessTokenKey, token).apply()
    }
}