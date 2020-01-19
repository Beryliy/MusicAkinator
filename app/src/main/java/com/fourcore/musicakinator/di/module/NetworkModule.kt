package com.fourcore.musicakinator.di.module

import com.fourcore.musicakinator.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {
    @Provides
    @Singleton
    @JvmStatic
    fun provideOkhttp() = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    @JvmStatic
    fun provideAuddIoRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.AUDDIO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}