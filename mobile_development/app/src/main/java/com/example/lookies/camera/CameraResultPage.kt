package com.example.lookies.camera

import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.lookies.DetailCakesActivity.Companion.DETAIL_CAKES
import com.example.lookies.MainViewModel
import com.example.lookies.PredictKueResponse
import com.example.lookies.R
import com.example.lookies.api.ApiConfig
import com.example.lookies.databinding.ActivityCameraResultPageBinding
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream


class CameraResultPage : AppCompatActivity() {
    private lateinit var binding: ActivityCameraResultPageBinding
    private lateinit var shimmerLayout: ShimmerFrameLayout
    private lateinit var viewModel: MainViewModel

    companion object {
        var imageSize = 150
        private const val IMAGE_BITMAP = "BitmapImage"
        private const val PHOTO = "file"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraResultPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar?.hide()
        val imageFromIntent = intent.getParcelableExtra<BitmapImage>(IMAGE_BITMAP) as BitmapImage
        binding.previewImageView.setImageBitmap(imageFromIntent.img)
        shimmerLayout = findViewById(R.id.myShimmer)
        shimmerLayout.startShimmer()

        predictKue(imageFromIntent.img)

        binding.imgBackButton.setOnClickListener {
            finish()
        }
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val idKue = intent.getIntExtra(DETAIL_CAKES, 0)
        val gambar = intent.getStringExtra(DETAIL_CAKES)
        val namaKue = intent.getStringExtra(DETAIL_CAKES)
        val paragraf1 = intent.getStringExtra(DETAIL_CAKES)
        var isFavorite = false
        CoroutineScope(Dispatchers.IO).launch {
            val cakes = viewModel.favCakes(idKue)
            withContext(Dispatchers.Main) {
                if (cakes != null) {
                    if (cakes > 0) {
                        binding.favBtn.isChecked = true
                        isFavorite = true
                    } else {
                        binding.favBtn.isChecked = false
                        isFavorite = false
                    }
                }
            }
        }
        binding.favBtn.setOnClickListener {
            isFavorite = !isFavorite
            if (isFavorite) {
                viewModel.addFavCakes(
                    idKue,
                    gambar.toString(),
                    namaKue.toString(),
                    paragraf1.toString()
                )
            } else {
                viewModel.deleteFavCakes(idKue)
            }
            binding.favBtn.isChecked = isFavorite
        }
    }


    private fun predictKue(image: Bitmap?) {
        val file = image?.let { imageToFile(it, "ImageFile.jpeg") }
//        val file = image as File
        val requestImageFile = file!!.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            PHOTO,
            file.name,
            requestImageFile
        )
        val service = ApiConfig.getApi().uploadImage(imageMultipart)
        service.enqueue(object : Callback<PredictKueResponse> {
            override fun onResponse(
                call: Call<PredictKueResponse>,
                response: Response<PredictKueResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        binding.txtSnackName.text = responseBody.namaKue
                        binding.txtParagraph1.text = responseBody.paragaf1
                        binding.txtParagraph2.text = responseBody.paragaf2
                        binding.txtIngredients.text = responseBody.bahan

                        shimmerLayout.stopShimmer()
                        shimmerLayout.visibility = View.GONE
                        binding.scrollView2.visibility = View.VISIBLE

                    }
                }
            }

            override fun onFailure(call: Call<PredictKueResponse>, t: Throwable) {
                Toast.makeText(this@CameraResultPage, "Terjadi Kesalahan", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }


    override fun onBackPressed() {
        finish()
    }

    private fun imageToFile(bitmap: Bitmap, newName: String): File? {
        var fileImage: File? = null
        return try {
            fileImage = File(
                Environment.getExternalStorageDirectory().toString() + File.separator + newName
            )
            fileImage.createNewFile()
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 0, bos)
            val bitmapdata = bos.toByteArray()
            val fos = FileOutputStream(fileImage)
            fos.write(bitmapdata)
            fos.flush()
            fos.close()
            fileImage
        } catch (e: Exception) {
            e.printStackTrace()
            fileImage
        }
    }
}