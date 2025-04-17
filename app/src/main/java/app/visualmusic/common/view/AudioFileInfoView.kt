package app.visualmusic.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import app.visualmusic.R
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

    fun setAudioName(name: String) {
        binding.audioName.text = name
    }

    fun setAudioSize(size: String) {
        binding.audioSize.text = size
    }

    fun setAudioDuration(duration: String) {
        binding.audioDuration.text = duration
    }
}