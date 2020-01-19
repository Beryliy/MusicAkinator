package com.fourcore.musicakinator

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MusicAkinatorApp: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}