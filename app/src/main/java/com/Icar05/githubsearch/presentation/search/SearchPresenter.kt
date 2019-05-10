package com.Icar05.githubsearch.presentation.search

import com.Icar05.githubsearch.app.scopes.PerActivity
import com.Icar05.githubsearch.domain.interactor.GetReposInteractor
import com.Icar05.githubsearch.domain.interactor.SearchReposInteractor
import com.Icar05.githubsearch.domain.models.Item
import com.Icar05.githubsearch.presentation.base.BasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

@PerActivity
open class SearchPresenter @Inject constructor(
    private val searchReposInteractor: SearchReposInteractor,
    private val getReposInteractor: GetReposInteractor
) : BasePresenter<SearchView>() {



    fun parallelSearch(name: String){

            addDisposable(
                        Observable.mergeDelayError(
                                getReposInteractor.getRepos(name).subscribeOn(Schedulers.io()),
                                searchReposInteractor.searchRepos(name).subscribeOn(Schedulers.io())
                        )
                        .observeOn(AndroidSchedulers.mainThread(), true)
                        .doOnSubscribe{view!!.showProgress()}
                        .doOnNext { view!!.hideProgress() }
                        .doFinally { view?.hideProgress()}
                        .subscribe({ result ->
                            handleResult(result)

                            if(!result.isEmpty())
                                cancelDisposible()

                        }, { error ->
                            handleError(error)
                        })

            )
    }


    fun cancelSearchRequest(){
        view!!.onSearchCanceled()
        cancelDisposible()
    }



     private fun handleError(error: Throwable?) {
        when (error) {
            is retrofit2.HttpException -> view!!.showRepos(emptyList())
            is SocketTimeoutException -> view!!.onInternetDisabled()
            is IOException -> view!!.onInternetDisabled()
        }
    }

     private fun handleResult(repos: List<Item>) {
         view!!.showRepos(repos)
    }


}