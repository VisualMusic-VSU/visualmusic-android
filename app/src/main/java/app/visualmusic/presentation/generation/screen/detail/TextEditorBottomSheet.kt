package app.visualmusic.presentation.generation.screen.detail

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import app.visualmusic.R
import app.visualmusic.common.util.setBottomSheetHeight
import app.visualmusic.databinding.BottomSheetTextEditorBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TextEditorBottomSheet : BottomSheetDialogFragment() {
    companion object {
        val TAG: String = TextEditorBottomSheet::class.simpleName.toString()
    }

    private lateinit var binding: BottomSheetTextEditorBinding

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
    }

    private fun setupBtnListeners() {
        binding.apply {
            cancelBtn.setOnClickListener {
                dismiss()
            }
            applyBtn.setOnClickListener {
                onApplyBtnClick()
            }
        }
    }


    private fun onApplyBtnClick() {
        Toast.makeText(context, "Apply Btn Clicked", Toast.LENGTH_SHORT).show()
        dismiss()
    }
}