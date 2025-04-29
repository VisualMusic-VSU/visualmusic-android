package app.visualmusic.common.util

import android.view.ViewGroup
import androidx.core.view.children

fun ViewGroup.setEnabledDeep(enabled: Boolean) {
    this.isEnabled = enabled

    this.children.forEach { child ->
        child.isEnabled = enabled

        if (child is ViewGroup) {
            child.setEnabledDeep(enabled)
        }
    }
}