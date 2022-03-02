package com.example.dbdinfos.presentation.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dbdinfos.data.MainRepository
import com.example.dbdinfos.data.User
import com.example.dbdinfos.util.FirebaseConfig
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.coroutines.launch
import java.lang.Exception

class SignUpViewModel(private val dbdrepo: MainRepository) : ViewModel() {

    var msgSignUp = MutableLiveData<String>()
    var deciderSignUp = MutableLiveData<Boolean>()

    fun createUser(user: User) {
        val fire = FirebaseConfig().fireInstance()

        viewModelScope.launch {
            fire.createUserWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        msgSignUp.value = "Created Successfully!"
                        deciderSignUp.postValue(true)
                    } else {
                        var exc = ""
                        try {
                            throw task.exception!!
                        } catch (e: FirebaseAuthWeakPasswordException) {
                            exc = "Type a stronger password"
                        } catch (e: FirebaseAuthInvalidCredentialsException) {
                            exc = "Type a valid email"
                        } catch (e: FirebaseAuthUserCollisionException) {
                            exc = "This account already exists"
                        } catch (e: Exception) {
                            exc = "Error: " + e.message
                            e.printStackTrace()
                        }
                        msgSignUp.value = exc
                        deciderSignUp.postValue(false)
                    }
                }
        }
    }
}