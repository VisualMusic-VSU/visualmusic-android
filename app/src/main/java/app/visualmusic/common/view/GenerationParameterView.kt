package app.visualmusic.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import app.visualmusic.R
import app.visualmusic.databinding.ViewGenerationParameterBinding

class GenerationParameterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {
    private val binding: ViewGenerationParameterBinding =
        ViewGenerationParameterBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)

        attrs?.let {
            val a =
                context.theme.obtainStyledAttributes(it, R.styleable.GenerationParameterView, 0, 0)

            val parameterName = a.getString(
                R.styleable.GenerationParameterView_parameterName
            )

            binding.parameterName.text = parameterName

            a.recycle()
        }
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        if (child == binding.root) {
            super.addView(child, index, params)
        } else {
            binding.slotContainer.addView(child, params)
        }
    }
}