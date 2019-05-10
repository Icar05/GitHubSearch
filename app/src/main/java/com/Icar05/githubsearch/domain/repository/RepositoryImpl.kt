package com.Icar05.githubsearch.domain.repository

import com.Icar05.githubsearch.data.dataProviders.global.GlobalDataProvider
import com.Icar05.githubsearch.data.dataProviders.local.LocalDataProvider
import com.Icar05.githubsearch.domain.models.BaseResponse
import com.Icar05.githubsearch.domain.models.Item
import io.reactivex.Observable

class RepositoryImpl(
    private val localDataProvider: LocalDataProvider,
    private val globalDataProvider: GlobalDataProvider
)
    : Repository {


    override fun storeRepos(repos: List<Item>): Observable<List<Item>>
           = localDataProvider.storeRepos(repos)

    override fun getRepos(name:String): Observable<List<Item>>
           = localDataProvider.getRepos(name)


    override fun searchRepos(name: String): Observable<BaseResponse>
            = globalDataProvider.searchRepos(name)


}