package com.example.setrep.ui.components.movementdetails

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MovementDetail(label: String, description: String) {
    Row(
        modifier = Modifier.padding(2.dp)
    ) {
        Text(
            text = label,
            modifier = Modifier.weight(0.35f),
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            text = description,
            modifier = Modifier.weight(0.65f)
        )
    }
}

@Preview
@Composable
fun MovementDetailPreview() {
    MovementDetail("test","test")
}