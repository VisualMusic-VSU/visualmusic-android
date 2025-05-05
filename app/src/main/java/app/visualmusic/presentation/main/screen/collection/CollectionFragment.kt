package app.visualmusic.presentation.main.screen.collection

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
import app.visualmusic.databinding.FragmentCollectionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CollectionFragment : Fragment() {
    private lateinit var binding: FragmentCollectionBinding
    private val viewModel: CollectionViewModel by viewModels()

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

        setupRecycleView()
        setupObservers()

        viewModel.fetchGeneratedCovers()
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

    private fun setupObservers() {
        viewModel.generatedCovers.observe(viewLifecycleOwner) {
            when (it) {
                is OperationResult.Success -> {
                    (binding.recycleView.adapter as CoverGroupItemListAdapter).submitList(it.data)
                }

                else -> Unit
            }
        }
    }
}