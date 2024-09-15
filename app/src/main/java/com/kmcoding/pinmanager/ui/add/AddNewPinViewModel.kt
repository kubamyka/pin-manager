package com.kmcoding.pinmanager.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmcoding.pinmanager.domain.Pin
import com.kmcoding.pinmanager.domain.repository.PinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class AddNewPinViewModel
    @Inject
    constructor(
        private val pinRepository: PinRepository,
    ) : ViewModel() {
        private val _pinWithConflict = MutableStateFlow<Pin?>(null)
        var pinWithConflict = _pinWithConflict.asStateFlow()
        private val _navigateBackChannel = Channel<Boolean>()
        var navigateBackChannel = _navigateBackChannel.receiveAsFlow()

        fun checkIfPinExistsInDatabase(name: String) {
            viewModelScope.launch {
                val pin = pinRepository.getPin(name = name).firstOrNull()
                if (pin != null) {
                    updatePinWithConflict(pin)
                } else {
                    savePinToDatabase(name = name)
                }
            }
        }

        private fun generatePinCode(): String {
            val pinCodeLength = 6
            var pinCode = ""
            repeat(pinCodeLength) {
                val randomNumber = Random.nextInt(10)
                pinCode = pinCode.plus(randomNumber)
            }
            return pinCode
        }

        suspend fun savePinToDatabase(
            name: String,
            id: Int? = null,
        ) {
            val pin =
                if (id != null) {
                    Pin(name = name, code = generatePinCode(), id = id)
                } else {
                    Pin(name = name, code = generatePinCode())
                }
            pinRepository.insertPin(pin)
            _navigateBackChannel.send(true)
        }

        fun updatePinWithConflict(value: Pin?) {
            _pinWithConflict.value = value
        }
    }
