package com.binus.core.domain.preferences

import kotlinx.coroutines.flow.Flow
import com.binus.core.BuildConfig

interface Preferences {
    suspend fun saveLogin(isLogin: Boolean)

    suspend fun loadIsLogin(): Boolean

    fun loadIsLoginAsFlow(): Flow<Boolean>

    companion object {
        const val DATA_STORE_NAME = BuildConfig.DATA_STORE_NAME
        const val IS_LOGIN = BuildConfig.IS_LOGIN_KEY
    }
}