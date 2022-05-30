package com.example.lookies
import android.Manifest
import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.lookies.databinding.ActivityPreCameraCaptureBinding
import java.io.ByteArrayOutputStream
import java.io.File
import kotlin.math.min


class PreCameraCapture : AppCompatActivity() {
    private lateinit var binding: ActivityPreCameraCaptureBinding
    private var result: Bitmap? = null
    private var selectedImg: Uri? = null

    companion object {
        const val CAMERA_X_RESULT = 200
        private const val TAG = "PreCameraCapture"
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
        private const val IMAGE_BITMAP = "BitmapImage"
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERA_X_RESULT) {
            val myFile = it.data?.getSerializableExtra("picture") as File
            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean
            result = rotateBitmap(
                BitmapFactory.decodeFile(myFile.path),
                isBackCamera
            )

            binding.btnOpenCamera.text = "Retake Photo"
            binding.txtSuggestion.text = "Ready to meet your new favorite snack?"
            binding.previewImageView.setImageBitmap(result)

        }
    }



    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            selectedImg = result.data?.data as Uri
            val myFile = uriToFile(selectedImg!!, this)
            binding.previewImageView.setImageURI(selectedImg)
        }
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    this,
                    "Tidak mendapatkan permission.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }
    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreCameraCaptureBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar?.hide()

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }

        binding.btnOpenCamera.setOnClickListener {
            startCameraX()
        }

        binding.galleryButton.setOnClickListener { startGallery() }

        binding.btnContinue.setOnClickListener {

            var dimension = min(result!!.width, result!!.height)
            var newImage = ThumbnailUtils.extractThumbnail(result, dimension, dimension)
            newImage = Bitmap.createScaledBitmap(newImage,
                dummyResultCamera.imageSize,
                dummyResultCamera.imageSize, false)
//            binding.previewImageView.setImageBitmap(result)
            Log.d(TAG, "Ukuran original = " + sizeOf(newImage!!))

            val imageToSend = bitmapImage(newImage)
            val intent = Intent(this, dummyResultCamera::class.java)
            intent.putExtra(IMAGE_BITMAP,imageToSend)
            startActivity(intent)

//            val bitmap = Bitmap.createScaledBitmap(capturedImage, width, height, true)

//            result = compressBitmap(result!!,5)
//            Log.d(TAG, "Ukuran original = " + sizeOf(result!!))
//            binding.previewImageView.setImageBitmap(result)


//            if(result != null || selectedImg != null){
//
//                //ini cuman bisa kalau dapat foto dari kamera
//                val imgToSend = bitmapImage(result?.let { it1 -> compressBitmap(it1) })
//                Log.d(TAG, "Ukuran original = " + sizeOf(compressBitmap(result!!)))
//                val intent = Intent(this, dummyResultCamera::class.java)
//                intent.putExtra(IMAGE_BITMAP,imgToSend)
//
//                startActivity(intent)

//
//            }else{
//                Toast.makeText(
//                    this,
//                    "Please enter a photo first",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }

        }
    }

    private fun startCameraX() {
        val intent = Intent(this, CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }


    fun sizeOf(data: Bitmap): Int {
        return data.allocationByteCount
    }

    private fun compressBitmap(bitmap:Bitmap, quality:Int=5):Bitmap{
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream)
        val byteArray = stream.toByteArray()
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
}