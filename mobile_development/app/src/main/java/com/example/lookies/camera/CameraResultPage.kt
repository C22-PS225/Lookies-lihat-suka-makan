package com.example.lookies.camera

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.lookies.CariKueResponse
import com.example.lookies.PredictKueResponse
import com.example.lookies.R
import com.example.lookies.api.ApiConfig
import com.example.lookies.databinding.ActivityCameraResultPageBinding
import com.example.lookies.search.SearchPage
import com.facebook.shimmer.ShimmerFrameLayout
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
    private var imageToDetail: String? = ""
    private var imageFromIntent: BitmapImage? = null
    private var snackName: String? = ""

    companion object {
        var imageSize = 150
        private const val IMAGE_BITMAP = "BitmapImage"
        private const val PHOTO = "file"
        private const val DETAIL_PHOTO = "photo"
        private const val TAG = "CameraResultPage"
        private const val SNACK_NAME = "snack_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraResultPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar?.hide()
        shimmerLayout = findViewById(R.id.myShimmer)


        imageFromIntent = intent.getParcelableExtra(IMAGE_BITMAP)
        imageToDetail = intent.getStringExtra(DETAIL_PHOTO)
        snackName = intent.getStringExtra(SNACK_NAME)
        if (imageFromIntent != null){
            shimmerLayout.startShimmer()
            binding.previewImageView.setImageBitmap(imageFromIntent!!.img)
            predictKue(imageFromIntent!!.img)
        }else if(imageToDetail != ""){
            Glide.with(this)
                .load(imageToDetail)
                .into(binding.previewImageView)
            snackName?.let { cariKue(it) }
        }
        binding.imgBackButton.setOnClickListener {
            finish()
        }
    }

    private fun cariKue(namaKue: String){
        val client = ApiConfig.getApi().cariKue(namaKue)
        client.enqueue(object : Callback<CariKueResponse> {
            override fun onResponse(call: Call<CariKueResponse>, response: Response<CariKueResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        val itemData = responseBody.kue[0]
                        binding.txtSnackName.text = itemData.namaKue
                        binding.txtParagraph1.text = itemData.paragaf1
                        binding.txtParagraph2.text = itemData.paragaf2
                        binding.txtIngredients.text = itemData.bahan

                        shimmerLayout.stopShimmer()
                        shimmerLayout.visibility = View.GONE
                        binding.scrollView2.visibility = View.VISIBLE
                    }
                } else {
                    Log.e(TAG, "onFailure : " + response.message())
                }
            }
            override fun onFailure(call: Call<CariKueResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    private fun predictKue(image: Bitmap?) {
        val file = image?.let { imageToFile(it, "ImageFile.jpeg") }
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