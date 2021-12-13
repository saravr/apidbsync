package com.sandymist.apidbsync

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class APIDBSyncViewModel @Inject constructor(
    private val apiDbSyncRepository: APIDBSyncRepository
): ViewModel() {

    private val _colors = MutableStateFlow<List<String>>(listOf())
    val colors: StateFlow<List<String>>
        get() = _colors

    fun getColors() = viewModelScope.launch {
        _colors.value = apiDbSyncRepository.getColors()
    }

}