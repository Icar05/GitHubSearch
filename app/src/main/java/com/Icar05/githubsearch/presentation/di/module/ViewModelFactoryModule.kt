package com.Icar05.githubsearch.presentation.di.module

import androidx.lifecycle.ViewModelProvider
import com.Icar05.githubsearch.presentation.di.factory.DaggerViewModelFactory
import dagger.Binds
import dagger.Module


@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory
}