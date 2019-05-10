package com.Icar05.githubsearch.domain.interactor

import com.Icar05.githubsearch.domain.interactor.StoreReposInteractor
import com.Icar05.githubsearch.domain.models.Item
import com.Icar05.githubsearch.domain.repository.Repository
import io.reactivex.Observable
import javax.inject.Inject

open class SearchReposInteractor
@Inject constructor(
    private val repository: Repository,
    private val storeReposInteractor: StoreReposInteractor
) {


    fun searchRepos(name: String): Observable<List<Item>> =
            repository.searchRepos(name).flatMap {
                 storeReposInteractor.storeRepos(it.items)
            }
}