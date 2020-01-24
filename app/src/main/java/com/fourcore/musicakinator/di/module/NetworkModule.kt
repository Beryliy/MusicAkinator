package com.fourcore.musicakinator.di.module

import com.deezer.sdk.player.networkcheck.WifiAndMobileNetworkStateChecker
import com.fourcore.musicakinator.BuildConfig
import com.fourcore.musicakinator.R
import com.fourcore.musicakinator.global.proxy.ResourcesRepository
import com.fourcore.musicakinator.network.interceptor.HeaderInterceptor
import com.fourcore.musicakinator.network.service.FindLyricsService
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
    fun provideOkhttp(resourcesRepository: ResourcesRepository): OkHttpClient {
        val headerInterceptor = HeaderInterceptor(resourcesRepository.getString(R.string.auddIoAPIToken))
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .build()
    }

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

    @Provides
    @Singleton
    @JvmStatic
    fun provideFindLyricsService(retrofit: Retrofit) = retrofit.create(FindLyricsService::class.java)

    @Provides
    @Singleton
    @JvmStatic
    fun provideNetworkStateChecker() = WifiAndMobileNetworkStateChecker()
}