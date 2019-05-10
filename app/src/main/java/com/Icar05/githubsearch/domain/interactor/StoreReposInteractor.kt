package com.Icar05.githubsearch.domain.interactor

import com.Icar05.githubsearch.domain.models.Item
import com.Icar05.githubsearch.domain.repository.Repository
import io.reactivex.Observable
import javax.inject.Inject

open class StoreReposInteractor
    @Inject constructor(private val repository: Repository) {

        fun storeRepos(repos: List<Item>): Observable<List<Item>>
                = repository.storeRepos(repos)

    }