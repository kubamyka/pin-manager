package com.kmcoding.pinmanager.ui.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kmcoding.pinmanager.ui.content.PinManagerApp

@Composable
fun AddNewPinScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "add new pin")
    }
}

@Preview(showBackground = true)
@Composable
fun AddNewPinScreenPreview() {
    AddNewPinScreen()
}