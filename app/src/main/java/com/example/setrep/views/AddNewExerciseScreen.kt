package com.example.setrep.views

import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.setrep.R
import com.example.setrep.model.Exercise
import com.example.setrep.ui.components.MainScaffold

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
    MainScaffold(
        navController = navController
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
    val textState = remember { mutableStateOf(TextFieldValue("test")) }


    Column {
        Text(text = stringResource(id = R.string.new_exercise))
        SearchView(state = textState)
        ExerciseList(
            exerciseList = exercises,
            modifier = Modifier.padding(8.dp),
            state = textState
        )
    }

    /*Column() {
        for (exercise: Exercise in exercises) {
            Text(text = exercise.toString())
        }
    }*/
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(state: MutableState<TextFieldValue>) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        modifier = Modifier
            .fillMaxWidth(),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value =
                            TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape // The TextFiled has rounded corners top left and right by default
    )
}

@Preview(showBackground = true)
@Composable
fun SearchViewPreview() {
    val textState = remember { mutableStateOf(TextFieldValue("test")) }
    SearchView(textState)
}

@Composable
fun ExerciseItem(exercise: Exercise, onItemClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .clickable(onClick = { onItemClick(exercise.title) })
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
    modifier: Modifier = Modifier,
    state: MutableState<TextFieldValue>
) {
    var exerciseArrayList = exerciseList as ArrayList<Exercise>
    var filteredCountries: ArrayList<Exercise>

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        val searchedText = state.value.text

        filteredCountries = if (searchedText.isEmpty()) ( exerciseArrayList ) else {

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
                onItemClick = { selectedExercise ->
                    /* Add code later */
                }
            )
        }
    }
}


@Preview
@Composable
private fun ExerciseItemPreview() {
    ExerciseItem(Exercise("test", "test", "test", "test", "test", "test"), {})
}
