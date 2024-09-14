package com.kmcoding.pinmanager.ui

import androidx.lifecycle.ViewModel
import com.kmcoding.pinmanager.domain.repository.PinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        private val pinRepository: PinRepository,
    ) : ViewModel()
