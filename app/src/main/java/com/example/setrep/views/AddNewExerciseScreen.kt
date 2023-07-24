package com.example.setrep.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.setrep.NavigateToScreenWithParam
import com.example.setrep.R
import com.example.setrep.datasource.WorkoutViewModel
import com.example.setrep.model.Exercise
import com.example.setrep.model.Movement
import com.example.setrep.navigation.Screen
import com.example.setrep.ui.components.button.ButtonRow
import com.example.setrep.ui.components.movementdetails.MovementDetail
import com.example.setrep.ui.components.movementdetails.MovementDetails
import com.example.setrep.ui.components.scaffold.EmptyScaffold
import com.example.setrep.ui.components.searchbar.SearchTextField
import com.example.setrep.ui.components.textfield.RepTextField
import com.example.setrep.ui.components.topbar.EmptyTopBar
import kotlinx.coroutines.delay
import java.util.Date

@Composable
fun AddNewExerciseScreenTopLevel(
    navController: NavController,
    movements: List<Movement>,
    workoutViewModel: WorkoutViewModel,
    time: Int
) {
    AddNewExerciseScreen(
        navController = navController,
        movements = movements,
        workoutViewModel = workoutViewModel,
        time = time
    )
}

@Composable
fun AddNewExerciseScreen(
    navController: NavController,
    movements: List<Movement>,
    workoutViewModel: WorkoutViewModel,
    time: Int
) {
    EmptyScaffold {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            AddNewExerciseScreenContent(
                movements = movements,
                navController = navController,
                workoutViewModel = workoutViewModel,
                time = time
            )
        }
    }
}

@Composable
private fun AddNewExerciseScreenContent(
    movements: List<Movement>,
    navController: NavController,
    workoutViewModel: WorkoutViewModel,
    time: Int
) {
    var ticks by remember { mutableStateOf(time) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000 - Date().time % 1000)
            ticks++
        }
    }

    val textState = remember { mutableStateOf("") }
    val selectedMovement = remember { mutableStateOf(Movement()) }
    val sets = remember { mutableStateListOf<MutableState<String>>() }

    val addSetButtonActive = remember { mutableStateOf(true) }
    val minusSetButtonActive = remember { mutableStateOf(true) }

    minusSetButtonActive.value = sets.size > 0
    addSetButtonActive.value = sets.size < 12

    Column(
        modifier = Modifier.fillMaxHeight()
    ) {
        EmptyTopBar(stringResource(id = R.string.search_exercise))

        Column(
        ) {
            SearchTextField(
                text = textState,
                movements = movements,
                selectedMovement = selectedMovement
            )

            LazyRow(
                modifier = Modifier
                    .padding(8.dp)
                    .height(200.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically

            ) {
                items(sets) { item ->

                    val addRepButtonActive = remember { mutableStateOf(true) }
                    val minusRepButtonActive = remember { mutableStateOf(true) }

                    if (item.value != "") {
                        minusRepButtonActive.value = item.value.toInt() > 0
                        addRepButtonActive.value = item.value.toInt() < 99
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp, 0.dp)
                    ) {
                        Button(
                            enabled = addRepButtonActive.value,
                            onClick = {
                                item.value = (item.value.toInt() + 1).toString()
                            }
                        ) {
                            Text(text = "+")
                        }

                        RepTextField(item = item, index = sets.indexOf(item) + 1)

                        Button(
                            enabled = minusRepButtonActive.value,
                            onClick = {
                                item.value = (item.value.toInt() - 1).toString()
                            }
                        ) {
                            Text(text = "-")
                        }
                    }
                }
                item {
                    Column {
                        Button(
                            enabled = addSetButtonActive.value,
                            onClick = {
                                val reps = mutableStateOf("0")
                                sets.add(reps)
                            }
                        ) {
                            Text(text = "+")
                        }

                        Button(
                            enabled = minusSetButtonActive.value,
                            onClick = {
                                sets.remove(sets[sets.size - 1])
                            }
                        ) {
                            Text(text = "-")
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        MovementDetails(
            movement = selectedMovement
        )

        ButtonRow(
            leftText = stringResource(id = R.string.new_exercise),
            leftOnClick = {
                val setsIntArray = ArrayList<Int>()
                for (stringSet in sets) {
                    setsIntArray.add(stringSet.value.toInt())
                }
                workoutViewModel.addExercise(
                    Exercise(selectedMovement.value, setsIntArray)
                )
                NavigateToScreenWithParam(navController,Screen.Workout,ticks)
            },
            rightText = stringResource(id = R.string.go_back),
            rightOnClick = {
                NavigateToScreenWithParam(navController,Screen.Workout,ticks)
            }
        )
    }
}

@Preview
@Composable
fun AddNewExerciseScreenPreview() {
    val navController = rememberNavController()
    val workoutViewModel: WorkoutViewModel = viewModel()
    AddNewExerciseScreen(
        navController = navController,
        movements = ArrayList(),
        workoutViewModel,
        0
    )
}

