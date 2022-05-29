package com.example.lookies

import android.graphics.Bitmap
import android.media.ThumbnailUtils
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.example.lookies.databinding.ActivityDummyResultCameraBinding
import com.example.lookies.ml.TfliteModel
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.math.min


class dummyResultCamera : AppCompatActivity() {
    private lateinit var binding: ActivityDummyResultCameraBinding

    companion object{
        private var imageSize = 150;
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDummyResultCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var resultImage = intent.getParcelableExtra<Parcelable>("BitmapImage") as Bitmap?
        if(resultImage != null){
            binding.previewImageView.setImageBitmap(resultImage)
            classifyImage(resultImage)
        }

//        val bitmap = intent.getParcelableExtra<Parcelable>("BitmapImage") as Bitmap?
    }

    private fun classifyImage(image: Bitmap){
        var dimension = min(image.width, image.height)
        var newImage = ThumbnailUtils.extractThumbnail(image, dimension, dimension)
        newImage = Bitmap.createScaledBitmap(newImage, imageSize, imageSize, false)

        val model = TfliteModel.newInstance(this)

        // Creates inputs for reference.
        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 150, 150, 3), DataType.FLOAT32)
        var byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3)
        byteBuffer.order(ByteOrder.nativeOrder())

        var intValues = intArrayOf(imageSize * imageSize)
        newImage.getPixels(intValues, 0, newImage.width, 0,0,newImage.width, newImage.height)
        var pixel = 0;
        for (i in 0..imageSize) {
            for (j in 0..imageSize){
                var value = intValues[pixel++]
                byteBuffer.putFloat(((value shr 16) and  0xFF) * (1f/1))
                byteBuffer.putFloat(((value shr 8) and  0xFF) * (1f/1))
                byteBuffer.putFloat((value  and  0xFF) * (1f/1))
            }
        }


        inputFeature0.loadBuffer(byteBuffer)

        // Runs model inference and gets result.
        val outputs = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer
        val confidences = outputFeature0.floatArray
        var maxPosition = 0
        var maxConfidence = 0f
        for (i in 0..confidences.size){
            maxConfidence = confidences[i]
            maxPosition = i
        }


        // Releases model resources if no longer used.
        model.close()


    }
}