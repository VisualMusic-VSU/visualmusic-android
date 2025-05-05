package app.visualmusic.presentation.generation.screen.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.visualmusic.databinding.FragmentDetailedGenerationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailedGenerationFragment : Fragment() {
    private lateinit var binding: FragmentDetailedGenerationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailedGenerationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSettingsBtnListeners()
    }

    private fun setupSettingsBtnListeners() {
        binding.apply {
            genresSetting.onSettingBtnClick {

            }
            moodSetting.onSettingBtnClick {

            }
            styleSetting.onSettingBtnClick {

            }
            textSetting.onSettingBtnClick {
                showTextEditorBottomSheet()
            }
        }
    }

    private fun showTextEditorBottomSheet() {
        TextEditorBottomSheet().show(childFragmentManager, TextEditorBottomSheet.TAG)
    }
}