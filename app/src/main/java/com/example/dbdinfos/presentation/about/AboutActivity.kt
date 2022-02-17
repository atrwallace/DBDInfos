package com.example.dbdinfos.presentation.about

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.Toast
import com.example.dbdinfos.databinding.ActivityAboutBinding
import com.example.dbdinfos.presentation.home.HomeActivity
import com.example.dbdinfos.presentation.home.HomeViewModel
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class AboutActivity : AppCompatActivity(), AndroidScopeComponent {
    override val scope: Scope by activityScope()
    private lateinit var binding: ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.clickLinkText.setOnClickListener {
            val browser = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/atrwallace"))
            try {
                startActivity(browser)
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
                Toast.makeText(
                    this, "No app was found to open your URL, please install a web browser",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this@AboutActivity, HomeActivity::class.java))
        finish()
    }
}