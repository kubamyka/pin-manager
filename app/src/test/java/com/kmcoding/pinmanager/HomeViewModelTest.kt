package com.kmcoding.pinmanager

import com.kmcoding.pinmanager.data.source.FakeDataSource.fakePins
import com.kmcoding.pinmanager.fake.FakePinRepositoryImpl
import com.kmcoding.pinmanager.ui.screens.home.HomeViewModel
import com.kmcoding.pinmanager.util.MainDispatcherRule
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {
    private lateinit var viewModel: HomeViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        viewModel = HomeViewModel(pinRepository = FakePinRepositoryImpl())
    }

    @Test
    fun `verify pins list after load`() =
        runTest {
            backgroundScope.launch(mainDispatcherRule.testDispatcher) {
                viewModel.pins.collect()
            }
            assertEquals(fakePins, viewModel.pins.value)
        }
}
