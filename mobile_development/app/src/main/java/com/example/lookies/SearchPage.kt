package com.example.lookies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lookies.databinding.ActivitySearchPageBinding

class SearchPage : AppCompatActivity() {
    private lateinit var binding: ActivitySearchPageBinding
    private lateinit var rcySearchPageAct: RecyclerView
    private val list = ArrayList<SpecialForYou>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar?.hide()
        rcySearchPageAct = findViewById(R.id.rcySearchPageAct)
        rcySearchPageAct.setHasFixedSize(true)

        list.addAll(listSpecial)
        showRecyclerList()
    }

    private val listSpecial: ArrayList<SpecialForYou>
        get() {
            val dataName = resources.getStringArray(R.array.data_name)
            val dataDescription = resources.getStringArray(R.array.data_description)
            val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
            val listSearched = ArrayList<SpecialForYou>()
            for (i in dataName.indices) {
                val hero = SpecialForYou(dataName[i],dataDescription[i], dataPhoto.getResourceId(i, -1))
                listSearched.add(hero)
            }
            return listSearched
        }

    private fun showRecyclerList() {
        rcySearchPageAct.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListSearchPageActAdapter(list)
        rcySearchPageAct.adapter = listHeroAdapter
    }
}