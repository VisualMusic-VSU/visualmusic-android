package app.visualmusic.presentation.generation.screen.prompt

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import app.visualmusic.common.util.setDebouncedClickListener
import app.visualmusic.databinding.FragmentPromptGenerationBinding
import app.visualmusic.presentation.generation.GenerationViewModel
import app.visualmusic.presentation.generation.InputCoverNameBottomSheet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PromptGenerationScreen : Fragment() {
    private lateinit var binding: FragmentPromptGenerationBinding
    private val viewModel: GenerationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPromptGenerationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTextEditor()
        setupObservers()
        setupBtnListeners()
    }

    private fun setupTextEditor() {
        binding.apply {
            viewModel.getPrompt()?.let {
                promptTextEdit.setText(it)
                promptTextEdit.setSelection(it.length)
            }

            promptTextEdit.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    charSequence: CharSequence?, start: Int, count: Int, after: Int
                ) {
                }

                override fun onTextChanged(
                    charSequence: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    val text = charSequence?.toString()?.trim()
                    val result = if (text.isNullOrEmpty()) null else text
                    viewModel.setPrompt(result)
                }

                override fun afterTextChanged(editable: Editable?) {}
            })

            promptTextEdit.postDelayed({
                promptTextEdit.requestFocus()
                val imm =
                    context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(promptTextEdit, InputMethodManager.SHOW_IMPLICIT)
            }, 200)
        }
    }

    private fun setupObservers() {
        viewModel.prompt.observe(viewLifecycleOwner) {
            val isNotNullPrompt = it != null

            setCheckedStateGenerateBtn(isNotNullPrompt)

            if (!isNotNullPrompt) {
                binding.promptTextEdit.setText(null)
            }
        }
    }

    private fun setCheckedStateGenerateBtn(checked: Boolean) {
        binding.generateBtn.isEnabled = checked
    }

    private fun setupBtnListeners() {
        binding.generateBtn.setDebouncedClickListener {
            InputCoverNameBottomSheet(
                binding.root
            ).show(
                parentFragmentManager,
                InputCoverNameBottomSheet.TAG
            )
        }
    }
}