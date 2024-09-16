package com.kmcoding.pinmanager

import com.kmcoding.pinmanager.data.source.FakeDataSource.fakePins
import com.kmcoding.pinmanager.fake.FakePinRepositoryImpl
import com.kmcoding.pinmanager.ui.add.AddNewPinViewModel
import com.kmcoding.pinmanager.util.MainDispatcherRule
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AddNewPinViewModelTest {
    private lateinit var viewModel: AddNewPinViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        viewModel = AddNewPinViewModel(pinRepository = FakePinRepositoryImpl())
    }

    @Test
    fun `verify that PIN with not unique name will force conflict`() =
        runTest {
            backgroundScope.launch(mainDispatcherRule.testDispatcher) {
                viewModel.checkIfPinExistsInDatabase(fakePins[0].name)
                viewModel.pinWithConflict.collect()
            }
            assertEquals(fakePins[0], viewModel.pinWithConflict.value)
        }

    @Test
    fun `verify that PIN with unique name will not force conflict`() =
        runTest {
            backgroundScope.launch(mainDispatcherRule.testDispatcher) {
                viewModel.checkIfPinExistsInDatabase("UniqueName")
                viewModel.pinWithConflict.collect()
            }
            assertEquals(null, viewModel.pinWithConflict.value)
        }

    @Test
    fun `verify that not unique code will not be used in PIN object`() =
        runTest {
            backgroundScope.launch(mainDispatcherRule.testDispatcher) {
                val result = viewModel.isPinCodeUnique(fakePins[0].code)
                assertFalse(result)
            }
        }

    @Test
    fun `verify that unique code will be used in PIN object`() =
        runTest {
            backgroundScope.launch(mainDispatcherRule.testDispatcher) {
                val result = viewModel.isPinCodeUnique("555555")
                assertTrue(result)
            }
        }

    @Test
    fun `verify that generated code has appropriate length`() =
        runTest {
            val result = viewModel.generatePinCode()
            assertEquals(viewModel.pinCodeLength, result.length)
        }

    @Test
    fun `verify that generated code has only digits`() =
        runTest {
            val result = viewModel.generatePinCode()
            var hasOnlyDigits = true
            result.forEach { char ->
                if (!char.isDigit()) {
                    hasOnlyDigits = false
                    return@forEach
                }
            }
            assertTrue(hasOnlyDigits)
        }
}
