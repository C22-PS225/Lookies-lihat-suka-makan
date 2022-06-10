package com.example.lookies.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.lookies.R

class ListSearchPageAdapter(private val listSpecial: ArrayList<DataSearchPage>) : RecyclerView.Adapter<ListSearchPageAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.imgSearch)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val photo = listSpecial[position].photo
        holder.imgPhoto.setImageResource(photo)
    }

    override fun getItemCount(): Int = listSpecial.size
}
