package app.visualmusic.common.util

import android.app.Activity
import android.os.Build
import android.view.WindowMetrics
import androidx.annotation.RequiresApi

private fun getWindowMetrics(activity: Activity): WindowMetrics {
    return activity.windowManager.currentWindowMetrics
}

fun getWidthPx(activity: Activity): Int = getWindowMetrics(activity).bounds.width()

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun getDensity(activity: Activity): Float = getWindowMetrics(activity).density