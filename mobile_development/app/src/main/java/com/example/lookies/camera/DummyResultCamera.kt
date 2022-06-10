package com.example.lookies.camera

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.lookies.databinding.ActivityDummyResultCameraBinding
import com.example.lookies.ml.TfliteModel
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder


class dummyResultCamera : AppCompatActivity() {
    private lateinit var binding: ActivityDummyResultCameraBinding

    companion object{
        var imageSize = 150
        private const val TAG = "dummyResultCamera"
        private const val IMAGE_BITMAP = "BitmapImage"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDummyResultCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar?.hide()
        val imageFromIntent = intent.getParcelableExtra<bitmapImage>(IMAGE_BITMAP) as bitmapImage
        binding.previewImageView.setImageBitmap(imageFromIntent.img)
        classifyImage(imageFromIntent.img!!)

        binding.imgBackButton.setOnClickListener{
            finish()
        }
    }

    private fun classifyImage(image: Bitmap){
        val model = TfliteModel.newInstance(this)

        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 150, 150, 3), DataType.FLOAT32)
        val byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3)
        byteBuffer.order(ByteOrder.nativeOrder())

        val intValues = IntArray(imageSize * imageSize)
        image.getPixels(intValues, 0, image.width, 0,0,image.width, image.height)

        var pixel = 0;
        for (i in 0 until imageSize) {
            for (j in 0 until imageSize) {
                var value = intValues[pixel++]
                byteBuffer.putFloat(((value shr 16) and  0xFF) * (1f/1))
                byteBuffer.putFloat(((value shr 8) and  0xFF) * (1f/1))
                byteBuffer.putFloat((value  and  0xFF) * (1f/1))
            }
        }
        inputFeature0.loadBuffer(byteBuffer)

        val outputs = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer
        val confidences = outputFeature0.floatArray
        var maxPosition = 0
        var maxConfidence = 0f
        for (i in confidences.indices){
            Log.d(TAG,"Ini merupakan confidence " + i + " = "+ confidences[i].toString())
            if(confidences[i] > maxConfidence){
                maxConfidence = confidences[i]
                maxPosition = i
            }
        }
        Log.d(TAG,"LOKASI CONFIDENCES " + maxPosition + " = "+ confidences[maxPosition])
        val classes = arrayOf("kue_ape","kue_bika_ambon","kue_cenil","kue_dadar_gulung","kue_gethuk_lindri","kue_kastengel","kue_klepon","kue_lapis","kue_lemper","kue_lumpur","kue_nagasari","kue_pastel","kue_putri_salju","kue_putu_ayu","kue_risoles","kue_serabi" )

        val classes2 = arrayOf("Dadar Gulung","Kastengel","Klepon","Lapis","Lumpur","Putri Salju","Risoles","Serabi","Pastel","Lemper","Putu Ayu","Nagasari","Ape Cake","Gethuk Lindri","Bika Ambon","Cenil" )

        val classes3 = arrayOf("Dadar Gulung","Kastengel","Klepon","Lapis","Lumpur","Putri Salju","Risoles","Pastel","Serabi","Lemper","Putu Ayu","Nagasari","Ape Cake","Gethuk Lindri","Bika Ambon","Cenil" )


        val fileName = "labels.txt"
        val inputString = application.assets.open(fileName).bufferedReader().use{it.readText()}
        val arrayKue = inputString.split("\n")

        binding.txtSnackName.text = arrayKue[maxPosition]
        model.close()
    }

    override fun onBackPressed() {
        finish()
    }
}