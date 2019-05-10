package com.Icar05.githubsearch.domain.repository

import com.Icar05.githubsearch.domain.models.BaseResponse
import com.Icar05.githubsearch.domain.models.Item
import io.reactivex.Observable

interface Repository {

    fun searchRepos(name: String): Observable<BaseResponse>

    fun storeRepos(repos: List<Item>): Observable<List<Item>>

    fun getRepos(name: String): Observable<List<Item>>

}