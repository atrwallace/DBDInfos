package com.example.dbdinfos.presentation.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import com.example.dbdinfos.databinding.ActivitySplashBinding
import com.example.dbdinfos.presentation.home.HomeActivity
import com.example.dbdinfos.util.FirebaseConfig


class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val auth = FirebaseConfig().fireInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val handler = Handler()
        handler.postDelayed({
            // do something after 1000ms
            setUpScreen()
        }, 2000)
    }

    fun setUpScreen() {
        if (auth.currentUser != null) {
            startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
            finish()

        } else {
            val intent = Intent(this, MainActivity::class.java)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this@SplashActivity,
                binding.imageView2,
                "profile"
            )
            startActivity(intent, options.toBundle())
            finish()
        }

    }
}