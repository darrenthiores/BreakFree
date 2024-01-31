package com.binus.core.data.local.preferences

import android.content.SharedPreferences
import com.binus.core.domain.preferences.TokenPreferences
import com.binus.core.BuildConfig
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class EncTokenPreferences @Inject constructor(
    @Named("encryptedPreferences") private val preferences: SharedPreferences
): TokenPreferences {
    override fun getAccessToken(): String = preferences.getString(BuildConfig.USER_TOKEN_KEY, "") ?: ""

    override fun getRefreshToken(): String = preferences.getString(BuildConfig.USER_REFRESH_TOKEN_KEY, "") ?: ""

    override fun setToken(
        accessToken: String,
        refreshToken: String
    ) {
        preferences.edit()
            .putString(BuildConfig.USER_TOKEN_KEY, accessToken)
            .putString(BuildConfig.USER_REFRESH_TOKEN_KEY, refreshToken)
            .apply()
    }

    override fun resetToken() {
        preferences.edit()
            .putString(BuildConfig.USER_TOKEN_KEY, "")
            .putString(BuildConfig.USER_REFRESH_TOKEN_KEY, "")
            .apply()
    }
}