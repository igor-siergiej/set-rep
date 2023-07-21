package com.example.setrep.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import com.example.setrep.ui.components.MainScaffold
import com.example.setrep.ui.components.StartScreenScaffold
import kotlinx.coroutines.delay
import java.util.Date
import kotlin.time.Duration.Companion.seconds

@Composable
fun WorkoutScreenTopLevel(
    navController: NavController,
    workoutViewModel: WorkoutViewModel,
    time: Int
) {
    WorkoutScreen(
        navController = navController,
        workoutViewModel = workoutViewModel,
        time = time
    )
}

@Composable
fun WorkoutScreen(
    navController: NavController,
    workoutViewModel: WorkoutViewModel,
    time: Int
) {
    EmptyScaffold {
        WorkoutScreenContent(
            modifier = Modifier.padding(8.dp),
            navController = navController,
            selectedExercises = workoutViewModel.workout.value.exercises,
            time = time
        )
    }
}


@Composable
private fun WorkoutScreenContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    selectedExercises: ArrayList<Exercise>,
    time: Int
) {

    var ticks by remember { mutableStateOf(time) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000 - Date().time % 1000)
            ticks++
        }
    }

    Column(
        modifier = Modifier.fillMaxHeight()
    ) {
        EmptyTopBar(stringResource(id = R.string.workout))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier
                    .padding(0.dp, 10.dp)
                    .size(100.dp)
            ) {
                Text(text = ticks.toString())
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(selectedExercises) {
                Card(
                    shape = RectangleShape,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(75.dp),
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = it.title,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        Spacer(modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                navController.navigate("${Screen.NewExercise.route}/${ticks}") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }) {
                Text(text = stringResource(id = R.string.new_exercise))
            }
            OutlinedButton(onClick = {

            }) {
                Text(text = stringResource(id = R.string.finish_workout))
            }
        }
    }
}

@Composable
@Preview
fun WorkoutScreenPreview() {
    var navController = rememberNavController()
    var workoutViewModel: WorkoutViewModel = viewModel()
    WorkoutScreen(
        navController = navController,
        workoutViewModel = workoutViewModel,
        time = 0
    )
}