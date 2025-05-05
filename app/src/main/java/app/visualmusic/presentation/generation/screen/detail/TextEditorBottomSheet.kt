package app.visualmusic.presentation.generation.screen.detail

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.activityViewModels
import app.visualmusic.R
import app.visualmusic.common.util.setBottomSheetHeight
import app.visualmusic.databinding.BottomSheetTextEditorBinding
import app.visualmusic.presentation.generation.GenerationViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TextEditorBottomSheet : BottomSheetDialogFragment() {
    companion object {
        val TAG: String = TextEditorBottomSheet::class.simpleName.toString()
    }

    private lateinit var binding: BottomSheetTextEditorBinding
    private val viewModel: GenerationViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog

        dialog.setOnShowListener {
            setBottomSheetHeight(
                dialog,
                requireActivity(),
                resources.getFloat(R.dimen.text_editor_bottom_sheet_max_height)
            )
        }

        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetTextEditorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupBtnListeners()
        setupTextEditor()
    }

    private fun setupBtnListeners() {
        binding.apply {
            cancelBtn.setOnClickListener {
                dismiss()
            }
            applyBtn.setOnClickListener {
                viewModel.setText(textEdit.text?.toString() ?: "")
                dismiss()
            }
        }
    }

    private fun setupTextEditor() {
        binding.apply {
            textEdit.postDelayed({
                textEdit.requestFocus()
                val imm =
                    context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(textEdit, InputMethodManager.SHOW_IMPLICIT)
            }, 200)

            viewModel.getText()?.let {
                textEdit.setText(it)
                textEdit.setSelection(it.length)
            }
        }
    }
}