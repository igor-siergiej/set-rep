package com.example.setrep.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.setrep.R
import com.example.setrep.model.Exercise
import com.example.setrep.ui.components.EmptyScaffold
import com.example.setrep.ui.components.EmptyTopBar
@Composable
fun AddNewExerciseScreenTopLevel(
    navController: NavController,
    exercises: List<Exercise>
) {
    AddNewExerciseScreen(
        navController = navController,
        exercises = exercises
    )
}

@Composable
fun AddNewExerciseScreen(
    navController: NavController,
    exercises: List<Exercise>
) {
    EmptyScaffold(
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            AddNewExerciseScreenContent(
                modifier = Modifier.padding(8.dp),
                exercises = exercises
            )
        }
    }
}

@Composable
private fun AddNewExerciseScreenContent(
    modifier: Modifier = Modifier,
    exercises: List<Exercise>
) {




    val textState = remember { mutableStateOf("") }
    val active = remember { mutableStateOf(false) }
    var selectedExercise = remember{ mutableStateOf(Exercise()) }

    Column {

        EmptyTopBar(stringResource(id = R.string.search_exercise))

        SearchView(
            text = textState,
            active = active,
            exercises = exercises,
            selectedExercise = selectedExercise
        )

        Text(text = selectedExercise.value.title)


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {

            }) {
                Text(text = stringResource(id = R.string.new_exercise))
            }
            OutlinedButton(onClick = {

            }) {
                Text(text = stringResource(id = R.string.finish_workout))
            }
        }
    }

    /*Column() {
        for (exercise: Exercise in exercises) {
            Text(text = exercise.toString())
        }
    }*/
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(
    text: MutableState<String>,
    active: MutableState<Boolean>,
    exercises: List<Exercise>,
    selectedExercise: MutableState<Exercise>) {

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
    SearchView(textState,active,exercises,exercise)
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

        filteredCountries = if (searchedText.isEmpty()) ( ArrayList() ) else {

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
    var navController = rememberNavController()
    AddNewExerciseScreen(navController = navController, exercises = ArrayList())
}


@Preview
@Composable
private fun ExerciseItemPreview() {
    ExerciseItem(Exercise("test", "test", "test", "test", "test", "test"), {})
}
