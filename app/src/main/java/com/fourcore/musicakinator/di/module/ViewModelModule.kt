package com.fourcore.musicakinator.di.module

import androidx.lifecycle.ViewModelProvider
import com.fourcore.musicakinator.di.factory.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}