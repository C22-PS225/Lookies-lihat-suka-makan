package com.example.lookies.search

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lookies.R
import com.example.lookies.databinding.ItemSearchBinding
import javax.sql.DataSource

class ListSearchPageAdapter(private val listSearchPage: ArrayList<DataSearchPage>) : RecyclerView.Adapter<ListSearchPageAdapter.ListViewHolder>() {
//    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var imgPhoto: ImageView = itemView.findViewById(R.id.imgSearch)
//        var snackName: TextView = itemView.findViewById(R.id.txtSnackName)
//    }

    class ListViewHolder(var binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root)

//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): ListViewHolder {
//        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
//        return ListViewHolder(view)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//        holder.binding.txtSnackName.text = listSearchPage[position].namaKue
        val photo = listSearchPage[position].photo
        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.binding.imgSearch)
    }

    override fun getItemCount(): Int = listSearchPage.size
}
