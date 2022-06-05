package com.example.lookies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.lookies.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var rcySpecialForYou: RecyclerView
    private lateinit var rcySpecialForYou2: RecyclerView
    private val list = ArrayList<SpecialForYou>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.supportActionBar?.hide()
        val navView: BottomNavigationView = binding.myBottomNavigationView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.myHome, R.id.mySearch, R.id.myPlaceholder, R.id.myAccount, R.id.mySetting
        ).build()

//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.myHome, R.id.mySearch, R.id.myPlaceholder, R.id.myAccount, R.id.mySetting
//            )
//        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


//        binding.myBottomNavigationView.background = null
//        binding.myBottomNavigationView.menu.getItem(2).isEnabled = false
//
//        rcySpecialForYou = findViewById(R.id.rcyViewSpesialForYou)
//        rcySpecialForYou.setHasFixedSize(true)
//        rcySpecialForYou2 = findViewById(R.id.rcyViewSpesialForYou2)
//        rcySpecialForYou.setHasFixedSize(true)

//        rcySpecialForYou2 = findViewById(R.id.rcyViewSpesialForYou2)
//        rcySpecialForYou2.setHasFixedSize(true)

//        list.addAll(listSpecial)
//        showRecyclerList()

//        binding.myFabCamera.setOnClickListener{
//            var intent = Intent(this, PreCameraCapture::class.java)
//            startActivity(intent)
//        }
    }

//    private val listSpecial: ArrayList<SpecialForYou>
//        get() {
//            val dataName = resources.getStringArray(R.array.data_name)
//            val dataDescription = resources.getStringArray(R.array.data_description)
//            val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
//            val listHero = ArrayList<SpecialForYou>()
//            for (i in dataName.indices) {
//                val hero = SpecialForYou(dataName[i],dataDescription[i], dataPhoto.getResourceId(i, -1))
//                listHero.add(hero)
//            }
//            return listHero
//        }
//    private fun showRecyclerList() {
//        rcySpecialForYou.layoutManager = LinearLayoutManager(this)
//        val listHeroAdapter = ListSpecialForYouAdapter(list)
//        rcySpecialForYou.adapter = listHeroAdapter
//
//        rcySpecialForYou2.layoutManager = LinearLayoutManager(this)
//        val listHeroAdapter2 = ListSpecialForYouAdapter(list)
//        rcySpecialForYou2.adapter = listHeroAdapter2
//    }
}
