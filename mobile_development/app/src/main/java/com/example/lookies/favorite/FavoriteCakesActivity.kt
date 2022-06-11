package com.example.lookies.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lookies.CakesAdapter
import com.example.lookies.FavCakes
import com.example.lookies.MainViewModel
import com.example.lookies.databinding.ActivityFavoriteCakesBinding

class FavoriteCakesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteCakesBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: CakesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteCakesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        adapter = CakesAdapter()
        binding.apply {
            rvFavCakes.layoutManager = LinearLayoutManager(this@FavoriteCakesActivity)
            rvFavCakes.setHasFixedSize(true)
            rvFavCakes.adapter = adapter
        }

        viewModel.getFavCakes().observe(this) {
            val favCakes = mapUserList(it)
            if (it != null) {
                adapter.getData(favCakes)
            }
        }
    }

    private fun mapUserList(it: List<FavCakes>): ArrayList<FavCakes> {
        val listCakes = ArrayList<FavCakes>()
        for (cakes in it) {
            val favUserMapped = FavCakes(
                cakes.idKue,
                cakes.gambar,
                cakes.namaKue,
                cakes.paragraf1
            )
            listCakes.add(favUserMapped)
        }
        return listCakes
    }
}