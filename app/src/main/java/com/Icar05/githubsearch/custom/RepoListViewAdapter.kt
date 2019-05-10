package com.Icar05.githubsearch.custom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.Icar05.githubsearch.R

import com.Icar05.githubsearch.domain.models.Item


class RepoListViewAdapter : BaseAdapter() {


    private var content: List<Item> = ArrayList()

    fun setContent(content: List<Item>){
        this.content = content
        notifyDataSetChanged()
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

        val inflater = parent?.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View?
        val holder: ItemViewHolder?

        if (convertView == null) {
            view = inflater.inflate(R.layout.repo_item, parent, false)
            holder = ItemViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ItemViewHolder
        }


        bindData(holder, position)


        return view
    }


    private fun bindData(holder: ItemViewHolder, position: Int){

        val item = getItem(position)
        holder.tvName.text = item.name
        holder.tvFullName.text = item.full_name
        holder.tvDescription.text = item.description
        holder.tvUrl.text = item.url
        holder.tvLanguage.text = item.language
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(position: Int): Item {
        return content[position]
    }

    override fun getCount(): Int {
        return content.size
    }



    inner class ItemViewHolder(itemView: View){
        var tvName : TextView = itemView.findViewById(R.id.tvName)
        var tvFullName: TextView = itemView.findViewById(R.id.tvFullName)
        var tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        var tvUrl: TextView = itemView.findViewById(R.id.tvUrl)
        var tvLanguage : TextView = itemView.findViewById(R.id.tvLanguage)
    }

}