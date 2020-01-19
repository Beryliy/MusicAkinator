package com.fourcore.musicakinator.di.component

import android.app.Application
import com.fourcore.musicakinator.MusicAkinatorApp
import com.fourcore.musicakinator.di.module.AppModule
import com.fourcore.musicakinator.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class,
    AppModule::class,
    NetworkModule::class
])
interface AppComponent: AndroidInjector<DaggerApplication> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(application: MusicAkinatorApp)
}