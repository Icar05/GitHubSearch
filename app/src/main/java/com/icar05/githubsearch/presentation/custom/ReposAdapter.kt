package com.icar05.githubsearch.presentation.custom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.icar05.githubsearch.R
import com.icar05.githubsearch.domain.model.SearchItem


class ReposAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    
    
    private var content: List<SearchItem> = ArrayList()
    
    fun setContent(content: List<SearchItem>) {
        this.content = content
        notifyDataSetChanged()
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = parent.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.repo_item, parent, false)
        return ItemViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).bindData(item = getItem(position = position))
    }
    
    override fun getItemId(position: Int): Long =  position.toLong()
    
    override fun getItemCount(): Int =  this.content.size
    
    private fun getItem(position: Int): SearchItem = this.content[position]
    
    
    
    
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        
        private var tvName: TextView = itemView.findViewById(R.id.tvName)
        private var tvFullName: TextView = itemView.findViewById(R.id.tvFullName)
        private var tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        private var tvUrl: TextView = itemView.findViewById(R.id.tvUrl)
        private var tvLanguage: TextView = itemView.findViewById(R.id.tvLanguage)
        
        fun bindData(item: SearchItem) {
            tvName.text = item.name
            tvFullName.text = item.full_name
            tvDescription.text = item.description
            tvUrl.text = item.url
            tvLanguage.text = item.language
        }
        
    }
    
}

