package br.com.dbdinfos.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.dbdinfos.data.User
import br.com.dbdinfos.databinding.ActivityLoginBinding
import br.com.dbdinfos.presentation.home.HomeActivity
import br.com.dbdinfos.presentation.signup.SignUpActivity
import br.com.dbdinfos.util.FirebaseConfig
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope
import java.util.regex.Matcher
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity(), AndroidScopeComponent {

    lateinit var binding: ActivityLoginBinding
    override val scope: Scope by activityScope()
    private val vm: LoginViewModel by viewModel()
    private lateinit var user: User
    private lateinit var email: EditText
    private lateinit var passwordIN: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var resetPassword: TextView
    private lateinit var btnSignUP: TextView
    private lateinit var btnLogIn: Button
    private val auth = FirebaseConfig().fireInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewers()
        setupObservers()
        progressBar.visibility = View.GONE

    }

    private fun initViewers() {
        email = binding.inputEmail
        passwordIN = binding.inputPassword
        progressBar = binding.progressBar
        resetPassword = binding.btnForgetPassword
        btnSignUP = binding.btnSignUP
        btnLogIn = binding.btnLogIn
    }

    private fun setupObservers() {

        resetPassword.setOnClickListener {
            resetPassword()
            vm.resetPasswordCheck.observe(this, Observer {
                if (it == true) {
                    Toast.makeText(this, vm.resetPassword.value.toString(), Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(this, vm.resetPassword.value.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            })
        }

        btnSignUP.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
            finish()
        }
        btnLogIn.setOnClickListener {
            setupLogin()
            vm.loginPass.observe(this, Observer {
                if (it == true) {
                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "No", Toast.LENGTH_LONG).show()
                }
            })
        }

    }


    private fun validationFields(password: String): Boolean {

        if (email.text.isEmpty()) {
            email.error = "Type an email!"
            email.requestFocus()

            return false
        }
        if (!passwordMatch(password)) {
            passwordIN.error =
                "Your password needs to be between 8 to 16 characters. It must have\n" +
                        "uppercase character, lowercase character, a number and a special character."

            passwordIN.requestFocus()
            return false
        }
        if (password.isEmpty()) {
            passwordIN.error = "Type a password!"
            passwordIN.requestFocus()

            return false
        }
        if (email.text.isEmpty() && passwordIN.text.isEmpty()) {
            Toast.makeText(this, "Fill out all the fields!", Toast.LENGTH_SHORT).show()

            return false

        }

        return true
    }

    private fun passwordMatch(password: String): Boolean {

        val default: Pattern
        val passDefault =
            "^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,16}\$"

        default = Pattern.compile(passDefault)
        val matcher: Matcher = default.matcher(password)


        return matcher.matches()
    }

    private fun setupLogin() {

        if (validationFields(passwordIN.text.toString())) {
            progressBar.visibility = View.VISIBLE
            user = User(email.text.toString(), passwordIN.text.toString())
            vm.startLogin(user)
            vm.msgLogin.observe(this, Observer {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            })
        } else {
            vm.errorLogin.observe(this, Observer {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            })
        }
    }

    private fun resetPassword() {
        if (email.text.isEmpty()) {
            email.error = "Insert email in the field"
            email.requestFocus()
        } else {
            vm.resetPassword(email.text.toString())
        }
    }

}