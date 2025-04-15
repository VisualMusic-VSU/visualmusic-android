package app.visualmusic.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat.Type.statusBars
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import app.visualmusic.common.ui.edgeToEdge
import app.visualmusic.common.ui.paddingBy
import app.visualmusic.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setupEdgeToEdge()
        setContentView(binding.root)

        setupBottomNavView()
    }

    private fun setupEdgeToEdge() {
        edgeToEdge {
            binding.root paddingBy statusBars()
        }
    }

    private fun setupBottomNavView() {
        val navController = binding.fragmentContainer.getFragment<NavHostFragment>().navController
        binding.bottomNavView.setupWithNavController(navController)
    }
}