package app.visualmusic.common.view

import android.content.Context
import android.net.Uri
import android.text.format.Formatter.formatFileSize
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import app.visualmusic.R
import app.visualmusic.common.util.formatDuration
import app.visualmusic.common.util.getAudioDurationInSec
import app.visualmusic.common.util.getFileName
import app.visualmusic.common.util.getFileSize
import app.visualmusic.databinding.ViewAudioFileInfoBinding

class AudioFileInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {
    private val binding: ViewAudioFileInfoBinding =
        ViewAudioFileInfoBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)

        attrs?.let {
            val a = context.theme.obtainStyledAttributes(it, R.styleable.AudioFileInfoView, 0, 0)

            val audioNameTextStyle = a.getResourceId(
                R.styleable.AudioFileInfoView_audioNameTextAppearance,
                R.style.TextAppearance_VisualMusic_TitleSmall
            )

            val audioSizeTextStyle = a.getResourceId(
                R.styleable.AudioFileInfoView_audioSizeTextAppearance,
                R.style.TextAppearance_VisualMusic_BodySmall
            )

            val audioDurationTextStyle = a.getResourceId(
                R.styleable.AudioFileInfoView_audioDurationTextAppearance,
                R.style.TextAppearance_VisualMusic_BodySmall
            )

            with(binding) {
                audioName.setTextAppearance(audioNameTextStyle)
                audioSize.setTextAppearance(audioSizeTextStyle)
                audioDuration.setTextAppearance(audioDurationTextStyle)
            }

            a.recycle()
        }
    }

    fun setFileNotChooseState() {
        setAudioName(resources.getString(R.string.file_not_choose_audio_name))
        setAudioSize(0)
        setAudioDuration(0)
    }

    fun setAudioData(audioUri: Uri) {
        setAudioName(
            getFileName(context, audioUri)
                ?: resources.getString(R.string.file_not_choose_audio_name)
        )
        setAudioSize(getFileSize(context, audioUri))
        setAudioDuration(getAudioDurationInSec(context, audioUri))
    }

    private fun setAudioName(name: String) {
        binding.audioName.text = name
    }

    private fun setAudioSize(sizeInBytes: Long) {
        binding.audioSize.text = formatFileSize(context, sizeInBytes)
    }

    private fun setAudioDuration(durationInSeconds: Long) {
        binding.audioDuration.text = formatDuration(context, durationInSeconds)
    }
}