package com.example.lookies.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lookies.camera.CameraResultPage
import com.example.lookies.databinding.ItemSpesialForYou2Binding
import com.example.lookies.injection.Injection

class FavCakeAdapter : RecyclerView.Adapter<FavCakeAdapter.FavoriteCakesHolder>() {
    private val listCakes = ArrayList<CakeEntity>()

    fun setListFavCakes(listFavCakes: List<CakeEntity>) {
        val diffCallback = CakeCallback(
            this.listCakes,
            listFavCakes as ArrayList<CakeEntity>
        )
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listCakes.clear()
        this.listCakes.addAll(listCakes)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteCakesHolder {
        val binding =
            ItemSpesialForYou2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteCakesHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteCakesHolder, position: Int) {
        holder.bind(listCakes[position])
    }

    inner class FavoriteCakesHolder(private val binding: ItemSpesialForYou2Binding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(users: CakeEntity) {
            val myPhoto = users.gambar
            val name = users.name
            with(binding) {
                Glide.with(itemView.context)
                    .load(myPhoto)
                    .into(binding.imgItemPhoto)
                binding.tvItemName.text = name
                binding.tvItemDescription.text = users.desc

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, CameraResultPage::class.java)
                    intent.putExtra(PHOTO, myPhoto)
                    intent.putExtra(SNACK_NAME, name)
                    itemView.context.startActivity(intent)
                }

                imgFavDelete.setOnClickListener {
                    val repo = Injection.provideRepository(itemView.context)
                    repo.deleteCake(users)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return listCakes.size
    }

    companion object {
        private const val PHOTO = "photo"
        private const val SNACK_NAME = "snack_name"
    }
}