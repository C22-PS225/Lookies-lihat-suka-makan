package com.example.lookies.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lookies.R
import com.example.lookies.api.ApiConfig
import com.example.lookies.camera.CameraResultPage
import com.example.lookies.databinding.FragmentHomeBinding
import com.example.lookies.favorite.FavoriteCakesActivity
import com.example.lookies.response.GetAllResponse
import com.example.lookies.response.KueItem2
import com.example.lookies.search.SearchPage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class HomeFragment : Fragment() {

    companion object{
        private const val KEYWORD_KUE = "keyWordCariKue"
        private const val TAG = "HomeFragment"
        private const val PHOTO = "photo"
        private const val SNACK_NAME = "snack_name"
    }
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var rcySpecialForYou: RecyclerView
    private val list = ArrayList<KueItem2>()
    private val arrayInt = ArrayList<Int>()
    private lateinit var bannerName: String
    private lateinit var bannerPhoto: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        rcySpecialForYou = root.findViewById(R.id.rcyViewSpesialForYou)
        rcySpecialForYou.setHasFixedSize(true)

        getBanner()
        getSpesial()

        binding.imageBanner.setOnClickListener {
            val intent = Intent(requireContext(), CameraResultPage::class.java)
            intent.putExtra(PHOTO, bannerPhoto)
            intent.putExtra(SNACK_NAME, bannerName)
           startActivity(intent)
        }


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

    private fun showRecyclerList(list: ArrayList<KueItem2>) {
        rcySpecialForYou.layoutManager = LinearLayoutManager(requireActivity())
        val listHeroAdapter = ListSpecialForYouAdapter(list)
        rcySpecialForYou.adapter = listHeroAdapter
    }

    private fun getBanner(){
        val client = ApiConfig.getApi().getAllStory()
        client.enqueue(object : Callback<GetAllResponse> {
            override fun onResponse(call: Call<GetAllResponse>, response: Response<GetAllResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        val random = Random.nextInt(0,15)
                        Log.e(TAG, "NILAI RANDOM =  $random")
                        val itemIndexPhoto = responseBody.kue[random].gambar
                        bannerName = responseBody.kue[random].namaKue
                        bannerPhoto = responseBody.kue[random].gambar
                        Glide.with(requireContext())
                            .load(itemIndexPhoto)
                            .into(binding.imageBanner)
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

    private fun getSpesial(){
        list.clear()
        arrayInt.clear()
        val client = ApiConfig.getApi().getAllStory()
        client.enqueue(object : Callback<GetAllResponse> {
            override fun onResponse(call: Call<GetAllResponse>, response: Response<GetAllResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        val itemData = responseBody.kue
                        var cc = 1
                        while (cc <=5){
                            val rand = Random.nextInt(0,15)
                            if(!arrayInt.contains(rand)){
                                arrayInt.add(rand)
                                cc += 1
                            }
                            Log.e(TAG, "Bawah = $rand")
                        }
                        for(i in arrayInt){
                            val data = itemData[i]
                            list.add(data)
                        }
                        showRecyclerList(list)
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
