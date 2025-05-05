package app.visualmusic.common.util

import android.app.Activity
import android.content.res.Resources
import android.os.Build
import android.view.WindowMetrics
import androidx.annotation.RequiresApi

private fun getWindowMetrics(activity: Activity): WindowMetrics {
    return activity.windowManager.currentWindowMetrics
}

fun getWidthPx(activity: Activity): Int = getWindowMetrics(activity).bounds.width()

fun getScreenHeightPx(activity: Activity): Int = getWindowMetrics(activity).bounds.height()

fun convertDpToPx(dp: Int): Int {
    return (dp * Resources.getSystem().displayMetrics.density).toInt()
}

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun getDensity(activity: Activity): Float = getWindowMetrics(activity).density