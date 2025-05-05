package app.visualmusic.presentation.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat.Type.ime
import androidx.core.view.WindowInsetsCompat.Type.navigationBars
import androidx.core.view.WindowInsetsCompat.Type.statusBars
import app.visualmusic.common.util.edgeToEdge
import app.visualmusic.common.util.marginTo
import app.visualmusic.common.util.paddingBy
import app.visualmusic.databinding.ActivityAuthorizationBinding
import app.visualmusic.presentation.main.MainActivity
import app.visualmusic.presentation.reg.RegistrationActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthorizationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthorizationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthorizationBinding.inflate(layoutInflater)
        setupEdgeToEdge()
        setContentView(binding.root)

        setupBtnListeners()
    }

    private fun setupEdgeToEdge() {
        edgeToEdge {
            binding.root paddingBy statusBars() + navigationBars()
            binding.startRegistrationBtn marginTo ime()
        }
    }

    private fun setupBtnListeners() {
        with(binding) {
            loginBtn.setOnClickListener {
                startMainActivity()
            }
            startRegistrationBtn.setOnClickListener {
                startRegistrationActivity()
            }
        }
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun startRegistrationActivity() {
        startActivity(Intent(this, RegistrationActivity::class.java))
    }
}