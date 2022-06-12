package com.example.lookies.search

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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

//        list.addAll(listSpecial)
//        showRecyclerList()
    }

//    private val listSpecial: ArrayList<SpecialForYou>
//        get() {
//            val dataName = resources.getStringArray(R.array.data_name)
//            val dataDescription = resources.getStringArray(R.array.data_description)
//            val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
//            val listSearched = ArrayList<SpecialForYou>()
//            for (i in dataName.indices) {
//                val hero = SpecialForYou(dataName[i],dataDescription[i], dataPhoto.getResourceId(i, -1))
//                listSearched.add(hero)
//            }
//            return listSearched
//        }

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
                        list.add(responseBody.kue[0])
//                        val gambar = responseBody.gambar
//                        val namaJajan = responseBody.namaKue
//                        var desc = responseBody.paragaf1
//                        desc = desc.subSequence(0, 27).toString() + "..."
//                        val sr = SearchRes(namaJajan, desc, gambar)
//                        list.add(sr)


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