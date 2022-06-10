package com.example.lookies

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.lookies.login.SignInActivity
import com.example.lookies.login.SignInViewModel

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user")

class SplashLookiesActivity : AppCompatActivity() {

    private lateinit var viewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_lookies)

        viewModel = ViewModelProvider(
            this, ViewModelFactory(UserPreference.getInstance(dataStore))
        )[SignInViewModel::class.java]
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.getUser().observe(this) { user ->
                if (user.isLogin) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {

                    startActivity(Intent(this, SignInActivity::class.java))
                    finish()
                }
            }
        }, DELAY)
    }

    companion object {
        const val DELAY = 3000L
    }
}