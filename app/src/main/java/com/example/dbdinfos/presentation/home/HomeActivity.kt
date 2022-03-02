package com.example.dbdinfos.presentation.home

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.example.dbdinfos.R
import com.example.dbdinfos.databinding.ActivityHomeBinding
import com.example.dbdinfos.presentation.about.AboutActivity
import com.example.dbdinfos.presentation.login.MainActivity
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class HomeActivity : AppCompatActivity(), AndroidScopeComponent {
    lateinit var binding: ActivityHomeBinding
    override val scope: Scope by activityScope()
    private val vm: HomeViewModel by viewModel()
    private lateinit var adapter: HomeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        actionBarSetUp()
        setUpViewPager()
        selectDTO()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate: MenuInflater = menuInflater
        inflate.inflate(R.menu.home_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuConfig -> {
                Toast.makeText(this, "Function not yet implemented", Toast.LENGTH_LONG).show()
            }
            R.id.logout -> {
                Toast.makeText(this, "Successfully logged out", Toast.LENGTH_LONG).show()
                vm.logout()
                startActivity(Intent(this@HomeActivity, MainActivity::class.java))
                finish()
            }
            R.id.about -> {
                startActivity(Intent(this@HomeActivity, AboutActivity::class.java))
                finish()
            }
            else -> Toast.makeText(applicationContext, "Nothing here to see", Toast.LENGTH_SHORT)
                .show()
        }


        return super.onOptionsItemSelected(item)
    }

    fun setUpViewPager() {
        adapter = HomeAdapter(supportFragmentManager)
        adapter.addFrag(KillerFragment(), "Killers")
        adapter.addFrag(SurvFragment(), "Survivors")
        binding.viewpager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewpager)
    }

    fun actionBarSetUp() {
        setSupportActionBar(binding.toolbarID.toolbar)
        supportActionBar?.title = "P E R K S"
    }

    fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }

    fun selectDTO() {
        if (isNetworkAvailable(this)) {
            vm.getAllPerks()
        } else {
            vm.getLocal()
        }

    }
}