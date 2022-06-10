package com.example.lookies.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lookies.home.ListSpecialForYouAdapter
import com.example.lookies.R
import com.example.lookies.home.SpecialForYou
import com.example.lookies.databinding.FragmentSearchPageTwoBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SearchPageFragmentTwo : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentSearchPageTwoBinding? = null
    private val binding get() = _binding!!
    private lateinit var rcySearchPageTwo: RecyclerView
    private val list = ArrayList<SpecialForYou>()

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchPageFragmentTwo().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchPageTwoBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        rcySearchPageTwo = root.findViewById(R.id.rcySearchPage)
//        rcySearchPageTwo.setHasFixedSize(true)
//
//        list.addAll(listSpecial)
//        showRecyclerList()

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
        rcySearchPageTwo.layoutManager = LinearLayoutManager(requireActivity())
        val listHeroAdapter = ListSpecialForYouAdapter(list)
        rcySearchPageTwo.adapter = listHeroAdapter

        rcySearchPageTwo.layoutManager = LinearLayoutManager(requireActivity())
        val listHeroAdapter2 = ListSpecialForYouAdapter(list)
        rcySearchPageTwo.adapter = listHeroAdapter2
    }


}