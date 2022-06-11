package com.example.lookies.register

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.lookies.UserPreference
import com.example.lookies.ViewModelFactory
import com.example.lookies.databinding.ActivitySignUpBinding
import com.example.lookies.login.PassEditText
import com.example.lookies.login.SignInActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var passEditText: PassEditText
    private lateinit var viewModel: SignUpViewModel
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        customView()
        setupAction()
        setupViewModel()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupViewModel() {

        viewModel = ViewModelProvider(
            this, ViewModelFactory(UserPreference.getInstance(dataStore))
        )[SignUpViewModel::class.java]

        viewModel.isLoad.observe(this) {
            showLoad(it)
        }

        viewModel.message.observe(this) { response ->
            Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
        }
    }

    private fun customView() {
        passEditText = binding.passwordEd
    }

    private fun setupAction() {
        binding.register.setOnClickListener {
            val nama = binding.nameEd.text.toString()
            val username = binding.usernameEd.text.toString()
            val email = binding.emailEd.text.toString()
            val pass = binding.passwordEd.text.toString()
            viewModel.postRegister(nama, username, email, pass)
            viewModel.regResp.observe(this) { resp ->
                if (!resp.error) {
                    val intentToLogin = Intent(this, SignInActivity::class.java)
                    startActivity(intentToLogin)
                    finish()
                }
            }
        }
    }

    private fun showLoad(isLoad: Boolean) {
        binding.progressBar.visibility = if (isLoad) View.VISIBLE else View.INVISIBLE
    }
}