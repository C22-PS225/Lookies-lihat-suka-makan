package com.example.lookies.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lookies.*
import com.example.lookies.api.ApiConfig
import com.example.lookies.home.SpecialForYou
import com.example.lookies.databinding.ActivitySearchPageBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchPage : AppCompatActivity() {
    private lateinit var binding: ActivitySearchPageBinding
    private lateinit var rcySearchPageAct: RecyclerView
    private val list = ArrayList<KueItem>()

    companion object{
        private const val TAG ="SearchPage"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar?.hide()
        rcySearchPageAct = findViewById(R.id.rcySearchPageAct)
        rcySearchPageAct.setHasFixedSize(true)

        val keyWordCariKue = intent.getStringExtra("keyWordCariKue")
        Log.d(TAG,"Ini merupakan keyword kue = $keyWordCariKue")
        keyWordCariKue?.let { cariKue(it) }

        binding.mySearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(this@SearchPage, "Mencari "+binding.mySearchView.query, Toast.LENGTH_LONG).show()
                val keyword = binding.mySearchView.query.toString().trim()
                cariKue(keyword)

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //do something
                return false
            }
        })

    }

    private fun showRecyclerList(list: ArrayList<KueItem>) {
        rcySearchPageAct.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListSearchPageActAdapter(list)
        rcySearchPageAct.adapter = listHeroAdapter
    }

    private fun cariKue(namaKue: String){
        val client = ApiConfig.getApi().cariKue(namaKue)
        client.enqueue(object : Callback<CariKueResponse> {
            override fun onResponse(call: Call<CariKueResponse>, response: Response<CariKueResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        showRecyclerList(responseBody.kue)
                    }
                } else {
                    Log.e(TAG, "onFailure : " + response.message())
                }
            }
            override fun onFailure(call: Call<CariKueResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
        showRecyclerList(list)
    }
}