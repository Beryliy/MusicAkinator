package com.fourcore.musicakinator.di.module

import com.fourcore.musicakinator.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
    fun provideRxJava2CallAdapterFactory() = RxJava2CallAdapterFactory.create()

    @Provides
    @Singleton
    @JvmStatic
    fun provideAuddIoRetrofit(
        okHttpClient: OkHttpClient,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.AUDDIO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .client(okHttpClient)
            .build()
    }
}