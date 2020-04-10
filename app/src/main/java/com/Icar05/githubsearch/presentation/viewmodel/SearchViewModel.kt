package com.Icar05.githubsearch.presentation.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.Icar05.githubsearch.domain.interactor.GetReposInteractor
import com.Icar05.githubsearch.domain.interactor.SearchReposInteractor
import com.Icar05.githubsearch.domain.interactor.StoreReposInteractor
import com.Icar05.githubsearch.domain.model.SearchItem
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SearchViewModel  constructor(
    private val getReposInteractor: GetReposInteractor,
    private val searchReposInteractor: SearchReposInteractor,
    private val storeReposInteractor: StoreReposInteractor
) : ViewModel() {
    
    private val disposables = CompositeDisposable()
    val showProgressObservable = MutableLiveData<Boolean>()
    val resultRepos = MutableLiveData<List<SearchItem>>()
    val resultError = MutableLiveData<Throwable>()
    
    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
    
    fun parallelSearch(name: String){
        
        if(this.showProgressObservable.value == true) {
            return
        }
        
        disposables.add(
            Observable.mergeDelayError(
                getReposInteractor.execute(name = name).subscribeOn(Schedulers.io()),
                searchReposInteractor.execute(name = name)
                    .flatMap { storeReposInteractor.execute(repos = it) }
                    .subscribeOn(Schedulers.io())
            )
                .delay(6000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread(), true)
                .doOnSubscribe{showProgressObservable.value = true}
                .subscribe(
                    { result -> finishSuccessfull(result = result)},
                    { error -> finishWithError(error = error)}
                )
        )
    }
    
    private fun finishSuccessfull(result: List<SearchItem>){
        this.resultRepos.value = result
        this.showProgressObservable.value = false
    }
    
    private fun finishWithError(error: Throwable){
        this.resultError.value = error
        this.showProgressObservable.value = false
    }
}