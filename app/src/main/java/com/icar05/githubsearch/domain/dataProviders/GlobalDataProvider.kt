package com.icar05.githubsearch.domain.dataProviders

import com.icar05.githubsearch.domain.model.SearchResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


/*
  https://api.github.com/search/repositories?q=HandyRatingBar
 */
interface GlobalDataProvider {
    
    @GET("search/repositories?")
    fun searchReposAsync(
        @Query("q") name: String,
        @Query("sort")  sort: String = "forks"
    ): Deferred<Response<SearchResponse>>
    
}