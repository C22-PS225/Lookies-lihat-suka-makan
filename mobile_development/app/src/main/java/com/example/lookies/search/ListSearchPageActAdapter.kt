package com.example.lookies.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lookies.KueItem
import com.example.lookies.R
import com.example.lookies.SearchRes

class ListSearchPageActAdapter(private val listSpecial: ArrayList<KueItem>) : RecyclerView.Adapter<ListSearchPageActAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_spesial_for_you, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//        val (name, description, photo) = listSpecial[position]
        val name = listSpecial[position].namaKue
        val photo = listSpecial[position].gambar
        var description = listSpecial[position].paragaf1
        description = description.subSequence(0, 27).toString() + "..."
        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.imgPhoto)
        holder.tvName.text = name
        holder.tvDescription.text = description
    }

    override fun getItemCount(): Int = listSpecial.size
}