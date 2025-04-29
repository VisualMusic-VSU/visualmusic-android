package app.visualmusic.presentation.generation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat.Type.ime
import androidx.core.view.WindowInsetsCompat.Type.navigationBars
import androidx.core.view.WindowInsetsCompat.Type.statusBars
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import app.visualmusic.R
import app.visualmusic.common.util.edgeToEdge
import app.visualmusic.common.util.paddingBy
import app.visualmusic.databinding.ActivityGenerationBinding

class GenerationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGenerationBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGenerationBinding.inflate(layoutInflater)
        setupEdgeToEdge()
        setContentView(binding.root)

        setupNavController()
        setupToggleBtn()
    }

    private fun setupNavController() {
        navController = binding.fragmentContainer.getFragment<NavHostFragment>().navController
    }

    private fun setupEdgeToEdge() {
        edgeToEdge {
            binding.root paddingBy statusBars() + navigationBars() + ime()
        }
    }

    private fun setupToggleBtn() {
        binding.toggleBtn.apply {
            check(binding.autoBtn.id)

            binding.toggleBtn.addOnButtonCheckedListener { _, checkedId, isChecked ->
                if (isChecked) {
                    handleToggleBtnCheck(checkedId)
                }
            }
        }
    }

    private fun handleToggleBtnCheck(checkedId: Int) {
        val autoBtnId = binding.autoBtn.id
        val detailBtnId = binding.detailBtn.id

        when (checkedId) {
            autoBtnId -> navController.navigate(R.id.autoGenerationScreen)
            detailBtnId -> navController.navigate(R.id.detailedGenerationScreen)
            else -> navController.navigate(R.id.promptGenerationScreen)
        }
    }
}