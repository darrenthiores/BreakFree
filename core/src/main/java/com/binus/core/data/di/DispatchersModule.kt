package com.binus.core.data.di

import com.binus.core.domain.dispatchers.DispatchersProvider
import com.binus.core.utils.StandardDispatchers
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DispatchersModule {

    @Binds
    abstract fun provideDispatchers(
        dispatchers: StandardDispatchers
    ): DispatchersProvider
    
}