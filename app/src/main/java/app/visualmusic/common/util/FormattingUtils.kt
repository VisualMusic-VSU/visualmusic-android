package app.visualmusic.common.util

import android.content.Context
import android.text.format.DateUtils
import app.visualmusic.R


fun formatDuration(context: Context, durationInSeconds: Long): String {
    val timeUnit = context.getString(
        when {
            durationInSeconds >= 3600 -> R.string.time_unit_hours
            durationInSeconds >= 60 -> R.string.time_unit_minutes
            else -> R.string.time_unit_seconds
        }
    )

    val elapsedTime = DateUtils.formatElapsedTime(durationInSeconds)

    return "$elapsedTime $timeUnit"
}