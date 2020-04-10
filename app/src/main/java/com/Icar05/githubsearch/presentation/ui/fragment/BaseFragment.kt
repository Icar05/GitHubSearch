package com.Icar05.githubsearch.presentation.ui.fragment

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.Icar05.githubsearch.presentation.di.factory.DaggerViewModelFactory
import javax.inject.Inject

abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {
    
    @Inject
    lateinit var factory: DaggerViewModelFactory
    
    abstract fun getTitleToolbarText(): Int
    
    protected open fun getTitleToolbarString() = ""
    
    @Suppress("UNCHECKED_CAST")
    fun getViewModel(modelClass: Class<out ViewModel>): ViewModel = ViewModelProvider(this, factory).get(modelClass)
    
}
