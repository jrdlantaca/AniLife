package com.example.anilife.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentController
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.anilife.R
import com.example.anilife.databinding.ActivityHomeBinding
import com.example.anilife.ui.fragment.WinterFragment
import com.example.anilife.vo.AiringAnime
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.card.MaterialCardView
import dagger.android.support.DaggerAppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityHomeBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_home)
        supportActionBar!!.hide()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        binding.bottomNavView.setupWithNavController(navController)
        setupTopBar()

    }

    private fun setupTopBar() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.animeFragment) {
                supportActionBar!!.show()
                supportActionBar!!.title = getString(R.string.anime_details)
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                bottom_nav_view.visibility = View.GONE
            } else {
                progress_bar.visibility = View.VISIBLE
                supportActionBar!!.hide()
                bottom_nav_view.visibility = View.VISIBLE
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}