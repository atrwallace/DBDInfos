package br.com.dbdinfos.presentation.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import br.com.dbdinfos.data.User
import br.com.dbdinfos.databinding.ActivitySignUpBinding
import br.com.dbdinfos.presentation.home.HomeActivity
import br.com.dbdinfos.presentation.login.LoginActivity
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope
import java.util.regex.Matcher
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity(), AndroidScopeComponent {
    private lateinit var binding: ActivitySignUpBinding
    override val scope: Scope by activityScope()
    private val vm: SignUpViewModel by viewModel()
    private lateinit var user: User
    private lateinit var email: EditText
    private lateinit var passwordIN: EditText
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewers()
        setupObservers()

        progressBar.visibility = View.GONE
    }

    private fun initViewers() {
        email = binding.inputEmail
        passwordIN = binding.inputPassword
        progressBar = binding.progressBar

    }

    private fun setupObservers() {
        binding.btnHome.setOnClickListener {
            startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
            finish()
        }
        binding.btnLogIn.setOnClickListener {
            setupLogin()
            vm.deciderSignUp.observe(this, Observer {
                if (it==true){
                    startActivity(Intent(this@SignUpActivity, HomeActivity::class.java))
                    finish()
                }
            })
        }
    }


    private fun validationFields(password: String): Boolean {

        if (email.text.toString().trim().isEmpty()) {
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
        if (email.text.toString().trim().isEmpty() && passwordIN.text.toString().trim().isEmpty()) {
            Toast.makeText(this, "Fill out all the fields!", Toast.LENGTH_SHORT).show()

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

    private fun setupLogin(): Boolean {

        return if (validationFields(passwordIN.text.toString().trim())) {
            progressBar.visibility = View.VISIBLE
            user = User(email.text.toString().trim(), passwordIN.text.toString().trim())
            vm.createUser(user)
            vm.msgSignUp.observe(this, Observer {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            })
            true
        } else {
            vm.msgSignUp.observe(this, Observer {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            })
            false
        }
    }

}