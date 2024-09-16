package com.kmcoding.pinmanager.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kmcoding.pinmanager.data.source.FakeDataSource.fakePins
import com.kmcoding.pinmanager.domain.model.Pin

@Composable
fun PinItem(
    modifier: Modifier = Modifier,
    pin: Pin,
    deletePin: (Pin) -> Unit,
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier =
            modifier
                .fillMaxWidth()
                .wrapContentHeight(align = Alignment.Top),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().background(color = MaterialTheme.colorScheme.onPrimary),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Icon(
                imageVector = Icons.Rounded.Lock,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(start = 16.dp).size(32.dp),
            )
            Column(modifier = modifier.padding(12.dp).weight(1f), horizontalAlignment = Alignment.Start) {
                Text(text = pin.name, style = MaterialTheme.typography.titleLarge)
                Text(
                    text = pin.code,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = null,
                tint = Color.Red,
                modifier = Modifier.padding(end = 16.dp).size(32.dp).clickable { deletePin(pin) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PinItemPreview() {
    PinItem(pin = fakePins[0], deletePin = {})
}
