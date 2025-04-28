package app.visualmusic.common.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.visualmusic.databinding.FragmentCoverImageBinding

class CoverImageFragment(
    private val imageResId: Int
) : Fragment() {
    private lateinit var binding: FragmentCoverImageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentCoverImageBinding.inflate(layoutInflater, container, false)
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.root.setImageResource(imageResId)
    }
}