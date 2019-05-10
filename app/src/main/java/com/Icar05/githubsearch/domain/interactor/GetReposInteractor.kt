package com.Icar05.githubsearch.domain.interactor

import com.Icar05.githubsearch.domain.models.Item
import com.Icar05.githubsearch.domain.repository.Repository
import io.reactivex.Observable
import javax.inject.Inject

open class GetReposInteractor
@Inject constructor(private val repository: Repository) {

    fun getRepos(name:String): Observable<List<Item>> = repository.getRepos(name)

}