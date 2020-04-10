package com.Icar05.githubsearch.domain.dataProviders

import com.Icar05.githubsearch.domain.model.SearchResponce
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


/*
  https://api.github.com/search/repositories?q=HandyRatingBar
 */
interface GlobalDataProvider {
    
    @GET("search/repositories?")
    fun searchRepos(
        @Query("q") name: String,
        @Query("sort")  sort: String = "forks"
    ): Observable<SearchResponce>
    
}