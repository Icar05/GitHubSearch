package com.Icar05.githubsearch.app

import com.Icar05.githubsearch.app.scopes.PerActivity
import com.Icar05.githubsearch.presentation.search.SearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector
    abstract fun searchActivity(): SearchActivity
}