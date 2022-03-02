package com.example.dbdinfos.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dbdinfos.data.MainRepository
import com.example.dbdinfos.data.User
import com.example.dbdinfos.util.FirebaseConfig
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainViewModel(private val dbdrepo: MainRepository) : ViewModel() {

    var msgLogin = MutableLiveData<String>()
    var loginPass = MutableLiveData<Boolean>()
    var errorLogin = MutableLiveData<String>()


    fun startLogin(user: User) {
        val fire = FirebaseConfig().fireInstance()
        viewModelScope.launch {
            fire.signInWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        msgLogin.postValue("Successful Login!")
                        loginPass.postValue(true)
                    } else {
                        var exc = ""
                        try {
                            throw task.exception!!
                        } catch (e: FirebaseAuthInvalidUserException) {
                            loginPass.postValue(false)
                            exc = "This account doesn't exist or it's invalid!"
                        } catch (e: FirebaseAuthInvalidCredentialsException) {
                            loginPass.postValue(false)
                            exc = "Email or password invalid!"
                        } catch (e: Exception) {
                            loginPass.postValue(false)
                            exc = "Error: " + e.message
                            e.printStackTrace()
                        }
                        errorLogin.value = exc
                        loginPass.postValue(false)
                    }
                }
        }
    }

}