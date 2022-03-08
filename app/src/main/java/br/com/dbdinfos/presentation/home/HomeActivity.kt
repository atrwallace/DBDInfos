package br.com.dbdinfos.presentation.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import br.com.dbdinfos.R
import br.com.dbdinfos.databinding.ActivityHomeBinding
import br.com.dbdinfos.presentation.about.AboutActivity
import br.com.dbdinfos.presentation.login.LoginActivity
import br.com.dbdinfos.util.ConnectionCheck
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
                startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
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

    fun selectDTO() {
        val networkCheck: Boolean = ConnectionCheck().isNetworkAvailable(this)
        if (networkCheck) {
            vm.getAllPerks()
        } else {
            vm.getLocal()
        }

    }
}