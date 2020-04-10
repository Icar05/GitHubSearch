package com.Icar05.githubsearch.domain.interactor

import com.Icar05.githubsearch.domain.model.SearchItem
import com.Icar05.githubsearch.domain.repository.Repository
import io.reactivex.Observable

open class GetReposInteractor constructor(private val repository: Repository) {
    fun execute(name:String): Observable<List<SearchItem>> = repository.getRepos(name)
}
