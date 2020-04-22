package com.icar05.githubsearch.data.repository

import com.icar05.githubsearch.data.model.Result
import com.icar05.githubsearch.domain.dataProviders.GlobalDataProvider
import com.icar05.githubsearch.domain.dataProviders.LocalDataProvider
import com.icar05.githubsearch.domain.model.SearchItem
import com.icar05.githubsearch.domain.model.SearchResponse
import com.icar05.githubsearch.domain.repository.Repository
import retrofit2.Response


class RepositoryImpl(
    private val localDataProvider: LocalDataProvider,
    private val globalDataProvider: GlobalDataProvider
) : Repository {
    
    override suspend fun storeRepos(repos: List<SearchItem>): List<SearchItem> {
        return localDataProvider.storeRepos(repos = repos)
    }
    
    override suspend fun getRepos(name: String): List<SearchItem> {
        return localDataProvider.getRepos(name = name)
    }
    
    override suspend fun searchRepos(name: String): Result<SearchResponse> {
        return safeApiResult(
            call = {globalDataProvider.searchReposAsync(name = name).await()}
        )
    }
    
    /**
     * https://medium.com/nuances-of-programming/
        android-networking-%D0%B2-2019-retrofit-%D1%81-kotlin-coroutines-7254e68ca4a4
     */
    private suspend fun <T: Any> safeApiResult(call: suspend ()-> Response<T>) : Result<T> {
        val response = call.invoke()
        if(response.isSuccessful) return Result.Success(response.body()!!)
        
        return Result.Error(Throwable(message = response.errorBody().toString()))
    }
}