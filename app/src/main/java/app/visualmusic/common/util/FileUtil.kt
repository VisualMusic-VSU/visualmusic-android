package app.visualmusic.common.util

import android.content.Context
import android.media.MediaMetadataRetriever
import android.net.Uri

fun getFileName(context: Context, uri: Uri): String? {
    var fileName: String? = null

    context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
        if (cursor.moveToFirst()) {
            val nameIndex = cursor.getColumnIndex(android.provider.OpenableColumns.DISPLAY_NAME)

            if (nameIndex != -1) {
                val fullName = cursor.getString(nameIndex)
                fileName = fullName.substringBeforeLast('.', fullName)
            }
        }
    }

    return fileName
}

fun getFileSize(context: Context, uri: Uri): Long {
    var size: Long = 0

    context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
        if (cursor.moveToFirst()) {
            val sizeIndex = cursor.getColumnIndex(android.provider.OpenableColumns.SIZE)

            if (sizeIndex != -1) {
                size = cursor.getLong(sizeIndex)
            }
        }
    }
    return size
}

fun getAudioDurationInSec(context: Context, uri: Uri): Long {
    var duration: Long = 0
    try {
        val retriever = MediaMetadataRetriever()
        retriever.setDataSource(context, uri)

        val durationStr = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
        duration = durationStr?.toLongOrNull() ?: 0

        retriever.release()
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return duration / 1000
}