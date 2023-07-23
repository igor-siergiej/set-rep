package com.example.setrep.ui.components.searchbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.setrep.model.Movement

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    text: MutableState<String>,
    movements: List<Movement>,
    selectedMovement: MutableState<Movement>
) {
    val active = remember { mutableStateOf(false) }
    SearchBar(
        query = text.value,
        onQueryChange = { value ->
            text.value = value
        },
        onSearch = {
            active.value = false
        },
        active = active.value,
        onActiveChange = {
            active.value = it
        },
        modifier = Modifier
            .fillMaxWidth(),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "Search",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        placeholder = {
            Text(text = "Search Exercise")
        },
        trailingIcon = {
            if (active.value) {
                IconButton(
                    onClick = {
                        if (text.value.isNotEmpty()) {
                            text.value = ""
                        } else {
                            active.value = false
                        }
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "Close",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        }
    ) {
        SearchList(
            movementList = movements,
            text = text,
            selectedMovement = selectedMovement,
            isSearchActive = active
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchViewPreview() {
    val textState = remember { mutableStateOf("test") }
    val movement = remember { mutableStateOf(Movement()) }
    val movements = emptyList<Movement>()
    SearchTextField(textState, movements, movement)
}