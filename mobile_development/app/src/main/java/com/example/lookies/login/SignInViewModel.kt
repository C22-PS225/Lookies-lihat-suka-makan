package com.example.lookies.login

import android.util.Log
import androidx.lifecycle.*
import com.example.lookies.api.ApiConfig
import com.example.lookies.data_class.UserModel
import com.example.lookies.main.UserPreference
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInViewModel(private val pref: UserPreference) : ViewModel() {

    private val _isLoad = MutableLiveData<Boolean>()
    val isLoad: LiveData<Boolean> = _isLoad

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _loginResp = MutableLiveData<LoginResponse>()
    val loginResp: LiveData<LoginResponse> = _loginResp

    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    fun saveUser(user: UserModel) {
        viewModelScope.launch {
            pref.saveUser(user)
        }
    }

    fun userLogin() {
        viewModelScope.launch {
            pref.userLogin()
        }
    }

    fun postLogin(email: String, password: String) {
        _isLoad.value = true
        val client = ApiConfig.getApi().postLogin(email, password)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                _isLoad.value = false
                if (response.isSuccessful && response.body() != null) {
                    _loginResp.value = response.body()
                    _message.value = response.body()?.message

                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                    _message.value = response.message()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _isLoad.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                _message.value = t.message
            }

        })
    }

    fun userLogout() {
        viewModelScope.launch {
            pref.userLogout()
        }
    }

    companion object {
        private const val TAG = "SignInViewModel"
    }

}