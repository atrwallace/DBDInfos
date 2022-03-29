package br.com.dbdinfos.presentation.login

import br.com.dbdinfos.data.MainRepository
import br.com.dbdinfos.data.User
import br.com.dbdinfos.util.FirebaseConfig
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

class LoginViewModelTest {

    private var mainRepository = mockk<MainRepository>(relaxed = true)
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var auth: FirebaseAuth

    @Before
    fun setUp() {
        loginViewModel = LoginViewModel(mainRepository)
        auth = FirebaseConfig().fireInstance()
    }

    @Test
    fun setLoginPass() {

    }

    @Test
    fun checkSoma(){
        loginViewModel.somar()
        verify { loginViewModel.somar() }
        println(loginViewModel.somar())
    }

    @Test
    fun startLogin() {
        val user = User("manoela@manoela.com", "bt@Hy225421")
        loginViewModel.startLogin(user)
        // Verify para verificar se a função foi chamada, não para teste
        verify { loginViewModel.startLogin(user) }
        // Verificando se valor é true depois da função ser chamada
        assertTrue(loginViewModel.loginPass.value!!)
    }

}