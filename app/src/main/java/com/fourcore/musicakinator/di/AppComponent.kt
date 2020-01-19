package com.fourcore.musicakinator.di

import android.app.Application
import com.fourcore.musicakinator.MusicAkinatorApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component
interface AppComponent: AndroidInjector<DaggerApplication> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(application: MusicAkinatorApp)
}