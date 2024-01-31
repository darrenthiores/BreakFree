package com.binus.core.domain.preferences

interface TokenPreferences {
    fun getAccessToken(): String

    fun getRefreshToken(): String

    fun setToken(
        accessToken: String,
        refreshToken: String
    )

    fun resetToken()
}