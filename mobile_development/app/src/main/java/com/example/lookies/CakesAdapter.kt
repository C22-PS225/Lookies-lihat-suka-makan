package com.example.lookies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lookies.databinding.ItemSpesialForYouBinding

class CakesAdapter :
    RecyclerView.Adapter<CakesAdapter.ViewHolder>() {
    private val listUsers = ArrayList<FavCakes>()

    inner class ViewHolder(private val binding: ItemSpesialForYouBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cakes: FavCakes) {
            binding.apply {
                tvItemName.text = cakes.namaKue
                tvItemDescription.text = cakes.paragraf1
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemSpesialForYouBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listUsers[position])
    }

    fun getData(users: ArrayList<FavCakes>) {
        listUsers.clear()
        listUsers.addAll(users)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listUsers.size
}