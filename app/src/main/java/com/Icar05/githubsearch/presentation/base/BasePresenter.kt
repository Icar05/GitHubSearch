package com.Icar05.githubsearch.presentation.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable



open class BasePresenter<T : BaseView> {

    protected var view: T? = null
    private var isFirstAttach = true
    private val compositeDisposable = CompositeDisposable()


    fun attach(view: T) {
        this.view = view
        if (isFirstAttach) {
            onFirstAttach()
            isFirstAttach = false
        }
    }

    protected open fun onFirstAttach() {

    }

    fun detach() {
        onDetach()
        view = null
        compositeDisposable.clear()
    }


    protected open fun cancelDisposible(){
        compositeDisposable.clear()
    }

    protected open fun onDetach() {

    }

    protected open fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}