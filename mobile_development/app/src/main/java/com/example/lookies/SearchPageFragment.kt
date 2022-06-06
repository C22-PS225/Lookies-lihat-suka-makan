package com.example.lookies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lookies.databinding.FragmentSearchPageBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SearchPageFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var rcySearchPage: RecyclerView
    private val list = ArrayList<DataSearchPage>()
    private var _binding: FragmentSearchPageBinding? = null
    private val binding get() = _binding!!

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchPageFragment().apply {
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
        _binding = FragmentSearchPageBinding.inflate(inflater, container, false)
        val root: View = binding.root


        rcySearchPage = root.findViewById(R.id.rcySearchPage)
        rcySearchPage.setHasFixedSize(true)

        list.addAll(listSpecial)
        showRecyclerList()

        return root
    }

    private val listSpecial: ArrayList<DataSearchPage>
        get() {
            val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
            val listSearch = ArrayList<DataSearchPage>()
            for (i in 0 until dataPhoto.length()) {
                val sp = DataSearchPage(dataPhoto.getResourceId(i, -1))
                listSearch.add(sp)
            }
            return listSearch
        }

    private fun showRecyclerList() {
        rcySearchPage.layoutManager = GridLayoutManager(requireActivity(), 2)
        val listHeroAdapter = ListSearchPageAdapter(list)
        rcySearchPage.adapter = listHeroAdapter
    }


}