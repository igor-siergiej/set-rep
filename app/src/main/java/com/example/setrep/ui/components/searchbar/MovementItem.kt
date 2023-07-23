package com.example.setrep.ui.components.searchbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.setrep.model.Movement

@Composable
fun MovementItem(movement: Movement, onItemClick: (Movement) -> Unit) {
    Row(
        modifier = Modifier
            .clickable(onClick = { onItemClick(movement) })
            .height(57.dp)
            .fillMaxWidth()
            .padding(PaddingValues(8.dp, 16.dp))
    ) {
        Text(text = movement.title, fontSize = 18.sp)
    }
}

@Preview
@Composable
fun MovementItemPreview() {
    MovementItem(movement = Movement("test","test","test","test","test","test"), onItemClick = {})
}