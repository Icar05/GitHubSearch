package com.Icar05.githubsearch.presentation.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.Icar05.githubsearch.R
import com.Icar05.githubsearch.presentation.extension.showOrHide

class EmptyRecyclerView: LinearLayout {
    
    private lateinit var recyclerView: RecyclerView
    
    private lateinit var tvEmpty: TextView
    
    constructor(context: Context) : this(context, null)
    
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) { init() }
    
    private fun init() {
        View.inflate(context, R.layout.empty_recycler_view,this)
        this.recyclerView = findViewById(R.id.recyclerView)
        this.tvEmpty = findViewById(R.id.tvEmpty)
        this.recyclerView.layoutManager = LinearLayoutManager(context)
    }
    
    fun showRecycler(value: Boolean){
        this.recyclerView.showOrHide(show = value)
        this.tvEmpty.showOrHide(show = !value)
    }
    
    fun <T: RecyclerView.Adapter<RecyclerView.ViewHolder>> setAdapter(adapter: T){
        this.recyclerView.adapter = adapter
    }
}