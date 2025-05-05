package app.visualmusic.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import app.visualmusic.R
import app.visualmusic.common.util.setDebouncedClickListener
import app.visualmusic.common.util.setEnabledDeep
import app.visualmusic.databinding.ViewGenerationSettingBinding

class GenerationSettingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {
    private val binding: ViewGenerationSettingBinding =
        ViewGenerationSettingBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)

        attrs?.let {
            val a =
                context.theme.obtainStyledAttributes(it, R.styleable.GenerationSettingView, 0, 0)

            val settingName = a.getString(
                R.styleable.GenerationSettingView_settingName
            )

            val btnText = a.getString(
                R.styleable.GenerationSettingView_btnText
            )

            with(binding) {
                settingsTitle.text = settingName
                settingsBtn.text = btnText
            }

            a.recycle()
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        setupCheckedChangeStatusChipClick()
    }

    private fun setupCheckedChangeStatusChipClick() {
        val statusChip = binding.statusChip

        statusChip.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                toggleElementsEnabledState(false)
                statusChip.text = context.getString(R.string.auto_setting_status)
            } else {
                toggleElementsEnabledState(true)
                statusChip.text = context.getString(R.string.not_auto_setting_status)
            }
        }
    }

    private fun toggleElementsEnabledState(isEnabled: Boolean) {
        binding.apply {
            settingsBtn.isEnabled = isEnabled
            slotContainer.setEnabledDeep(isEnabled)
        }
    }

    fun setIsAutoStatus(isAuto: Boolean) {
        val statusChip = binding.statusChip

        if(statusChip.isChecked != isAuto) {
            statusChip.performClick()
        }
    }

    fun onSettingBtnClick(action: () -> Unit) {
        binding.settingsBtn.setDebouncedClickListener {
            action()
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