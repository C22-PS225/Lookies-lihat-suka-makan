package com.example.lookies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lookies.login.SignInViewModel
import com.example.lookies.register.SignUpViewModel

class ViewModelFactory(private val pref: UserPreference) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> {
                SignUpViewModel() as T
            }
            modelClass.isAssignableFrom(SignInViewModel::class.java) -> {
                SignInViewModel(pref) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}