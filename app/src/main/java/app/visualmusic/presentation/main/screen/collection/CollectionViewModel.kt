package app.visualmusic.presentation.main.screen.collection

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
class CollectionViewModel @Inject constructor(
    private val coverService: CoverService
) : ViewModel() {

    private val _generatedCovers =
        MutableStateFlow<OperationResult<List<CoverGroupItemDto>>>(OperationResult.Loading)
    val generatedCovers = _generatedCovers.asLiveData()

    fun fetchGeneratedCovers() = _generatedCovers.emitInIO(viewModelScope) {
        coverService.getAllGeneratedCoverGroups()
    }
}