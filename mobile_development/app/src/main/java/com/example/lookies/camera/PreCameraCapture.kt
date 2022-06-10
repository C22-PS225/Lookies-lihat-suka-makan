package com.example.lookies.camera
import android.Manifest
import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.lookies.camera.dummyResultCamera
import com.example.lookies.databinding.ActivityPreCameraCaptureBinding
import com.example.lookies.rotateBitmap
import com.example.lookies.uriToFile
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
    ) { it ->
        if (it.resultCode == RESULT_OK) {
            selectedImg = it.data?.data as Uri
            val myFile = uriToFile(selectedImg!!, this)
            binding.previewImageView.setImageURI(selectedImg)
            result = null
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
        binding.imgBackButton.setOnClickListener{
            finish()
        }
        binding.btnOpenCamera.setOnClickListener {
            startCameraX()
        }

        binding.galleryButton.setOnClickListener { startGallery() }

        binding.btnContinue.setOnClickListener {

            if(result != null){
                val dimension: Int = min(result!!.width, result!!.height)
                var newImage = ThumbnailUtils.extractThumbnail(result, dimension, dimension)
                newImage = Bitmap.createScaledBitmap(newImage,
                    dummyResultCamera.imageSize,
                    dummyResultCamera.imageSize, false)
                val imageToSend = bitmapImage(newImage)
                val intent = Intent(this, dummyResultCamera::class.java)
                intent.putExtra(IMAGE_BITMAP,imageToSend)
                startActivity(intent)
            }else if(selectedImg != null){
                val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, selectedImg)

                val dimension: Int = min(bitmap!!.width, bitmap!!.height)
                var newImage = ThumbnailUtils.extractThumbnail(bitmap, dimension, dimension)
                newImage = Bitmap.createScaledBitmap(newImage,
                    dummyResultCamera.imageSize,
                    dummyResultCamera.imageSize, false)
                val imageToSend = bitmapImage(newImage)
                val intent = Intent(this, dummyResultCamera::class.java)
                intent.putExtra(IMAGE_BITMAP,imageToSend)
                startActivity(intent)
            }else{
                Toast.makeText(
                    this,
                    "Put a photo first",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun startCameraX() {
        val intent = Intent(this, CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

}