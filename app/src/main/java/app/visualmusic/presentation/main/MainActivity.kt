package app.visualmusic.presentation.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import app.visualmusic.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavView()
    }

    private fun setupBottomNavView() {
        val navController = binding.fragmentContainer.getFragment<NavHostFragment>().navController
        binding.bottomNavView.setupWithNavController(navController)
    }
}