package com.example.lookies.home

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lookies.response.KueItem2
import com.example.lookies.R
import com.example.lookies.camera.CameraResultPage

class ListSpecialForYouAdapter(private val listSpecial: ArrayList<KueItem2>) : RecyclerView.Adapter<ListSpecialForYouAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
        var imgOpenBrowser: ImageView = itemView.findViewById(R.id.imgOpenBrowser)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_spesial_for_you, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val name = listSpecial[position].namaKue
        val photo = listSpecial[position].gambar
        var description = listSpecial[position].paragaf1
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

        holder.imgOpenBrowser.setOnClickListener {

            val builder1 = AlertDialog.Builder(holder.itemView.context)
            builder1.setMessage("Ingin membuka $name di Browser ?")
            builder1.setCancelable(true)
            builder1.setPositiveButton("Ya") {
                    dialog, id -> dialog.cancel()
                val link = "https://www.google.com/search?q=$name"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                Toast.makeText(holder.itemView.context, "Membuka Browser", Toast.LENGTH_LONG).show()
                holder.itemView.context.startActivity(intent)
            }

            builder1.setNegativeButton(
                "Tidak"
            ) { dialog, id -> dialog.cancel() }

            val alert11 = builder1.create()
            alert11.show()

        }
    }

    override fun getItemCount(): Int = listSpecial.size

    companion object{
        private const val PHOTO = "photo"
        private const val SNACK_NAME = "snack_name"
    }
}
