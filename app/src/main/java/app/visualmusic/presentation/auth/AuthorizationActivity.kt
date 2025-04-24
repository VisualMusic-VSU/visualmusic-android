package app.visualmusic.presentation.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat.Type.ime
import androidx.core.view.WindowInsetsCompat.Type.navigationBars
import androidx.core.view.WindowInsetsCompat.Type.statusBars
import app.visualmusic.common.ui.edgeToEdge
import app.visualmusic.common.ui.marginTo
import app.visualmusic.common.ui.paddingBy
import app.visualmusic.databinding.ActivityAuthorizationBinding

class AuthorizationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthorizationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthorizationBinding.inflate(layoutInflater)
        setupEdgeToEdge()
        setContentView(binding.root)
    }

    private fun setupEdgeToEdge() {
        edgeToEdge {
            binding.root paddingBy statusBars() + navigationBars()
            binding.startRegistrationBtn marginTo ime()
        }
    }
}