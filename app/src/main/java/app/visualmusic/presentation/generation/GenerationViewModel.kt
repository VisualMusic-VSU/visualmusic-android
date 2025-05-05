package app.visualmusic.presentation.generation

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class GenerationViewModel @Inject constructor() : ViewModel() {
    private val _audioUri = MutableStateFlow<Uri?>(null)
    val audioUri = _audioUri.asLiveData()

    private var coverName: String? = null

    fun clearParams() {
        _audioUri.value = null
        coverName = null
    }

    fun getAudioUri() = _audioUri.value

    fun setAudioUri(audioUri: Uri) {
        _audioUri.value = audioUri
    }

    fun setCoverName(name: String) {
        coverName = name
    }
}