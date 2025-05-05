package app.visualmusic.common.bottomsheet

import app.visualmusic.core.model.reference.ReferenceItem

data class CheckedReferenceItem(
    val item: ReferenceItem,
    val isChecked: Boolean
)
