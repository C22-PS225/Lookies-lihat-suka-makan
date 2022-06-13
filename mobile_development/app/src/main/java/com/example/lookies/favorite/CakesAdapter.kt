//package com.example.lookies.favorite
//
//import android.content.Intent
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.lookies.DetailCakesActivity
//import com.example.lookies.DetailCakesActivity.Companion.DETAIL_CAKES
//import com.example.lookies.databinding.ItemSpesialForYouBinding
//import com.example.lookies.response.KueItem
//
//class CakesAdapter :
//    RecyclerView.Adapter<CakesAdapter.ViewHolder>() {
//    private val listCakes = ArrayList<KueItem>()
//
//    inner class ViewHolder(private val binding: ItemSpesialForYouBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(cakes: KueItem) {
//            binding.apply {
//                tvItemName.text = cakes.namaKue
//                tvItemDescription.text = cakes.paragaf1
//                itemView.setOnClickListener {
//                    val intentToDetail = Intent(itemView.context, DetailCakesActivity::class.java)
//                    intentToDetail.putExtra(DETAIL_CAKES, cakes.gambar)
//                    intentToDetail.putExtra(DETAIL_CAKES, cakes.namaKue)
//                    intentToDetail.putExtra(DETAIL_CAKES, cakes.paragaf1)
//                    itemView.context.startActivity(intentToDetail)
//                }
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = ItemSpesialForYouBinding.inflate(
//            LayoutInflater.from(parent.context), parent,
//            false
//        )
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(listCakes[position])
//    }
//
//    fun getData(cakes: ArrayList<KueItem>) {
//        listCakes.clear()
//        listCakes.addAll(cakes)
//        notifyDataSetChanged()
//    }
//
//    override fun getItemCount(): Int = listCakes.size
//}