package com.icar05.githubsearch.presentation.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.icar05.githubsearch.data.model.Result
import com.icar05.githubsearch.domain.interactor.GetReposInteractor
import com.icar05.githubsearch.domain.interactor.SearchReposInteractor
import com.icar05.githubsearch.domain.interactor.StoreReposInteractor
import com.icar05.githubsearch.domain.model.SearchItem
import com.icar05.githubsearch.domain.model.SearchResponse
import kotlinx.coroutines.*


/**
 * useful reading: https://devcolibri.com/kotlin-coroutines-patterns-anti-patterns/
 * don't use default dispatcher!
 */
class SearchViewModel  constructor(
    private val getReposInteractor: GetReposInteractor,
    private val searchReposInteractor: SearchReposInteractor,
    private val storeReposInteractor: StoreReposInteractor
) : ViewModel() {
    
    private val job: Job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Main + job)
    
    val showProgressObservable = MutableLiveData<Boolean>()
    val resultRepos = MutableLiveData<List<SearchItem>>()
    val resultError = MutableLiveData<Throwable>()
    
    
    override fun onCleared() {
        super.onCleared()
        scope.coroutineContext.cancelChildren()
    }
    
    fun parallelSearch(name: String){
        
        if(this.showProgressObservable.value == true) {
            return
        }
    
        this.showProgressObservable.value = true
        
        scope.launch {
           
               val storedResults: List<SearchItem> = getReposAsync(name = name)
               val searchResults: List<SearchItem> = searchAndStoreAsync(name = name)
        
               val content: List<SearchItem> = if(storedResults.isNotEmpty()){
                   storedResults
               }else{
                   searchResults
               }
               
               finishSuccessfull(result = content)
           }
       
    }
    
    /**
     * get repos with small delay from database
     */
    private suspend fun getReposAsync(name: String): List<SearchItem>{
        delay(1000)
       return withContext(Dispatchers.IO) {
            getReposInteractor.execute(name = name)
        }
    }
    
    /**
     * store repos in database
     */
    private suspend fun storeDataAsync(repos: List<SearchItem>): List<SearchItem> {
        delay(1000)
        return withContext(Dispatchers.IO) {
            storeReposInteractor.execute(repos = repos)
        }
    }
    
    /**
     * search repos on github
     */
    private suspend fun searchAsync(name: String): Result<SearchResponse>{
        delay(1000)
        return withContext(Dispatchers.IO){
            searchReposInteractor.execute(name = name)
        }
    }
    
    private suspend fun searchAndStoreAsync(name: String): List<SearchItem> {
        delay(2000)
    
        return when(val searchResults = searchAsync(name = name)){
            is Result.Success -> {
                storeDataAsync(repos = searchResults.data.items)
            }
            is Result.Error -> {
                finishWithError(error = searchResults.exception)
                return mutableListOf()
            }
        }
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
