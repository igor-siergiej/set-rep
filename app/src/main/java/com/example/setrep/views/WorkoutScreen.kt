package com.example.setrep.views

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.setrep.R
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
    navController: NavController
) {
    WorkoutScreen(
        navController = navController
    )
}

@Composable
fun WorkoutScreen(
    navController: NavController
) {
    EmptyScaffold() {
        WorkoutScreenContent(
            modifier = Modifier.padding(8.dp),
            navigateToScreen = {
                navController.navigate(Screen.NewExercise.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}


@Composable
private fun WorkoutScreenContent(
    modifier: Modifier = Modifier,
    navigateToScreen: () -> Unit = {}
) {
    var ticks by remember { mutableStateOf(0) }
    LaunchedEffect(Unit) {
        while(true) {
            delay(1000 - Date().time % 1000)
            ticks++
        }
    }
    // TODO pass seconds passed to next screen to carry the timer on

    Column(
        modifier = Modifier.fillMaxHeight()
    ) {
        EmptyTopBar(stringResource(id = R.string.workout))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card (
                modifier = Modifier
                    .padding(0.dp, 10.dp)
                    .size(100.dp)
            ) {
                Text(text = ticks.toString())
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
                navigateToScreen()
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
fun Timer() {

}

@Composable
@Preview
fun WorkoutScreenPreview() {
    var navController = rememberNavController()
    WorkoutScreen(
        navController = navController
    )
}