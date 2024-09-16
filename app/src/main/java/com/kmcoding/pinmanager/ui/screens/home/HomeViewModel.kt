package com.kmcoding.pinmanager.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmcoding.pinmanager.domain.model.Pin
import com.kmcoding.pinmanager.domain.repository.PinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        private val pinRepository: PinRepository,
    ) : ViewModel() {
        private val _pinToDelete = MutableStateFlow<Pin?>(null)
        var pinToDelete = _pinToDelete.asStateFlow()

        private val _pins = MutableStateFlow<List<Pin>>(listOf())
        val pins =
            pinRepository.getAllPins().stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = _pins.value,
            )

        suspend fun deletePin(pin: Pin) {
            pinRepository.deletePin(pin)
            updatePinToDelete(null)
        }

        fun updatePinToDelete(pin: Pin?) {
            _pinToDelete.update { pin }
        }
    }
