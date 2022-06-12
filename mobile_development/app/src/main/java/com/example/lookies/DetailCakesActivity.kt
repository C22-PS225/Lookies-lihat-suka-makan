package com.example.lookies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.lookies.databinding.ActivityDetailCakesBinding

class DetailCakesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailCakesBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCakesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar?.hide()

        binding.imgBackButton.setOnClickListener {
            finish()
        }

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.listCakes.observe(this) { cakes ->
            binding.apply {
                Glide.with(this@DetailCakesActivity)
                    .load(cakes.gambar)
                    .fitCenter()
                    .into(previewImageView)
                txtSnackName.text = cakes.namaKue
                txtParagraph1.text = cakes.paragaf1
                txtParagraph2.text = cakes.paragaf2
                txtIngredients.text = cakes.bahan
            }

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }


    companion object {
        const val DETAIL_CAKES = "detail_cakes"
    }

}