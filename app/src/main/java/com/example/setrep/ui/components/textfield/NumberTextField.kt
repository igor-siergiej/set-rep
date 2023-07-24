package com.example.setrep.ui.components.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun NumberTextField(textFieldValue: MutableState<String>, numOfDigits: Int) {
    val focusRequester = remember { FocusRequester() }

    TextField(
        value = textFieldValue.value,
        modifier = Modifier
            .focusRequester(focusRequester)
            .width(65.dp),
        onValueChange = {
            if (it.length <= numOfDigits) textFieldValue.value = it
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true
    )
}

@Composable
fun RepTextField(textFieldValue: MutableState<String>, index: Int) {
    Text(
        text = "Set $index",
        style = MaterialTheme.typography.labelLarge)
    Text(text = "Reps:")
    NumberTextField(textFieldValue = textFieldValue, numOfDigits = 2)
}

@Composable
fun WeightTextField(textFieldValue: MutableState<String>,) {
    Text(text = "Weight(kg):")
    NumberTextField(textFieldValue = textFieldValue, numOfDigits = 3)
}