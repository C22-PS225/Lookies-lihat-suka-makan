package com.example.lookies.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lookies.*
import com.example.lookies.api.ApiConfig
import com.example.lookies.databinding.FragmentSearchPageBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        private const val TAG = "SearchPageFragment"
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

//        list.addAll(listSpecial)
//        showRecyclerList()

        cariKue()
        return root
    }

//    private val listSpecial: ArrayList<DataSearchPage>
//        get() {
//            val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
//            val listSearch = ArrayList<DataSearchPage>()
//            for (i in 0 until dataPhoto.length()) {
//                val sp = DataSearchPage(dataPhoto.getResourceId(i, -1))
//                listSearch.add(sp)
//            }
//            return listSearch
//        }

    private fun cariKue(){
        val dataKue = resources.obtainTypedArray(R.array.data_kue)
        for (i in 0 until dataKue.length()) {
            val namaKue = dataKue.getString(i)
//            Log.d(TAG,"ini merupakan nama kue = "+ namaKue)
            val client = ApiConfig.getApi().getAllStory()
            client.enqueue(object : Callback<GetAllResponse> {
                override fun onResponse(call: Call<GetAllResponse>, response: Response<GetAllResponse>) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            showRecyclerList(responseBody.kue)
                        }
                    } else {
                        Log.e(TAG, "onFailure : " + response.message())
                    }
                }
                override fun onFailure(call: Call<GetAllResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message}")
                }
            })
        }


    }

    private fun showRecyclerList(array: ArrayList<KueItem2>) {

        rcySearchPage.layoutManager = GridLayoutManager(requireActivity(), 2)
        val listSearchPage = ListSearchPageAdapter(array)
        rcySearchPage.adapter = listSearchPage
    }


}