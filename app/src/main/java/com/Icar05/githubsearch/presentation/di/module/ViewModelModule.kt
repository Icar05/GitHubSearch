package com.Icar05.githubsearch.presentation.di.module

import androidx.lifecycle.ViewModel
import com.Icar05.githubsearch.presentation.di.scope.ViewModelKey
import com.Icar05.githubsearch.presentation.viewmodel.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearch(viewModel: SearchViewModel): ViewModel
    
}