package app.visualmusic.presentation.main.screen.gallery

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import app.visualmusic.R
import app.visualmusic.common.adapter.CoverGroupItemListAdapter
import app.visualmusic.common.bottomsheet.MultipleChoiceListBottomSheet
import app.visualmusic.common.bottomsheet.SingleChoiceListBottomSheet
import app.visualmusic.common.decoration.VerticalSpacingItemDecoration
import app.visualmusic.common.util.setDebouncedClickListener
import app.visualmusic.core.utils.OperationResult
import app.visualmusic.databinding.FragmentGalleryBinding
import app.visualmusic.presentation.main.screen.gallery.GalleryViewModel.Companion.DEFAULT_SORT_FILTER_VALUE
import com.google.android.material.chip.Chip
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

        setupFilterChips()
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

        viewModel.apply {
            selectedSort.observe(viewLifecycleOwner) {
                setCheckedChipState(binding.sortFilter, it != DEFAULT_SORT_FILTER_VALUE)
            }
            selectedGenres.observe(viewLifecycleOwner) {
                setCheckedChipState(binding.genresFilter, !it.isEmpty())
            }
            selectedMood.observe(viewLifecycleOwner) {
                setCheckedChipState(binding.moodFilter, it != null)
            }
            selectedStyle.observe(viewLifecycleOwner) {
                setCheckedChipState(binding.styleFilter, it != null)
            }
        }
    }

    private fun setCheckedChipState(chip: Chip, checked: Boolean) {
        val backgroundColor =
            if (checked) R.color.md_theme_secondaryContainer
            else R.color.md_theme_surfaceContainer

        val textColor =
            if (checked) R.color.md_theme_onSecondaryContainer
            else R.color.md_theme_onSurfaceVariant

        chip.apply {
            chipBackgroundColor = ColorStateList.valueOf(
                ContextCompat.getColor(context, backgroundColor)
            )
            closeIconTint = ColorStateList.valueOf(
                ContextCompat.getColor(context, textColor)
            )
            setTextColor(ContextCompat.getColor(context, textColor))
        }
    }

    private fun setupFilterChips() {
        binding.apply {
            sortFilter.setDebouncedClickListener {
                showSortFilterBottomSheetList()
            }
            genresFilter.setDebouncedClickListener {
                showGenresFilterBottomSheetList()
            }
            moodFilter.setDebouncedClickListener {
                showMoodFilterBottomSheetList()
            }
            styleFilter.setDebouncedClickListener {
                showStyleFilterBottomSheetList()
            }
        }
    }

    private fun showSortFilterBottomSheetList() {
        SingleChoiceListBottomSheet(
            title = resources.getString(R.string.sort_filter),
            allItems = viewModel.getAllSorts(),
            initialSelectedItem = viewModel.getSelectedSort(),
            onApply = viewModel::setSelectedSort
        ).show(childFragmentManager, SingleChoiceListBottomSheet.TAG)
    }

    private fun showGenresFilterBottomSheetList() {
        MultipleChoiceListBottomSheet(
            title = resources.getString(R.string.genres_filter),
            allItems = viewModel.getAllGenres(),
            initialSelectedItemIds = viewModel.getSelectedGenres(),
            onApply = viewModel::setSelectedGenres
        ).show(childFragmentManager, MultipleChoiceListBottomSheet.TAG)
    }

    private fun showMoodFilterBottomSheetList() {
        SingleChoiceListBottomSheet(
            title = resources.getString(R.string.mood_filter),
            allItems = viewModel.getAllMoods(),
            initialSelectedItem = viewModel.getSelectedMood(),
            onApply = viewModel::setSelectedMood
        ).show(childFragmentManager, SingleChoiceListBottomSheet.TAG)
    }

    private fun showStyleFilterBottomSheetList() {
        SingleChoiceListBottomSheet(
            title = resources.getString(R.string.style_filter),
            allItems = viewModel.getAllStyles(),
            initialSelectedItem = viewModel.getSelectedStyle(),
            onApply = viewModel::setSelectedStyle
        ).show(childFragmentManager, SingleChoiceListBottomSheet.TAG)
    }
}