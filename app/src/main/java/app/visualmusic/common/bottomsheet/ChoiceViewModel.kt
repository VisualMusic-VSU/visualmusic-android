package app.visualmusic.common.bottomsheet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ChoiceViewModel @Inject constructor() : ViewModel() {
    private val _selectedItemIds = MutableStateFlow<Set<Long>>(setOf())
    val selectedItemIds = _selectedItemIds.asLiveData()

    fun clearSelectedItems() {
        _selectedItemIds.value = emptySet()
    }

    fun addItem(id: Long) {
        _selectedItemIds.value += id
    }

    fun addItems(ids: Set<Long>) {
        _selectedItemIds.value += ids
    }

    fun removeItem(id: Long) {
        _selectedItemIds.value -= id
    }

    fun getItemsIds(): Set<Long> = _selectedItemIds.value
}