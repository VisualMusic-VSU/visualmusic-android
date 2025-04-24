package app.visualmusic.presentation.generation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat.Type.ime
import androidx.core.view.WindowInsetsCompat.Type.navigationBars
import androidx.core.view.WindowInsetsCompat.Type.statusBars
import app.visualmusic.common.ui.edgeToEdge
import app.visualmusic.common.ui.paddingBy
import app.visualmusic.databinding.ActivityGenerationBinding

class GenerationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGenerationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGenerationBinding.inflate(layoutInflater)
        setupEdgeToEdge()
        setContentView(binding.root)
    }

    private fun setupEdgeToEdge() {
        edgeToEdge {
            binding.root paddingBy statusBars() + navigationBars() + ime()
        }
    }
}