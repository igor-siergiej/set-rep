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
    movements: List<Movement>,
    text: MutableState<String>,
    selectedMovement: MutableState<Movement>,
    isSearchActive: MutableState<Boolean>,
    hasItemBeenClicked: MutableState<Boolean>
) {

    val filteredMovements: ArrayList<Movement> = if (text.value.isEmpty()) (ArrayList()) else (
            ArrayList(movements.sortedWith(compareBy<Movement> {
                findSimilarity(
                    text.value,
                    it.title
                )
            }.reversed()))
            )


    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(filteredMovements) { filteredExercise ->
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

fun getLevenshteinDistance(X: String, Y: String): Int {
    val m = X.length
    val n = Y.length
    val T = Array(m + 1) { IntArray(n + 1) }
    for (i in 1..m) {
        T[i][0] = i
    }
    for (j in 1..n) {
        T[0][j] = j
    }
    var cost: Int
    for (i in 1..m) {
        for (j in 1..n) {
            cost = if (X[i - 1] == Y[j - 1]) 0 else 1
            T[i][j] = Integer.min(
                Integer.min(T[i - 1][j] + 1, T[i][j - 1] + 1),
                T[i - 1][j - 1] + cost
            )
        }
    }
    return T[m][n]
}

fun findSimilarity(x: String?, y: String?): Double {
    require(!(x == null || y == null)) { "Strings should not be null" }

    val maxLength = Integer.max(x.length, y.length)
    return if (maxLength > 0) {
        (maxLength * 1.0 - getLevenshteinDistance(x, y)) / maxLength * 1.0
    } else 1.0
}