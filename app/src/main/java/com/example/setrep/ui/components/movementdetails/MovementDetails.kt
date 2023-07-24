package com.example.setrep.ui.components.movementdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.setrep.model.Movement

@Composable
fun MovementDetails(movement: MutableState<Movement>) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        MovementDetail(label = "Body Part Worked", description = movement.value.bodyPartWorked)
        MovementDetail(label = "Difficulty Level", description = movement.value.level)
        MovementDetail(label = "Exercise Type", description = movement.value.type)
        MovementDetail(label = "Exercise Description", description = movement.value.description)
        MovementDetail(label = "Equipment Needed", description = movement.value.equipment)
    }
}

@Preview
@Composable
fun MovementDetailsPreview() {
    val movement =
        remember { mutableStateOf(Movement("test", "test", "test", "test", "test", "test")) }
    MovementDetails(movement = movement)
}