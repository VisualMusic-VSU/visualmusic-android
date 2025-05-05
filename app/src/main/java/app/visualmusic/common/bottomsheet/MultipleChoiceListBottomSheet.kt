package app.visualmusic.common.bottomsheet

import android.os.Bundle
import android.view.View
import app.visualmusic.core.model.reference.ReferenceItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MultipleChoiceListBottomSheet(
    val title: String,
    allItems: List<ReferenceItem>,
    initialSelectedItemIds: Set<Long>,
    private val onApply: (Set<Long>) -> Unit
) : AbstractChoiceBottomSheetList(title, false, allItems, initialSelectedItemIds) {

    companion object {
        val TAG: String = MultipleChoiceListBottomSheet::class.simpleName.toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            resetBtn.setOnClickListener {
                dismiss()
                onApply(emptySet<Long>())
            }
            applyBtn.setOnClickListener {
                dismiss()
                onApply(viewModel.getItemsIds())
            }
        }
    }

    override fun onItemCheckedChange(itemId: Long, isChecked: Boolean) {
        if (isChecked) {
            viewModel.addItem(itemId)
        } else {
            viewModel.removeItem(itemId)
        }
    }
}