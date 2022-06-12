package com.example.lookies.search

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lookies.KueItem
import com.example.lookies.R
import com.example.lookies.camera.CameraResultPage

class ListSearchPageActAdapter(private val listCari: ArrayList<KueItem>) : RecyclerView.Adapter<ListSearchPageActAdapter.ListViewHolder>() {
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
        val name = listCari[position].namaKue
        val photo = listCari[position].gambar
        var description = listCari[position].paragaf1
        description = description.subSequence(0, 50).toString() + "..."
        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.imgPhoto)
        holder.tvName.text = name
        holder.tvDescription.text = description

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, CameraResultPage::class.java)
            intent.putExtra(PHOTO, photo)
            intent.putExtra(SNACK_NAME, name)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listCari.size

    companion object{
        private const val PHOTO = "photo"
        private const val SNACK_NAME = "snack_name"
    }
}