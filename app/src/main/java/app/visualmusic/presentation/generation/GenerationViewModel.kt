package app.visualmusic.presentation.generation

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import app.visualmusic.core.model.reference.Genre
import app.visualmusic.core.model.reference.Mood
import app.visualmusic.core.model.reference.Style
import app.visualmusic.core.service.CoverService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class GenerationViewModel @Inject constructor(
    private val coverService: CoverService
) : ViewModel() {
    private val _audioUri = MutableStateFlow<Uri?>(null)
    val audioUri = _audioUri.asLiveData()

    private var allGenres = listOf<Genre>()
    private var allMoods = listOf<Mood>()
    private var allStyles = listOf<Style>()

    private val _selectedGenres = MutableStateFlow<Set<Genre>>(setOf())
    val selectedGenres = _selectedGenres.asLiveData()

    private val _selectedMood = MutableStateFlow<Mood?>(null)
    val selectedMood = _selectedMood.asLiveData()

    private val _selectedStyle = MutableStateFlow<Style?>(null)
    val selectedStyle = _selectedStyle.asLiveData()

    private val _selectedText = MutableStateFlow<String?>(null)
    val selectedText = _selectedText.asLiveData()

    private var coverName: String? = null

    fun clearParams() {
        _audioUri.value = null
        coverName = null
    }

    // Audio
    fun getAudioUri() = _audioUri.value

    fun setAudioUri(audioUri: Uri) {
        _audioUri.value = audioUri
    }

    fun setCoverName(name: String) {
        coverName = name
    }

    // Genres
    fun getAllGenres(): List<Genre> {
        allGenres = coverService.getAllGenres()

        return allGenres
    }

    fun getSelectedGenresIds() = _selectedGenres.value.map { it.id }.toSet()

    fun setSelectedGenresFromIds(genreIds: Set<Long>) {
        _selectedGenres.value = allGenres.filter {
            genreIds.contains(it.id)
        }.toSet()
    }

    // Moods
    fun getAllMoods(): List<Mood> {
        allMoods = coverService.getAllMoods()

        return allMoods
    }

    fun getSelectedMoodId() = _selectedMood.value?.id

    fun setSelectedMoodFromId(moodId: Long?) {
        if (moodId != null) {
            _selectedMood.value = allMoods.filter {
                it.id == moodId
            }.toSet().first()
        } else {
            _selectedMood.value = null
        }
    }

    // Styles
    fun getAllStyles(): List<Style> {
        allStyles = coverService.getAllStyles()

        return allStyles
    }

    fun getSelectedStyleId() = _selectedStyle.value?.id

    fun setSelectedStyleFromId(styleId: Long?) {
        Log.d("Test", "setSelectedStyleFromId: $styleId")
        if(styleId != null) {
            _selectedStyle.value = allStyles.filter {
                it.id == styleId
            }.toSet().first()
        } else {
            _selectedStyle.value = null
        }
    }


    // Text
    fun setText(text: String) {
        _selectedText.value = text
    }

    fun getText() = _selectedText.value
}