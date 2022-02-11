package com.example.dbdinfos.presentation.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dbdinfos.data.MainRepository
import com.example.dbdinfos.data.User
import com.example.dbdinfos.util.FirebaseConfig
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import java.lang.Exception

class SignUpViewModel(private val dbdrepo: MainRepository) : ViewModel() {

    var msgSignUp = MutableLiveData<String>()
    var deciderSignUp = MutableLiveData<Boolean>()

    fun createUser(user: User) {
        val fire = FirebaseConfig().fireInstance()

        fire.createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    msgSignUp.value = "Created Successfully!"
                    deciderSignUp.value = true
                } else {
                    var exc = ""
                    try {
                        throw task.exception!!
                    } catch (e: FirebaseAuthWeakPasswordException) {
                        exc = "Digite uma senha mais forte"
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        exc = "Digite um email válido"
                    } catch (e: FirebaseAuthUserCollisionException) {
                        exc = "Essa conta já existe!"
                    } catch (e: Exception) {
                        exc = "Erro ao cadastrar usuário" + e.message
                        e.printStackTrace()
                    }
                    msgSignUp.value = exc
                    deciderSignUp.value = false
                }
            }
    }
}