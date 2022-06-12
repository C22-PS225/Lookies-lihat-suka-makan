package com.example.lookies.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.lookies.R
import com.example.lookies.databinding.FragmentSearchBinding
import com.example.lookies.home.HomeFragment


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SearchFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private var mFragmentManager: FragmentManager? = null
    private var fragment: Fragment? = null



    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
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
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        mFragmentManager = parentFragmentManager
        fragment = mFragmentManager!!.findFragmentByTag(HomeFragment::class.java.simpleName)
        val searchPage = SearchPageFragment()

        if (fragment !is HomeFragment) {
            mFragmentManager!!
                .beginTransaction()
                .add(R.id.frame_container, searchPage, HomeFragment::class.java.simpleName)
                .commit()
        }

        binding.mySearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(context, "Mencari "+binding.mySearchView.query, Toast.LENGTH_LONG).show()
                val intent = Intent(requireContext(), SearchPage::class.java)
                val keyword = binding.mySearchView.query.toString()
                intent.putExtra("keyWordCariKue",keyword)
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


}