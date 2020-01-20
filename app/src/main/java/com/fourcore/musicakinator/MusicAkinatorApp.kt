package com.fourcore.musicakinator

import com.fourcore.musicakinator.di.component.DaggerAppComponent

class MusicAkinatorApp: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}