package app.visualmusic.common.bottomsheet

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import app.visualmusic.R
import app.visualmusic.common.util.setBottomSheetHeight
import app.visualmusic.core.model.reference.ReferenceItem
import app.visualmusic.databinding.BottomSheetListBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.divider.MaterialDividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class AbstractChoiceBottomSheetList(
    private val title: String,
    private val isSingleChoice: Boolean,
    private val allItems: List<ReferenceItem>,
    private val initialCheckedItemIds: Set<Long>
) : BottomSheetDialogFragment() {
    protected lateinit var binding: BottomSheetListBinding

    protected val viewModel: ChoiceViewModel by viewModels()

    private lateinit var listAdapter: CheckedReferenceItemsListAdapter

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
        setupCancelBtnListeners()
        setupObserver()

        viewModel.addItems(initialCheckedItemIds)
    }

    private fun setupRecycleView() {
        listAdapter = CheckedReferenceItemsListAdapter(isSingleChoice, ::onItemCheckedChange)

        binding.recycleView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter

            addItemDecoration(
                MaterialDividerItemDecoration(context, LinearLayoutManager.VERTICAL).apply {
                    isLastItemDecorated = false
                }
            )
        }
    }

    protected abstract fun onItemCheckedChange(itemId: Long, isChecked: Boolean)

    private fun setupCancelBtnListeners() {
        binding.apply {
            cancelBtn.setOnClickListener {
                dismiss()
            }
        }
    }

    private fun setupObserver() {
        viewModel.selectedItemIds.observe(this) {
            val isEmpty = it.isEmpty()
            setBtnsVisible(!isEmpty, !isEmpty, isEmpty)
            submitCheckedReferenceItemsList(it)
        }
    }

    private fun setBtnsVisible(
        isResetBtnVisible: Boolean,
        isApplyBtnVisible: Boolean,
        isCancelBtnVisible: Boolean
    ) {
        binding.apply {
            resetBtn.visibility = if (isResetBtnVisible) View.VISIBLE else View.GONE
            applyBtn.visibility = if (isApplyBtnVisible) View.VISIBLE else View.GONE
            cancelBtn.visibility = if (isCancelBtnVisible) View.VISIBLE else View.INVISIBLE
        }
    }

    protected fun submitCheckedReferenceItemsList(checkedItemIds: Set<Long>) {
        val checkedReferenceItems = mutableListOf<CheckedReferenceItem>()

        checkedReferenceItems.addAll(allItems.map { item ->
            val isChecked = checkedItemIds.contains(item.id)
            CheckedReferenceItem(item, isChecked)
        })

        listAdapter.submitList(checkedReferenceItems)
    }
}