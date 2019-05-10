package com.Icar05.githubsearch.data.dataProviders.local

import com.Icar05.githubsearch.domain.models.Item
import io.reactivex.Observable

interface LocalDataProvider {

    fun storeRepos(repos: List<Item>): Observable<List<Item>>

    fun getRepos(name:String): Observable<List<Item>>

}