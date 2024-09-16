package com.kmcoding.pinmanager.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kmcoding.pinmanager.data.source.FakeDataSource.fakePins
import com.kmcoding.pinmanager.domain.model.Pin

@Composable
fun PinList(
    modifier: Modifier = Modifier,
    pins: List<Pin> = listOf(),
    deletePin: (Pin) -> Unit,
) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        item {
            Spacer(modifier = Modifier)
        }
        items(key = { pin ->
            pin.id
        }, items = pins) {
            PinItem(pin = it, deletePin = deletePin)
        }
        item {
            Box(modifier = Modifier.padding(bottom = 72.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PinListPreview() {
    PinList(pins = fakePins, deletePin = {})
}
