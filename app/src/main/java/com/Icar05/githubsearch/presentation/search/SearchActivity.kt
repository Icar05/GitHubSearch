package com.Icar05.githubsearch.presentation.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Toast
import com.Icar05.githubsearch.R
import com.Icar05.githubsearch.custom.RepoListViewAdapter
import com.Icar05.githubsearch.domain.models.Item
import com.Icar05.githubsearch.presentation.base.BaseActivity
import com.example.alex.githubsearchexample.app.LAST_REQUESTED_NAME
import kotlinx.android.synthetic.main.activity_search.*


class SearchActivity :
        BaseActivity<SearchPresenter>(), SearchView {



    private lateinit var adapter: RepoListViewAdapter
    private var lastRequestedName: String = ""
    private var inProgress: Boolean = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        presenter.attach(this)
        setUp()
        refreshSearch(savedInstanceState)
    }



    private fun refreshSearch(savedInstanceState: Bundle?){
        if (savedInstanceState != null) {
            lastRequestedName = savedInstanceState.getString(LAST_REQUESTED_NAME)

            if(!lastRequestedName.isEmpty()){
                presenter.parallelSearch(lastRequestedName)
            }
        }
    }

    public override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putString(LAST_REQUESTED_NAME, lastRequestedName)
        super.onSaveInstanceState(savedInstanceState)
    }


    override fun onSearchCanceled(){tvEmpty.visibility = View.VISIBLE}


    override fun showProgress() {
        inProgress = true
        progress.visibility = View.VISIBLE
        listView.visibility = View.GONE
        tvEmpty.visibility = View.GONE
        ivSearch.setImageDrawable(getDrawable(R.drawable.button_search_active))
    }

    override fun hideProgress() {
        inProgress = false
        progress.visibility = View.GONE
        listView.visibility = View.VISIBLE
        ivSearch.setImageDrawable(getDrawable(R.drawable.button_search_inactive))
    }



    override fun onInternetDisabled() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(getString(R.string.trouble_internet_message))
        builder.setPositiveButton(getString(R.string.ok)) { dialog, _ ->
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }


    override fun showRepos(repos: List<Item>) { adapter.setContent(repos) }




    private fun setUp() {

        adapter = RepoListViewAdapter()
        listView.emptyView = tvEmpty
        listView.adapter = adapter



        ivSearch.setOnClickListener {

            if(!inProgress){
                lastRequestedName = etSearchRepos.text.toString()

                if(lastRequestedName.isEmpty()){
                    Toast.makeText(this, this.getString(R.string.empty_query_warning), Toast.LENGTH_SHORT).show()
                } else{
                    presenter.parallelSearch(lastRequestedName)
                }
            }else{
                presenter.cancelSearchRequest()
            }

        }
    }



    companion object {
        fun start(context: Context) {
            val intent = Intent(context, SearchActivity::class.java)
            context.startActivity(intent)
        }
    }
}
