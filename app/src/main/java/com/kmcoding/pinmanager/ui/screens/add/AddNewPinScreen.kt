package com.kmcoding.pinmanager.ui.screens.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.kmcoding.pinmanager.R
import com.kmcoding.pinmanager.ui.PinAlertDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun AddNewPinScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddNewPinViewModel = hiltViewModel(),
) {
    val pinWithConflict by viewModel.pinWithConflict.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()
    val lifecycleOwner = LocalLifecycleOwner.current
    var text by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(lifecycleOwner.lifecycle) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(Dispatchers.Main.immediate) {
                viewModel.navigateBackChannel.collect { shouldNavigateBack ->
                    if (shouldNavigateBack) {
                        navigateBack()
                    }
                }
            }
        }
    }

    Column(
        modifier = modifier.fillMaxWidth().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text(text = stringResource(id = R.string.pin_name)) },
            singleLine = true,
            keyboardOptions =
                KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done,
                ),
            keyboardActions =
                KeyboardActions(
                    onDone = {
                        viewModel.checkIfPinExistsInDatabase(text)
                    },
                ),
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
        )

        Button(
            onClick = { viewModel.checkIfPinExistsInDatabase(text) },
            enabled = text.isNotEmpty(),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = stringResource(id = R.string.save))
        }

        val pinWithConflictState = pinWithConflict
        if (pinWithConflictState != null) {
            PinAlertDialog(
                icon = Icons.Default.Warning,
                iconContentDescription = stringResource(id = R.string.warning),
                dialogTitle = stringResource(id = R.string.warning),
                dialogText = stringResource(id = R.string.dialog_message_replace_pin),
                confirmationButtonText = stringResource(id = R.string.replace),
                dismissButtonText = stringResource(id = R.string.cancel),
                onConfirmation = {
                    coroutineScope.launch {
                        viewModel.savePinToDatabase(text, pinWithConflictState.id)
                    }
                },
                onDismissRequest = {
                    viewModel.updatePinWithConflict(null)
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddNewPinScreenPreview() {
    AddNewPinScreen(navigateBack = {})
}
