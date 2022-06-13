package com.example.lookies.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lookies.databinding.ActivityFavoriteCakesBinding
import com.example.lookies.injection.Injection

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
            adapter.setListFavCakes(it)
        }
        adapter = FavCakeAdapter()
        binding.rvFavCakes.setHasFixedSize(true)
        binding.rvFavCakes.adapter = adapter

        binding.imgBackButton.setOnClickListener {
            finish()
        }
    }
}