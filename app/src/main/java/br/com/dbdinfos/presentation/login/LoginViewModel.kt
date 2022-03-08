package br.com.dbdinfos.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dbdinfos.data.MainRepository
import br.com.dbdinfos.data.User
import br.com.dbdinfos.util.FirebaseConfig
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class LoginViewModel(private val dbdrepo: MainRepository) : ViewModel() {

    var msgLogin = MutableLiveData<String>()
    var loginPass = MutableLiveData<Boolean>()
    var errorLogin = MutableLiveData<String>()
    var resetPassword = MutableLiveData<String>()
    var resetPasswordCheck = MutableLiveData<Boolean>()


    fun startLogin(user: User) {
        val firebaseAuth = FirebaseConfig().fireInstance()
        viewModelScope.launch {
            firebaseAuth.signInWithEmailAndPassword(user.email, user.password)
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

    fun resetPassword(email: String) {
        Firebase.auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    resetPassword.postValue("Email was sent to your inbox")
                    resetPasswordCheck.postValue(true)
                } else {
                    resetPassword.postValue("There's a problem with your account, email support!")
                    resetPasswordCheck.postValue(false)
                }
            }
    }

}