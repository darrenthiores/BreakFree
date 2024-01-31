package com.binus.core.data.di

import com.binus.core.data.local.preferences.DefaultPreferences
import com.binus.core.data.local.preferences.EncTokenPreferences
import com.binus.core.domain.preferences.Preferences
import com.binus.core.domain.preferences.TokenPreferences
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferenceModule {

    @Binds
    abstract fun providePreferences(
        preferences: DefaultPreferences
    ): Preferences

    @Binds
    abstract fun provideTokenPreferences(
        preferences: EncTokenPreferences
    ): TokenPreferences
    
}