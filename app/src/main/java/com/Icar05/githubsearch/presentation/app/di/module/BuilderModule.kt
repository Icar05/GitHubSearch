package com.Icar05.githubsearch.presentation.app.di.module

import com.Icar05.githubsearch.presentation.app.di.scope.PerFragment
import com.Icar05.githubsearch.presentation.ui.activity.MainActivity
import com.Icar05.githubsearch.presentation.ui.fragment.SearchFragment
import com.icar05.androidbasearch.di.scopes.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class BuilderModule {
    
    @PerActivity
    @ContributesAndroidInjector
    internal abstract fun bindMainActivity(): MainActivity
    
    @PerFragment
    @ContributesAndroidInjector
    internal abstract fun bindSearchFragment(): SearchFragment
    
}