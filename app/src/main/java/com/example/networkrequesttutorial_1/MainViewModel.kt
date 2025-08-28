package com.example.networkrequesttutorial_1

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _user= MutableStateFlow<User>(User(0,"","",))
    val user: StateFlow<User> = _user

    fun getUser() {
        viewModelScope.launch {
            try {
                val response= KtorClient.client.get("https://jsonplaceholder.typicode.com/users/1")
                val user=response.body<User>()
                _user.value=user
            }
            catch (e: Exception)
            {
                Log.e("MainViewModel", e.message.toString())
            }
        }
    }
}