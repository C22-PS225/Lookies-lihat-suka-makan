package com.example.lookies

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.lookies.databinding.ActivitySignInBinding

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user")

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var viewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_sign_in)

        setupView()
        setupViewModel()
        setupAction()




        binding.login.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            viewModel.postLogin(username, password)
            viewModel.loginResp.observe(this) { resp ->
                saveUserSession(
                    UserModel(
                        resp.token,
                        true
                    )
                )
            }
        }
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
        )[SignInViewModel::class.java]

        viewModel.isLoad.observe(this) {
            showLoad(it)
        }

        viewModel.message.observe(this) { response ->
            Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupAction() {
        binding.login.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            viewModel.postLogin(username, password)
            viewModel.loginResp.observe(this) { resp ->
                saveUserSession(
                    UserModel(
                        resp.token,
                        true
                    )
                )
            }
        }
//        binding.createAcc.setOnClickListener {
//            val intentToSignUp = Intent(this, SignUpActivity::class.java)
//            startActivity(intentToSignUp)
//            finish()
//        }

        val clickHere = findViewById<TextView>(R.id.createAcc)
        clickHere.setOnClickListener {
            val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveUserSession(user: UserModel) {
        viewModel.saveUser(user)
        viewModel.userLogin()
        val intentToMain = Intent(this, MainActivity::class.java)
        startActivity(intentToMain)
        finish()
    }

    private fun showLoad(isLoad: Boolean) {
        binding.progressBar.visibility = if (isLoad) View.VISIBLE else View.INVISIBLE
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}