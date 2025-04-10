package app.visualmusic.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.visualmusic.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val navController = this.findNavController(R.id.nav_host_fragment)
//        binding.bottomNavView.setupWithNavController(navController)
    }
}