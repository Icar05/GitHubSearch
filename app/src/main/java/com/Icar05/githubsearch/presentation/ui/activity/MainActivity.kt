package com.Icar05.githubsearch.presentation.ui.activity

import com.Icar05.githubsearch.R
import dagger.android.AndroidInjection


class MainActivity : BaseActivity() {
    
    override fun getLayoutId() = R.layout.activity_main
    
    override fun onChildCreate() {
        AndroidInjection.inject(this)
    }
}

