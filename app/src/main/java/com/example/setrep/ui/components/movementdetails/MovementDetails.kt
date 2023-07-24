package com.example.setrep.ui.components.movementdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.setrep.model.Movement

@Composable
fun MovementDetails(movement: MutableState<Movement>) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        val sizeOfLine = 20.dp
        MovementDetail(label = "Body Part Worked", description = movement.value.bodyPartWorked,sizeOfLine)
        MovementDetail(label = "Difficulty Level", description = movement.value.level,sizeOfLine)
        MovementDetail(label = "Exercise Type", description = movement.value.type,sizeOfLine)
        MovementDetail(label = "Equipment Needed", description = movement.value.equipment,sizeOfLine)
        MovementDetail(label = "Exercise Description", description = movement.value.description,200.dp)
    }
}

@Preview
@Composable
fun MovementDetailsPreview() {
    val movement =
        remember { mutableStateOf(Movement("test", "test", "test", "test", "test", "test")) }
    MovementDetails(movement = movement)
}