package com.fourcore.musicakinator.di.module.app

import androidx.lifecycle.ViewModelProvider
import com.fourcore.musicakinator.di.factory.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface AppBindModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}