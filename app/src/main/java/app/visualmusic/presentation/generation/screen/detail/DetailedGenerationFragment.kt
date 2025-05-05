package app.visualmusic.presentation.generation.screen.detail

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import app.visualmusic.R
import app.visualmusic.common.bottomsheet.MultipleChoiceListBottomSheet
import app.visualmusic.common.bottomsheet.SingleChoiceListBottomSheet
import app.visualmusic.common.util.setDebouncedClickListener
import app.visualmusic.databinding.FragmentDetailedGenerationBinding
import app.visualmusic.presentation.generation.GenerationViewModel
import app.visualmusic.presentation.generation.InputCoverNameBottomSheet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailedGenerationFragment : Fragment() {
    private lateinit var binding: FragmentDetailedGenerationBinding
    private val viewModel: GenerationViewModel by activityViewModels()

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

        setupObservers()
        setupBtnListeners()
    }

    private fun setupObservers() {
        viewModel.apply {
            audioUri.observe(viewLifecycleOwner) {
                it?.let {
                    setAudioUploadedState(it)
                    binding.generateBtn.isEnabled = true  //TODO затычка (наверное)
                } ?: setAudioUploadState()

            }
            selectedMood.observe(viewLifecycleOwner) {
                var text = it?.name ?: resources.getString(R.string.empty_mood_settings_chip_text)
                binding.selectedMood.text = text
            }
            selectedStyle.observe(viewLifecycleOwner) {
                var text = it?.name ?: resources.getString(R.string.empty_style_settings_chip_text)
                binding.selectedStyle.text = text
            }
            selectedText.observe(viewLifecycleOwner) {
                var text = it ?: resources.getString(R.string.empty_text_settings_chip_text)
                binding.selectedText.text = text
            }
        }
    }

    private fun setAudioUploadedState(audioUri: Uri) {
        binding.audioFileInfo.apply {
            setAudioData(audioUri)
        }

        //TODO затычки
        binding.apply {
            if (viewModel.getSelectedGenresIds().isEmpty()) {
                genresSetting.setIsAutoStatus(true)
            }
            if (viewModel.getSelectedMoodId() == null) {
                moodSetting.setIsAutoStatus(true)
            }
            if (viewModel.getSelectedStyleId() == null) {
                styleSetting.setIsAutoStatus(true)
            }
            if (viewModel.getText().isNullOrBlank()) {
                textSetting.setIsAutoStatus(true)
            }
        }
    }

    private fun setAudioUploadState() {
        binding.audioFileInfo.apply {
            setFileNotChooseState()
        }

        //TODO затычка
        binding.apply {
            generateBtn.isEnabled = false
            genresSetting.setIsAutoStatus(false)
            moodSetting.setIsAutoStatus(false)
            styleSetting.setIsAutoStatus(false)
            textSetting.setIsAutoStatus(false)
        }
    }

    private fun setupBtnListeners() {
        binding.apply {
            audioSettingsBtn.setDebouncedClickListener {
                pickAudioLauncher.launch("audio/x-wav")
            }
            genresSetting.onSettingBtnClick {
                showGenresFilterBottomSheetList()
            }
            moodSetting.onSettingBtnClick {
                showMoodFilterBottomSheetList()
            }
            styleSetting.onSettingBtnClick {
                showStyleFilterBottomSheetList()
            }
            textSetting.onSettingBtnClick {
                showTextEditorBottomSheet()
            }
            generateBtn.setDebouncedClickListener {
                showInputCoverNameBottomSheet()
            }
        }
    }

    private val pickAudioLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            handleAudioSelection(uri)
        }

    private fun handleAudioSelection(uri: Uri?) {
        uri?.let {
            viewModel.setAudioUri(it)
        }
    }

    private fun showGenresFilterBottomSheetList() {
        MultipleChoiceListBottomSheet(
            title = resources.getString(R.string.genres_filter),
            allItems = viewModel.getAllGenres(),
            initialSelectedItemIds = viewModel.getSelectedGenresIds(),
            onApply = viewModel::setSelectedGenresFromIds
        ).show(childFragmentManager, MultipleChoiceListBottomSheet.TAG)
    }

    private fun showMoodFilterBottomSheetList() {
        SingleChoiceListBottomSheet(
            title = resources.getString(R.string.mood_filter),
            allItems = viewModel.getAllMoods(),
            initialSelectedItem = viewModel.getSelectedMoodId(),
            onApply = viewModel::setSelectedMoodFromId
        ).show(childFragmentManager, SingleChoiceListBottomSheet.TAG)
    }

    private fun showStyleFilterBottomSheetList() {
        SingleChoiceListBottomSheet(
            title = resources.getString(R.string.style_filter),
            allItems = viewModel.getAllStyles(),
            initialSelectedItem = viewModel.getSelectedStyleId(),
            onApply = viewModel::setSelectedStyleFromId
        ).show(childFragmentManager, SingleChoiceListBottomSheet.TAG)
    }

    private fun showTextEditorBottomSheet() {
        TextEditorBottomSheet().show(childFragmentManager, TextEditorBottomSheet.TAG)
    }

    private fun showInputCoverNameBottomSheet() {
        InputCoverNameBottomSheet(
            binding.root
        ).show(
            parentFragmentManager,
            InputCoverNameBottomSheet.TAG
        )
    }
}