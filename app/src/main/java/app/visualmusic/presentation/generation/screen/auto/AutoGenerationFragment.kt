package app.visualmusic.presentation.generation.screen.auto

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import app.visualmusic.R
import app.visualmusic.common.util.setDebouncedClickListener
import app.visualmusic.databinding.FragmentAutoGenerationBinding
import app.visualmusic.presentation.generation.GenerationViewModel
import app.visualmusic.presentation.generation.InputCoverNameBottomSheet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AutoGenerationFragment : Fragment() {
    private lateinit var binding: FragmentAutoGenerationBinding
    private val viewModel: GenerationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAutoGenerationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBtnListeners()
        setupObservers()
    }

    private fun setupBtnListeners() {
        binding.apply {
            selectAudioBtn.setOnClickListener {
                pickAudioLauncher.launch("audio/x-wav")
            }
            generateBtn.setDebouncedClickListener {
                InputCoverNameBottomSheet(
                    binding.root
                ).show(
                    parentFragmentManager,
                    InputCoverNameBottomSheet.TAG
                )
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

    private fun setupObservers() {
        viewModel.audioUri.observe(viewLifecycleOwner) {
            it?.let { setUploadedState(it) } ?: setUploadState()
        }
    }

    private fun setUploadedState(audioUri: Uri) {
        setupAudioFileInfo(audioUri)

        binding.apply {
            selectAudioBtn.text = ContextCompat.getString(
                requireContext(), R.string.select_another_audio_btn_text
            )
            audioUploadState.text = resources.getString(R.string.uploaded_audio_text)
            generateBtn.isEnabled = true
        }
    }

    private fun setUploadState() {
        binding.apply {
            audioFileInfo.visibility = View.GONE

            selectAudioBtn.text = ContextCompat.getString(
                requireContext(), R.string.select_audio_btn_text
            )
            audioUploadState.text = resources.getString(R.string.upload_audio_text)
            generateBtn.isEnabled = false
        }
    }

    private fun setupAudioFileInfo(audioUri: Uri) {
        binding.audioFileInfo.apply {
            setAudioData(audioUri)
            visibility = View.VISIBLE
        }
    }
}
