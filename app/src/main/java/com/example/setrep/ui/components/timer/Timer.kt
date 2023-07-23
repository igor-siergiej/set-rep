package com.example.setrep.ui.components.timer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Timer(totalSeconds: Int) {
    val hours = totalSeconds / 3600;
    val minutes = (totalSeconds % 3600) / 60;
    val seconds = totalSeconds % 60;

    Card(
        modifier = Modifier
            .padding(0.dp, 10.dp)
            .height(50.dp).width(300.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Duration: " + String.format("%02d:%02d:%02d", hours, minutes, seconds)
            )
        }
    }
}

@Composable
@Preview
fun TimerPreview() {
    Timer(totalSeconds = 3843)
}