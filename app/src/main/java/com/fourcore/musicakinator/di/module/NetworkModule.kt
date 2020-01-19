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
    fun provideOkhttp(): OkHttpClient = OkHttpClient.Builder().build()

}