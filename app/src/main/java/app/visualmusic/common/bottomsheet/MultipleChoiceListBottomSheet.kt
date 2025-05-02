package app.visualmusic.common.bottomsheet

import android.os.Bundle
import android.view.View

class MultipleChoiceListBottomSheet(
    val title: String
) : AbstractBottomSheetList(title, false) {
    companion object {
        val TAG: String = MultipleChoiceListBottomSheet::class.simpleName.toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onApplyBtnClick() {
        dismiss()
    }
}