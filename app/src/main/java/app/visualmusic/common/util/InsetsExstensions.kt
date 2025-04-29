package app.visualmusic.common.util

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding

fun Activity.edgeToEdge(handling: () -> Unit) {
    WindowCompat.setDecorFitsSystemWindows(window, false)

    window.apply {
        navigationBarColor = Color.TRANSPARENT
        statusBarColor = Color.TRANSPARENT
    }

    handling()
}


infix fun View.paddingBy(insetType: Int) {
    val oldPaddingLeft = paddingLeft
    val oldPaddingTop = paddingTop
    val oldPaddingRight = paddingRight
    val oldPaddingBottom = paddingBottom

    ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
        val inset = insets.getInsets(insetType)

        view.updatePadding(
            left = oldPaddingLeft + inset.left,
            top = oldPaddingTop + inset.top,
            right = oldPaddingRight + inset.right,
            bottom = oldPaddingBottom + inset.bottom
        )

        insets
    }
}

infix fun View.marginTo(insetType: Int) {
    val oldMarginLeft = marginLeft
    val oldMarginTop = marginTop
    val oldMarginRight = marginRight
    val oldMarginBottom = marginBottom

    ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
        val inset = insets.getInsets(insetType)

        view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            leftMargin = oldMarginLeft + inset.left
            topMargin = oldMarginTop + inset.top
            rightMargin = oldMarginRight + inset.right
            bottomMargin = oldMarginBottom + inset.bottom
        }

        insets
    }
}