package com.example.lookies.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lookies.R
import com.example.lookies.search.SearchPage
import com.example.lookies.databinding.FragmentHomeBinding
import com.example.lookies.favorite.FavoriteCakesActivity


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class HomeFragment : Fragment() {

    companion object{
        private const val KEYWORD_KUE = "keyWordCariKue"
    }
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var rcySpecialForYou: RecyclerView
    private lateinit var rcySpecialForYou2: RecyclerView
    private val list = ArrayList<SpecialForYou>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        rcySpecialForYou = root.findViewById(R.id.rcyViewSpesialForYou)
        rcySpecialForYou.setHasFixedSize(true)
        rcySpecialForYou2 = root.findViewById(R.id.rcyViewSpesialForYou2)
        rcySpecialForYou.setHasFixedSize(true)

        list.addAll(listSpecial)
        showRecyclerList()

        binding.favCakes.setOnClickListener {
            val intentToFav = Intent(requireContext(), FavoriteCakesActivity::class.java)
            startActivity(intentToFav)
        }

        binding.mySearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(context, "Mencari "+binding.mySearchView.query, Toast.LENGTH_LONG).show()
                val intent = Intent(requireContext(), SearchPage::class.java)
                val key = binding.mySearchView.query.toString().trim()
                intent.putExtra(KEYWORD_KUE,key )
                startActivity(intent)

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //do semothing
                return false
            }
        })



        return root
    }

    private val listSpecial: ArrayList<SpecialForYou>
        get() {
            val dataName = resources.getStringArray(R.array.data_name)
            val dataDescription = resources.getStringArray(R.array.data_description)
            val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
            val listHero = ArrayList<SpecialForYou>()
            for (i in dataName.indices) {
                val hero = SpecialForYou(dataName[i],dataDescription[i], dataPhoto.getResourceId(i, -1))
                listHero.add(hero)
            }
            return listHero
        }

    private fun showRecyclerList() {
        rcySpecialForYou.layoutManager = LinearLayoutManager(requireActivity())
        val listHeroAdapter = ListSpecialForYouAdapter(list)
        rcySpecialForYou.adapter = listHeroAdapter

        rcySpecialForYou2.layoutManager = LinearLayoutManager(requireActivity())
        val listHeroAdapter2 = ListSpecialForYouAdapter(list)
        rcySpecialForYou2.adapter = listHeroAdapter2
    }
}