package com.example.dbdinfos.presentation.login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.dbdinfos.data.User
import com.example.dbdinfos.databinding.ActivityMainBinding
import com.example.dbdinfos.presentation.home.HomeActivity
import com.example.dbdinfos.presentation.signup.SignUpActivity
import com.example.dbdinfos.util.FirebaseConfig
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope
import java.util.regex.Matcher
import java.util.regex.Pattern

class MainActivity : AppCompatActivity(), AndroidScopeComponent {

    lateinit var binding: ActivityMainBinding
    override val scope: Scope by activityScope()
    private val vm: MainViewModel by viewModel()
    private lateinit var user: User
    private lateinit var email: EditText
    private lateinit var passwordIN: EditText
    private lateinit var progressBar: ProgressBar
    private val auth = FirebaseConfig().fireInstance()
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewers()
        setupObservers()
        progressBar.visibility = View.GONE

    }


    override fun onBackPressed() {

    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            startActivity(Intent(this@MainActivity, HomeActivity::class.java))
            finish()
        }
    }

    private fun initViewers() {
        email = binding.inputEmail
        passwordIN = binding.inputPassword
        progressBar = binding.progressBar
    }

    private fun setupObservers() {
        binding.btnSignUP.setOnClickListener {
            startActivity(Intent(this@MainActivity, SignUpActivity::class.java))
            finish()
        }
        binding.btnLogIn.setOnClickListener {
            setupLogin()
            vm.loginPass.observe(this, Observer {
                if (it == true) {
                    startActivity(Intent(this@MainActivity, HomeActivity::class.java))
                    finish()
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


            // Changed
            return false

        }

        return true
    }

    private fun passwordMatch(password: String): Boolean {

        val default: Pattern
        val passdefault =
            "^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,16}\$"

        default = Pattern.compile(passdefault)
        val matcher: Matcher = default.matcher(password)


        return matcher.matches()
    }

    private fun setupLogin(): Boolean {

        return if (validationFields(passwordIN.text.toString())) {
            progressBar.visibility = View.VISIBLE
            user = User(email.text.toString(), passwordIN.text.toString())
            vm.startLogin(user)
            vm.msgLogin.observe(this, Observer {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            })
            true
        } else {
            vm.errorLogin.observe(this, Observer {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            })
            false
        }
    }

}