package com.example.lookies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class PreCameraCapture : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pre_camera_capture)
        this.supportActionBar?.hide()
    }
}