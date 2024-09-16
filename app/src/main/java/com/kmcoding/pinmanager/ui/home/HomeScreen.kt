package com.kmcoding.pinmanager.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kmcoding.pinmanager.R
import com.kmcoding.pinmanager.ui.util.PinAlertDialog
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val pins by viewModel.pins.collectAsStateWithLifecycle()
    val pinToDelete by viewModel.pinToDelete.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = modifier.fillMaxSize()) {
        if (pins.isEmpty()) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text(text = stringResource(id = R.string.empty_pins))
            }
        } else {
            PinList(pins = pins, deletePin = { viewModel.updatePinToDelete(it) }, modifier = Modifier.padding(horizontal = 16.dp))

            val pinToDeleteState = pinToDelete
            if (pinToDeleteState != null) {
                PinAlertDialog(
                    icon = Icons.Default.Delete,
                    iconContentDescription = stringResource(id = R.string.delete),
                    dialogTitle = stringResource(id = R.string.delete),
                    dialogText =
                        stringResource(
                            id = R.string.dialog_message_delete_pin,
                            pinToDeleteState.name,
                        ),
                    confirmationButtonText = stringResource(id = R.string.delete),
                    dismissButtonText = stringResource(id = R.string.cancel),
                    onConfirmation = {
                        coroutineScope.launch {
                            viewModel.deletePin(pinToDeleteState)
                        }
                    },
                    onDismissRequest = {
                        viewModel.updatePinToDelete(null)
                    },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
