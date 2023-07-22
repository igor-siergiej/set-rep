package com.example.setrep.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SearchBar
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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.setrep.R
import com.example.setrep.datasource.WorkoutViewModel
import com.example.setrep.model.Exercise
import com.example.setrep.model.Movement
import com.example.setrep.navigation.Screen
import com.example.setrep.ui.components.EmptyScaffold
import com.example.setrep.ui.components.EmptyTopBar
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

    Column(
        modifier = Modifier.fillMaxHeight()
    ) {
        EmptyTopBar(stringResource(id = R.string.search_exercise))

        Column(
        ) {
            SearchView(
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
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp, 0.dp)
                    ) {
                        Button(onClick = {
                            item.value = (item.value.toInt() + 1).toString()
                        }
                        ) {
                            Text(text = "+")
                        }

                        RepSelector(item = item, index = sets.indexOf(item) + 1)

                        Button(onClick = {
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
                            onClick = {
                            val reps = mutableStateOf("0")
                            sets.add(reps)
                        }
                        ) {
                            Text(text = "+")
                        }

                        Button(
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

        ExerciseDetails(
            movement = selectedMovement
        )



        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    val setsIntArray = ArrayList<Int>()
                    for (stringSet in sets) {
                        setsIntArray.add(stringSet.value.toInt())
                    }
                    workoutViewModel.addExercise(
                        Exercise(selectedMovement.value, setsIntArray)
                    )
                    // TODO REMOVE THIS CODE DUPLICATION, HOIST STATE
                    navController.navigate("${Screen.Workout.route}/${ticks}") {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }) {
                Text(text = stringResource(id = R.string.new_exercise))
            }

            OutlinedButton(
                onClick = {
                    navController.navigate("${Screen.Workout.route}/${ticks}") {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }) {
                Text(text = stringResource(id = R.string.go_back))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(
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
        ExerciseList(
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
    SearchView(textState, movements, movement)
}

@Composable
fun ExerciseItem(movement: Movement, onItemClick: (Movement) -> Unit) {
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

@Composable
private fun ExerciseList(
    movementList: List<Movement>,
    text: MutableState<String>,
    selectedMovement: MutableState<Movement>,
    isSearchActive: MutableState<Boolean>
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
            ExerciseItem(
                movement = filteredExercise,
                onItemClick = { exercise ->
                    selectedMovement.value = exercise
                    text.value = exercise.title
                    isSearchActive.value = false
                }
            )
        }
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


@Preview
@Composable
private fun ExerciseItemPreview() {
    ExerciseItem(Movement("test", "test", "test", "test", "test", "test")) {}
}

@Composable
fun ExerciseDetail(lable: String, description: String) {
    Row(
        modifier = Modifier.padding(2.dp)
    ) {
        Text(
            text = lable,
            modifier = Modifier.weight(0.35f),
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            text = description,
            modifier = Modifier.weight(0.65f)
        )
    }
}

@Composable
fun ExerciseDetails(movement: MutableState<Movement>) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        ExerciseDetail(lable = "Body Part Worked", description = movement.value.bodyPartWorked)
        ExerciseDetail(lable = "Difficulty Level", description = movement.value.level)
        ExerciseDetail(lable = "Exercise Type", description = movement.value.type)
        ExerciseDetail(lable = "Exercise Description", description = movement.value.description)
        ExerciseDetail(lable = "Equipment Needed", description = movement.value.equipment)
    }
}

@Preview
@Composable
fun ExerciseDetailsPreview() {
    val movement =
        remember { mutableStateOf(Movement("test", "test", "test", "test", "test", "test")) }
    ExerciseDetails(movement = movement)
}

@Composable
fun RepSelector(item: MutableState<String>, index: Int) {
    val focusRequester = remember { FocusRequester() }

    Text(text = "Set $index")

    TextField(
        value = item.value,
        modifier = Modifier
            .focusRequester(focusRequester)
            .width(60.dp),
        onValueChange = {
            if (it.length <= 2) item.value = it
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        supportingText = {
            Text(
                text = "${item.value.length} / 2",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
            )
        },
    )
}