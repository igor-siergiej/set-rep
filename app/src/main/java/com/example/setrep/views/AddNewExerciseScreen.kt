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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
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
import com.example.setrep.navigation.Screen
import com.example.setrep.ui.components.EmptyScaffold
import com.example.setrep.ui.components.EmptyTopBar
import kotlinx.coroutines.delay
import java.util.Date

@Composable
fun AddNewExerciseScreenTopLevel(
    navController: NavController,
    exercises: List<Exercise>,
    workoutViewModel: WorkoutViewModel,
    time: Int
) {
    AddNewExerciseScreen(
        navController = navController,
        exercises = exercises,
        workoutViewModel = workoutViewModel,
        time = time
    )
}

@Composable
fun AddNewExerciseScreen(
    navController: NavController,
    exercises: List<Exercise>,
    workoutViewModel: WorkoutViewModel,
    time: Int
) {
    EmptyScaffold {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            AddNewExerciseScreenContent(
                exercises = exercises,
                navController = navController,
                workoutViewModel = workoutViewModel,
                time = time
            )
        }
    }
}

@Composable
private fun AddNewExerciseScreenContent(
    exercises: List<Exercise>,
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

    val active = remember { mutableStateOf(false) }

    val selectedExercise = remember { mutableStateOf(Exercise()) }

    val focusRequester = remember { FocusRequester() }

    val sets = remember { mutableStateListOf<MutableState<String>>() }

    Column(
        modifier = Modifier.fillMaxHeight()
    ) {
        EmptyTopBar(stringResource(id = R.string.search_exercise))

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .height(700.dp)
                .padding(8.dp)

        ) {
            SearchView(
                text = textState,
                active = active,
                exercises = exercises,
                selectedExercise = selectedExercise
            )

            Text(text = "Body Part Worked: " + selectedExercise.value.bodyPartWorked)
            Text(text = "Difficulty Level: " + selectedExercise.value.level)
            Text(text = "Exercise Type: " + selectedExercise.value.type)
            Text(text = "Exercise Description: " + selectedExercise.value.description)
            Text(text = "Equipment Needed: " + selectedExercise.value.equipment)

            LazyColumn() {
                items(sets) { item ->
                    TextField(
                        value = item.value,
                        modifier = Modifier.focusRequester(focusRequester),
                        onValueChange = { item.value = it },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                    )

                    if (sets[sets.size - 1] == item) {
                        LaunchedEffect(Unit) {
                            focusRequester.requestFocus()
                        }
                    }
                }

                item {
                    Button(onClick = {
                        val reps = mutableStateOf("")
                        sets.add(reps)
                    }
                    ) {
                        Text(text = "+")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    workoutViewModel.addExercise(selectedExercise.value)
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
    active: MutableState<Boolean>,
    exercises: List<Exercise>,
    selectedExercise: MutableState<Exercise>
) {

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
            exerciseList = exercises,
            text = text,
            selectedExercise = selectedExercise,
            isSearchActive = active
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchViewPreview() {
    val textState = remember { mutableStateOf("test") }
    val exercise = remember { mutableStateOf(Exercise()) }
    val active = remember { mutableStateOf(false) }
    val exercises = emptyList<Exercise>()
    SearchView(textState, active, exercises, exercise)
}

@Composable
fun ExerciseItem(exercise: Exercise, onItemClick: (Exercise) -> Unit) {
    Row(
        modifier = Modifier
            .clickable(onClick = { onItemClick(exercise) })
            .height(57.dp)
            .fillMaxWidth()
            .padding(PaddingValues(8.dp, 16.dp))
    ) {
        Text(text = exercise.title, fontSize = 18.sp)
    }
}

@Composable
private fun ExerciseList(
    exerciseList: List<Exercise>,
    text: MutableState<String>,
    selectedExercise: MutableState<Exercise>,
    isSearchActive: MutableState<Boolean>
) {
    var filteredCountries: ArrayList<Exercise>

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        val searchedText = text.value

        filteredCountries = if (searchedText.isEmpty()) (ArrayList()) else {

            val resultList = ArrayList<Exercise>()
            for (exercise in exerciseList) {
                if (exercise.title.contains(searchedText)) {
                    resultList.add(exercise)
                }
            }
            resultList
        }

        items(filteredCountries) { filteredExercise ->
            ExerciseItem(
                exercise = filteredExercise,
                onItemClick = { exercise ->
                    selectedExercise.value = exercise
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
        exercises = ArrayList(),
        workoutViewModel,
        0
    )
}


@Preview
@Composable
private fun ExerciseItemPreview() {
    ExerciseItem(Exercise("test", "test", "test", "test", "test", "test")) {}
}
