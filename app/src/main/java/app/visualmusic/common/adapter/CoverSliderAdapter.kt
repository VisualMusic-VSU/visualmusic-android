package app.visualmusic.common.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import app.visualmusic.common.fragment.CoverImageFragment

class CoverSliderAdapter(
    fragment: FragmentActivity,
    private val imageUrls: List<String>
) : FragmentStateAdapter(fragment) {

    override fun createFragment(position: Int): Fragment {
        return CoverImageFragment(imageUrls[position].toInt())
    }

    override fun getItemCount(): Int = imageUrls.size
}