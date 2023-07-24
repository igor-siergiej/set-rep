package com.example.setrep.ui.components.searchbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.example.setrep.model.Movement

@Composable
fun SearchList(
    movementList: List<Movement>,
    text: MutableState<String>,
    selectedMovement: MutableState<Movement>,
    isSearchActive: MutableState<Boolean>,
    hasItemBeenClicked: MutableState<Boolean>
) {
    var filteredCountries: ArrayList<Movement>

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        val searchedText = text.value

        filteredCountries = if (searchedText.isEmpty()) (ArrayList()) else {

            val resultList = ArrayList<Movement>()
            for (exercise in movementList) {
                if (exercise.title.contains(searchedText)) {
                    resultList.add(exercise)
                }
            }
            resultList
        }

        items(filteredCountries) { filteredExercise ->
            MovementItem(
                movement = filteredExercise,
                onItemClick = { exercise ->
                    selectedMovement.value = exercise
                    text.value = exercise.title
                    isSearchActive.value = false
                    hasItemBeenClicked.value = true
                }
            )
        }
    }
}
