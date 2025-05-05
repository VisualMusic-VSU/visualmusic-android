package app.visualmusic.common.bottomsheet

import android.os.Bundle
import android.view.View
import app.visualmusic.core.model.reference.ReferenceItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class SingleChoiceListBottomSheet(
    val title: String,
    allItems: List<ReferenceItem>,
    initialSelectedItem: Long?,
    private val onApply: (Long?) -> Unit
) : AbstractChoiceBottomSheetList(
    title,
    true,
    allItems,
    initialSelectedItem?.let { setOf(it) } ?: setOf()
) {

    companion object {
        val TAG: String = SingleChoiceListBottomSheet::class.simpleName.toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            resetBtn.setOnClickListener {
                dismiss()
                onApply(null)
            }
            applyBtn.setOnClickListener {
                dismiss()
                onApply(viewModel.getItemsIds().first())
            }
        }
    }

    override fun onItemCheckedChange(itemId: Long, isChecked: Boolean) {
        viewModel.clearSelectedItems()

        if (isChecked) {
            viewModel.addItem(itemId)
        }
    }
}