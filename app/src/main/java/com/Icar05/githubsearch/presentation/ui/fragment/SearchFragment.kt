package com.Icar05.githubsearch.presentation.ui.fragment

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.Icar05.githubsearch.R
import com.Icar05.githubsearch.domain.model.SearchItem
import com.Icar05.githubsearch.presentation.custom.ReposAdapter
import com.Icar05.githubsearch.presentation.extension.onTextChanges
import com.Icar05.githubsearch.presentation.extension.setLoadingBackground
import com.Icar05.githubsearch.presentation.extension.showOrHide
import com.Icar05.githubsearch.presentation.util.DialogUtil
import com.Icar05.githubsearch.presentation.util.ErrorHandler
import com.Icar05.githubsearch.presentation.viewmodel.SearchViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_search.*


import javax.inject.Inject

class SearchFragment : BaseFragment(R.layout.fragment_search) {
    
    
    
    private lateinit var viewModel: SearchViewModel
    
    private val adapter: ReposAdapter = ReposAdapter()
    
    @Inject
    lateinit var dialogUtil: DialogUtil
    
    @Inject
    lateinit var errorHandler: ErrorHandler
    
    override fun getTitleToolbarText(): Int = R.string.search
    
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        AndroidSupportInjection.inject(this)
        viewModel = getViewModel(SearchViewModel::class.java) as SearchViewModel
        setUp()
    }
    
    private fun setUp() {
        eRVSearch.setAdapter(adapter = adapter)
        
        viewModel.showProgressObservable.observe(viewLifecycleOwner, Observer {
            showProgress(value = it)
        })
        
        viewModel.resultRepos.observe(viewLifecycleOwner, Observer {
            showResult(repos = it.orEmpty())
        })
        
        viewModel.resultError.observe(viewLifecycleOwner, Observer {
            context?.let { cxt ->
                val error = errorHandler.prepareTextError(error = it, context = cxt)
                dialogUtil.showErrorOnDialog(error, cxt)
            }
        })
        
        ivSearch.setOnClickListener{
            val text = etSearchRepos.text.toString()
            if (text.isEmpty()) {
                Toast.makeText(context, getString(R.string.empty_query_warning), Toast.LENGTH_SHORT).show()
            } else {
                viewModel.parallelSearch(name = text)
            }
        }
        
        etSearchRepos.onTextChanges {
            ivSearch.showOrHide(show = !it.isNullOrEmpty())
        }
    }
    
    private fun showProgress(value: Boolean){
        bgSearch.setLoadingBackground(value = value)
        progress.showOrHide(show = value)
        eRVSearch.showOrHide(show = !value)
    }
    
    private fun showResult(repos: List<SearchItem>) {
        adapter.setContent(repos)
        eRVSearch.showRecycler(value = repos.isNotEmpty())
    }
}
