package com.example.lookies.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lookies.CakeCallback
import com.example.lookies.CakeEntity
import com.example.lookies.Injection
import com.example.lookies.camera.CameraResultPage
import com.example.lookies.databinding.ItemSpesialForYou2Binding
import com.example.lookies.databinding.ItemSpesialForYouBinding
import com.example.lookies.home.ListSpecialForYouAdapter

class FavCakeAdapter : RecyclerView.Adapter<FavCakeAdapter.FavoriteGithubUserHolder>() {
    private val listFavGithubUser = ArrayList<CakeEntity>()

    fun setListFavGithubUser(listFavGithubUser: List<CakeEntity>) {
        val diffCallback = CakeCallback(this.listFavGithubUser,
            listFavGithubUser as ArrayList<CakeEntity>
        )
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listFavGithubUser.clear()
        this.listFavGithubUser.addAll(listFavGithubUser)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteGithubUserHolder {
        val binding = ItemSpesialForYou2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteGithubUserHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteGithubUserHolder, position: Int) {
        holder.bind(listFavGithubUser[position])
    }

    inner class FavoriteGithubUserHolder(private val binding: ItemSpesialForYou2Binding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(users: CakeEntity) {
            val myPhoto = users.avatarUrl
            val name = users.name
            with(binding) {
                Glide.with(itemView.context)
                    .load(myPhoto)
                    .into(binding.imgItemPhoto)
                binding.tvItemName.text = name
                binding.tvItemDescription.text = users.desc

                itemView.setOnClickListener{
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
        return listFavGithubUser.size
    }

    companion object{
        private const val PHOTO = "photo"
        private const val SNACK_NAME = "snack_name"
    }
}