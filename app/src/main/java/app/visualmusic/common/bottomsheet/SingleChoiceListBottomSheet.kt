package app.visualmusic.common.bottomsheet

class SingleChoiceListBottomSheet(
    val title: String
) : AbstractBottomSheetList(title, true) {
    companion object {
        val TAG: String = SingleChoiceListBottomSheet::class.simpleName.toString()
    }

    override fun onApplyBtnClick() {
        dismiss()
    }
}