package com.binus.core.data.di

import com.binus.core.domain.preferences.Preferences
import com.binus.core.domain.preferences.TokenPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreNetworkModule {

    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun provideKtorHttpClient(
        preferences: Preferences,
        tokenPreferences: TokenPreferences
    ): HttpClient {
        val json = kotlinx.serialization.json.Json {
            explicitNulls = false
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        }

        return HttpClient(Android) {
            install(ContentNegotiation) {
                json(
                    json = json
                )
            }

            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.ALL
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 30000L
                connectTimeoutMillis = 3000L
                socketTimeoutMillis = 15000L
            }

            install(Auth) {
                bearer {
                    loadTokens {
                        BearerTokens(
                            accessToken = tokenPreferences.getAccessToken(),
                            refreshToken = tokenPreferences.getRefreshToken()
                        )
                    }
                    refreshTokens {
                        return@refreshTokens BearerTokens(
                            "",
                            ""
                        )
                    }
                    sendWithoutRequest {
                        !it.url.encodedPath.startsWith("")
                    }
                }
            }
        }
    }
}