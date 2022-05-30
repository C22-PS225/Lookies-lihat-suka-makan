package com.example.lookies

import android.graphics.Bitmap
import android.os.Bundle
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
        var byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3)
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
            if(confidences[i] > maxConfidence){
                maxConfidence = confidences[i]
                maxPosition = i
            }
        }

        val classes = arrayOf("kue_ape","kue_bika_ambon","kue_cenil","kue_dadar_gulung","kue_gethuk_lindri","kue_kastengel","kue_klepon","kue_lapis","kue_lemper","kue_lumpur","kue_nagasari","kue_pastel","kue_putri_salju","kue_putu_ayu","kue_risoles","kue_serabi" )

        binding.txtSnackName.text = classes[maxPosition]

//        {'kue_ape': 0, 'kue_bika_ambon': 1, 'kue_cenil': 2, 'kue_dadar_gulung': 3, 'kue_gethuk_lindri': 4, 'kue_kastengel': 5, 'kue_klepon': 6, 'kue_lapis': 7, 'kue_lemper': 8, 'kue_lumpur': 9, 'kue_nagasari': 10, 'kue_pastel': 11, 'kue_putri_salju': 12, 'kue_putu_ayu': 13, 'kue_risoles': 14, 'kue_serabi': 15}
        model.close()
    }

    override fun onBackPressed() {

        finish()
    }
}