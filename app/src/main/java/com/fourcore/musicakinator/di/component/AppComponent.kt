package com.fourcore.musicakinator.di.component

import android.app.Application
import com.fourcore.musicakinator.MusicAkinatorApp
import com.fourcore.musicakinator.di.builders.ActivityBuilder
import com.fourcore.musicakinator.di.module.ApiModule
import com.fourcore.musicakinator.di.module.app.AppBindModule
import com.fourcore.musicakinator.di.module.app.AppProvideModule
import com.fourcore.musicakinator.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppProvideModule::class,
    AppBindModule::class,
    NetworkModule::class,
    ApiModule::class,
    ActivityBuilder::class
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