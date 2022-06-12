package com.example.lookies.search

import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lookies.KueItem2
import com.example.lookies.R
import com.example.lookies.camera.CameraResultPage
import com.example.lookies.databinding.ItemSearchBinding
import javax.sql.DataSource

class ListSearchPageAdapter(private val listSearchPage: ArrayList<KueItem2>) : RecyclerView.Adapter<ListSearchPageAdapter.ListViewHolder>() {
    class ListViewHolder(var binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val photo = listSearchPage[position].gambar
        val name = listSearchPage[position].namaKue
        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.binding.imgSearch)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, CameraResultPage::class.java)
            intent.putExtra(PHOTO, photo)
            intent.putExtra(SNACK_NAME, name)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listSearchPage.size

    companion object{
        private const val PHOTO = "photo"
        private const val SNACK_NAME = "snack_name"
    }
}
