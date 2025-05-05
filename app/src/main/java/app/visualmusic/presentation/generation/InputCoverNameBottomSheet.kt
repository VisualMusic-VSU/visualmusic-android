package app.visualmusic.presentation.generation

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import app.visualmusic.R
import app.visualmusic.common.util.setDebouncedClickListener
import app.visualmusic.databinding.BottomSheetInputCoverNameBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InputCoverNameBottomSheet(
    private val fragmentRootView: View
) : BottomSheetDialogFragment() {
    companion object {
        val TAG: String = InputCoverNameBottomSheet::class.simpleName.toString()
    }

    private lateinit var binding: BottomSheetInputCoverNameBinding
    private val viewModel: GenerationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetInputCoverNameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBtnListeners()
        setupTextEditor()
    }

    fun setupBtnListeners() {
        binding.apply {
            goBackBtn.setDebouncedClickListener {
                dismiss()
            }
            binding.generateBtn.setDebouncedClickListener {
                viewModel.setCoverName(textEdit.text.toString()) //TODO Запрос на генерацию

                showSnackbar()
                viewModel.clearParams()

                dismiss()
            }
        }
    }

    private fun showSnackbar() {
        val snackbar = Snackbar.make(
            fragmentRootView,
            resources.getString(R.string.generation_start_snackbar_text),
            Snackbar.LENGTH_LONG
        )
        val snackbarView = snackbar.view

        val layoutParams = snackbarView.layoutParams
        layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
        (layoutParams as FrameLayout.LayoutParams).gravity =
            Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM

        snackbarView.layoutParams = layoutParams

        val textView =
            snackbarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER

        textView.setTextColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.md_theme_onTertiary
                )
            )
        )

        snackbarView.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                requireActivity(),
                R.color.md_theme_tertiary
            )
        )

        snackbarView.background = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = resources.getDimension(R.dimen.corner_12dp)
        }

        snackbar.show()
    }

    private fun setupTextEditor() {
        binding.apply {
            textEdit.addTextChangedListener(object : TextWatcher {
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
                    generateBtn.isEnabled = (charSequence?.length
                        ?: 0) >= resources.getInteger(R.integer.min_cover_name_length)
                }

                override fun afterTextChanged(editable: Editable?) {}
            })

            textEdit.postDelayed({
                textEdit.requestFocus()
                val imm =
                    context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(textEdit, InputMethodManager.SHOW_IMPLICIT)
            }, 400)
        }
    }
}