package app.visualmusic.presentation.cover

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import app.visualmusic.R
import app.visualmusic.common.fragment.CoverImageFragment

class CoverSliderAdapter(
    fragment: FragmentActivity
) : FragmentStateAdapter(fragment) {

    private val mockImageResIds = listOf<Int>(
        R.drawable.mock_cover_image_1,
        R.drawable.mock_cover_image_2,
        R.drawable.mock_cover_image_3
    )

    override fun createFragment(position: Int): Fragment {
        return CoverImageFragment(mockImageResIds[position])
    }

    override fun getItemCount(): Int = mockImageResIds.size
}