package com.Icar05.githubsearch.domain.interactor

import com.Icar05.githubsearch.domain.model.SearchItem
import com.Icar05.githubsearch.domain.repository.Repository
import io.reactivex.Observable


open class StoreReposInteractor constructor(private val repository: Repository) {
    fun execute(repos: List<SearchItem>): Observable<List<SearchItem>> = repository.storeRepos(repos = repos)
}