package app.visualmusic.common.util

import android.app.Activity
import android.app.Dialog
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.children
import com.google.android.material.bottomsheet.BottomSheetBehavior

fun ViewGroup.setEnabledDeep(enabled: Boolean) {
    this.isEnabled = enabled

    this.children.forEach { child ->
        child.isEnabled = enabled

        if (child is ViewGroup) {
            child.setEnabledDeep(enabled)
        }
    }
}

fun setBottomSheetHeight(dialog: Dialog, activity: Activity, heightPercent: Float) {
    val maxHeight = (getScreenHeightPx(activity) * heightPercent).toInt()

    val bottomSheet =
        (dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)) as FrameLayout

    bottomSheet.apply {
        layoutParams.height = maxHeight
        requestLayout()
    }

    BottomSheetBehavior.from(bottomSheet).apply {
        state = BottomSheetBehavior.STATE_EXPANDED
        skipCollapsed = true
    }
}

fun View.setDebouncedClickListener(delayMillis: Long = 500L, onClick: () -> Unit) {
    var isClickable = true

    setOnClickListener {
        if (!isClickable) return@setOnClickListener
        isClickable = false
        onClick()
        postDelayed({ isClickable = true }, delayMillis)
    }
}