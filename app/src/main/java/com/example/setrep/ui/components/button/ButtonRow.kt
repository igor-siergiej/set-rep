package com.example.setrep.ui.components.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ButtonRow(
    leftText: String,
    leftOnClick: () -> Unit,
    rightText: String,
    rightOnClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 20.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = leftOnClick,
            modifier = Modifier.padding(10.dp,0.dp).weight(0.5f)
        ) {
            Text(text = leftText)
        }
        OutlinedButton(
            onClick = rightOnClick,
            modifier = Modifier.padding(10.dp,0.dp).weight(0.5f)
        ) {
            Text(text = rightText)
        }
    }
}

@Preview
@Composable
fun ButtonRowPreview() {
    ButtonRow("test", {}, "test", {})
}