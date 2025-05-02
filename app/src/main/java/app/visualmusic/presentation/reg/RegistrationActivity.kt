package app.visualmusic.presentation.reg

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat.Type.ime
import androidx.core.view.WindowInsetsCompat.Type.navigationBars
import androidx.core.view.WindowInsetsCompat.Type.statusBars
import app.visualmusic.common.util.edgeToEdge
import app.visualmusic.common.util.marginTo
import app.visualmusic.common.util.paddingBy
import app.visualmusic.databinding.ActivityRegistrationBinding
import app.visualmusic.presentation.auth.AuthorizationActivity

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setupEdgeToEdge()
        setContentView(binding.root)

        setupBtnListeners()
    }

    private fun setupEdgeToEdge() {
        edgeToEdge {
            binding.root paddingBy statusBars() + navigationBars()
            binding.goBackBtn marginTo ime()
        }
    }

    private fun setupBtnListeners() {
        with(binding) {
            goBackBtn.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
            createAccountBtn.setOnClickListener {
                startAuthorizationActivity()
            }
        }
    }

    private fun startAuthorizationActivity() {
        startActivity(Intent(this, AuthorizationActivity::class.java))
    }
}