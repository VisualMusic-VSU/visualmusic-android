package app.visualmusic.presentation.main.screen.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.visualmusic.databinding.FragmentCollectionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CollectionFragment : Fragment() {
    private lateinit var binding: FragmentCollectionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}