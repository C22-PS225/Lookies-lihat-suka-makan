package com.example.lookies.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lookies.Injection
import com.example.lookies.R
import com.example.lookies.databinding.ActivityFavoriteCakesBinding

class FavoriteCakesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteCakesBinding
    private lateinit var adapter: FavCakeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteCakesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar?.hide()

        binding.rvFavCakes.layoutManager = LinearLayoutManager(this)
        val repo = Injection.provideRepository(this)
        repo.getFavUser().observe(this) {
            adapter.setListFavGithubUser(it)
        }
        adapter = FavCakeAdapter()
        binding.rvFavCakes.setHasFixedSize(true)
        binding.rvFavCakes.adapter = adapter

        binding.imgBackButton.setOnClickListener {
            finish()
        }
    }

//    private fun mapUserList(it: List<FavCakes>): ArrayList<KueItem> {
//        val listCakes = ArrayList<KueItem>()
//        for (cakes in it) {
////            val favCakesMapped = KueItem(
////                cakes.namaKue,
////                cakes.gambar,
////                cakes.paragraf1,
////                )
////            listCakes.add(favCakesMapped)
//        }
//        return listCakes
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        onBackPressed()
//        return super.onSupportNavigateUp()
//    }
}