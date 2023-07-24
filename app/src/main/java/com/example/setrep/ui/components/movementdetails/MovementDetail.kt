package com.example.setrep.ui.components.movementdetails

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MovementDetail(label: String, description: String, height: Dp) {
    Row(
        modifier = Modifier
            .padding(2.dp)
            .height(height)
    ) {
        Text(
            text = label,
            modifier = Modifier.weight(0.35f),
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            text = description,
            modifier = Modifier.weight(0.65f).verticalScroll(state = rememberScrollState()),
        )
    }
}

@Preview
@Composable
fun MovementDetailPreview() {
    MovementDetail("test","test",50.dp)
}