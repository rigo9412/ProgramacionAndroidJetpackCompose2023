package com.lanazirot.curpavanzado

import com.lanazirot.curpavanzado.services.implementation.CURPGenerator
import com.lanazirot.curpavanzado.services.interfaces.ICURPGenerator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideCURPGenerator(): ICURPGenerator = CURPGenerator()
}