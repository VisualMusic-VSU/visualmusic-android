package app.visualmusic.common.bottomsheet

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import app.visualmusic.R
import app.visualmusic.common.util.setBottomSheetHeight
import app.visualmusic.core.model.ReferenceItem
import app.visualmusic.databinding.BottomSheetListBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.divider.MaterialDividerItemDecoration

abstract class AbstractBottomSheetList(
    private val title: String,
    private val isSingleChoice: Boolean
) : BottomSheetDialogFragment() {
    protected lateinit var binding: BottomSheetListBinding

    private var pendingItems: List<ReferenceItem>? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog

        dialog.setOnShowListener {
            setBottomSheetHeight(
                dialog,
                requireActivity(),
                resources.getFloat(R.dimen.list_bottom_sheet_max_height)
            )
        }

        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.title.text = title

        setupRecycleView()
        setupBtnListeners()

        pendingItems?.let {
            (binding.recycleView.adapter as ReferenceItemsListAdapter)
                .submitList(it)
        }
    }

    private fun setupRecycleView() {
        binding.recycleView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ReferenceItemsListAdapter(isSingleChoice)

            addItemDecoration(
                MaterialDividerItemDecoration(context, LinearLayoutManager.VERTICAL).apply {
                    isLastItemDecorated = false
                }
            )
        }
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

    abstract fun onApplyBtnClick()

    fun mockItems(items: List<ReferenceItem>) {
        pendingItems = items
    }
}