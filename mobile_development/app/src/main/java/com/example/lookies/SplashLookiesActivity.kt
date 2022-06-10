package com.example.lookies

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.lookies.login.SignInActivity

class SplashLookiesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_lookies)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }, DELAY)
    }

    companion object {
        const val DELAY = 3000L
    }
}