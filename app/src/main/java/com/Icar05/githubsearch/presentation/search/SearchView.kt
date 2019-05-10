package com.Icar05.githubsearch.presentation.search

import com.Icar05.githubsearch.domain.models.Item
import com.Icar05.githubsearch.presentation.base.BaseView

interface SearchView : BaseView {

      fun showProgress()

      fun hideProgress()

      fun onInternetDisabled()

      fun showRepos(repos: List<Item>)

      fun onSearchCanceled()

}