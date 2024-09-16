package com.kmcoding.pinmanager.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmcoding.pinmanager.domain.model.Pin
import com.kmcoding.pinmanager.domain.repository.PinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
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

        fun updatePinWithConflict(value: Pin?) {
            _pinWithConflict.update { value }
        }

        /**
         * Checks if entered PIN name is unique.
         * If yes, then PIN item is being saved into database.
         * If no, then user should decide if he wants to override PIN item in database.
         *
         * @param name PIN name, which should be unique
         */
        fun checkIfPinExistsInDatabase(name: String) {
            viewModelScope.launch {
                val pin = pinRepository.getPinByName(name = name).firstOrNull()
                if (pin == null) {
                    savePinToDatabase(name = name)
                } else {
                    // Entered PIN name is not unique, ask if user want to replace it in database
                    updatePinWithConflict(pin)
                }
            }
        }

        suspend fun savePinToDatabase(
            name: String,
            id: Int? = null,
        ) {
            val pin =
                if (id != null) {
                    // Replaces current PIN item in database with new one
                    Pin(name = name, code = generateUniquePinCode(), id = id)
                } else {
                    Pin(name = name, code = generateUniquePinCode())
                }
            pinRepository.insertPin(pin)
            _navigateBackChannel.send(true)
        }

        /**
         * Generates unique PIN code with 6 digits
         */
        private suspend fun generateUniquePinCode(): String {
            val pinCodeLength = 6
            var pinCode = ""

            // Generates PIN code till it's unique
            while (pinCode.isEmpty()) {
                repeat(pinCodeLength) {
                    val randomNumber = Random.nextInt(10)
                    pinCode = pinCode.plus(randomNumber)
                }

                val pin = pinRepository.getPinByCode(pinCode).firstOrNull()
                if (pin != null) {
                    // PIN code already exists in database, generates the new one
                    pinCode = ""
                }
            }

            return pinCode
        }
    }
