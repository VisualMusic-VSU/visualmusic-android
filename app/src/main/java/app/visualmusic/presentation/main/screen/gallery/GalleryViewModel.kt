package app.visualmusic.presentation.main.screen.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import app.visualmusic.common.util.emitInIO
import app.visualmusic.core.model.cover.CoverGroupItemDto
import app.visualmusic.core.model.reference.Genre
import app.visualmusic.core.model.reference.Sort
import app.visualmusic.core.service.CoverService
import app.visualmusic.core.utils.OperationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val coverService: CoverService
) : ViewModel() {
    companion object {
        const val DEFAULT_SORT_FILTER_VALUE = 1L
    }

    private val _uiState =
        MutableStateFlow<OperationResult<List<CoverGroupItemDto>>>(OperationResult.Loading)
    val uiState = _uiState.asLiveData()

    private val _selectedSort = MutableStateFlow<Long>(DEFAULT_SORT_FILTER_VALUE)
    val selectedSort = _selectedSort.asLiveData()

    private val _selectedGenres = MutableStateFlow<Set<Long>>(setOf())
    val selectedGenres = _selectedGenres.asLiveData()

    private val _selectedMood = MutableStateFlow<Long?>(null)
    val selectedMood = _selectedMood.asLiveData()

    private val _selectedStyle = MutableStateFlow<Long?>(null)
    val selectedStyle = _selectedStyle.asLiveData()

    fun fetchCovers() = _uiState.emitInIO(viewModelScope) {
        coverService.getAllCovers()
    }

    fun getAllSorts(): List<Sort> = coverService.getAllSorts()

    fun setSelectedSort(sort: Long?) {
        _selectedSort.value = sort ?: DEFAULT_SORT_FILTER_VALUE
    }

    fun getSelectedSort() = _selectedSort.value

    fun getAllGenres(): List<Genre> = coverService.getAllGenres()

    fun getSelectedGenres() = _selectedGenres.value

    fun setSelectedGenres(genres: Set<Long>) {
        _selectedGenres.value = genres
    }

    fun getAllMoods() = coverService.getAllMoods()

    fun getSelectedMood() = _selectedMood.value

    fun setSelectedMood(mood: Long?) {
        _selectedMood.value = mood
    }

    fun getAllStyles() = coverService.getAllStyles()

    fun getSelectedStyle() = _selectedMood.value

    fun setSelectedStyle(style: Long?) {
        _selectedStyle.value = style
    }
}