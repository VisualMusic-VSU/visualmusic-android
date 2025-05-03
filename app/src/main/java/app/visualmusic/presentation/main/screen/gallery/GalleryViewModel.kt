package app.visualmusic.presentation.main.screen.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import app.visualmusic.common.util.emitInIO
import app.visualmusic.core.model.cover.CoverGroupItemDto
import app.visualmusic.core.service.CoverService
import app.visualmusic.core.utils.OperationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val coverService: CoverService
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<OperationResult<List<CoverGroupItemDto>>>(OperationResult.Loading)
    val uiState = _uiState.asLiveData()

    fun fetchCovers() = _uiState.emitInIO(viewModelScope) {
        coverService.getAllCovers()
    }
}