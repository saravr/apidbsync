package com.sandymist.apidbsync

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FruitViewModel @Inject constructor(
    private val fruitRepository: FruitRepository
): ViewModel() {

    private val _fruits = MutableStateFlow<List<Fruit>>(listOf())
    val fruits: StateFlow<List<Fruit>>
        get() = _fruits

    fun getColors() = viewModelScope.launch {
        fruitRepository.getFruits().collect {
            _fruits.value = it
        }
    }
}