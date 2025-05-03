package app.visualmusic.presentation.main.screen.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import app.visualmusic.R
import app.visualmusic.common.adapter.CoverGroupItemListAdapter
import app.visualmusic.common.decoration.VerticalSpacingItemDecoration
import app.visualmusic.core.utils.OperationResult
import app.visualmusic.databinding.FragmentGalleryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : Fragment() {
    private lateinit var binding: FragmentGalleryBinding
    private val viewModel: GalleryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycleView()
        setupObserver()

        viewModel.fetchCovers()
    }

    private fun setupRecycleView() {
        activity?.let { fragmentActivity ->
            binding.recycleView.apply {
                layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                adapter = CoverGroupItemListAdapter(fragmentActivity, ::onClickCoverGroup)

                addItemDecoration(
                    VerticalSpacingItemDecoration(
                        resources.getDimension(R.dimen.margin_10dp).toInt()
                    )
                )
            }
        }
    }

    private fun onClickCoverGroup(groupId: Long) {
        Log.d("GalleryFragment", "" + groupId)
        return
    }

    private fun setupObserver() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is OperationResult.Success -> {
                    (binding.recycleView.adapter as CoverGroupItemListAdapter).submitList(it.data)
                }

                else -> Unit
            }
        }
    }
}