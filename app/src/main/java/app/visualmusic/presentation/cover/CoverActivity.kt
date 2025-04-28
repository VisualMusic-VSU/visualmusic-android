package app.visualmusic.presentation.cover

import android.animation.ArgbEvaluator
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsCompat.Type.navigationBars
import androidx.core.view.WindowInsetsCompat.Type.statusBars
import app.visualmusic.R
import app.visualmusic.common.ui.edgeToEdge
import app.visualmusic.common.ui.paddingBy
import app.visualmusic.common.util.getDensity
import app.visualmusic.common.util.getWidthPx
import app.visualmusic.databinding.ActivityCoverBinding
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import kotlin.math.abs

class CoverActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoverBinding

    private val argbEvaluator = ArgbEvaluator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCoverBinding.inflate(layoutInflater)
        setupEdgeToEdge()
        setContentView(binding.root)

        setCoversSectionBackgroundColor()
        setupAppBarLayout()
        setupCoverSlider();
    }

    private fun setupEdgeToEdge() {
        edgeToEdge {
            binding.root paddingBy statusBars() + navigationBars()
        }
    }

    private fun setCoversSectionBackgroundColor() {
        val color = ContextCompat.getColor(this, R.color.neutral_color)

        binding.apply {
            collapsingToolbarLayout.setBackgroundColor(color)
            coverSettingsLayout.setBackgroundColor(color)
        }
    }

    private fun setupAppBarLayout() {
        binding.appBarLayout.addOnOffsetChangedListener(object : OnOffsetChangedListener {
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                val totalScrollRange = appBarLayout.getTotalScrollRange()
                val scrollFraction: Float = abs(verticalOffset.toFloat()) / totalScrollRange

                val context = this@CoverActivity
                val color = argbEvaluator.evaluate(
                    scrollFraction,
                    ContextCompat.getColor(context, R.color.transparent),
                    ContextCompat.getColor(context, R.color.md_theme_surfaceContainerLowest)
                ) as Int

                binding.apply {
                    offsetViewCover.setBackgroundColor(color)
                    offsetViewDragHandle.setBackgroundColor(color)
                }
            }
        })
    }

    private fun setupCoverSlider() {
        val coverSlider = binding.coverSlider

        coverSlider.adapter = CoverSliderAdapter(this)

        coverSlider.apply {
            offscreenPageLimit = 1
            clipToPadding = false
            clipChildren = false
        }

        val offset = calcOffsetForCoverSlider()

        coverSlider.setPageTransformer { page, position ->
            val scale = 0.85f + (1 - abs(position)) * 0.15f
            page.scaleY = scale

            val offset = position * -(2 * offset)
            page.translationX = offset
        }
    }

    @SuppressLint("NewApi")
    private fun calcOffsetForCoverSlider(): Int {
        val screenWidth = getWidthPx(this)
        val screenDensity = getDensity(this)

        return screenWidth / (screenDensity * 2.5).toInt()
    }
}