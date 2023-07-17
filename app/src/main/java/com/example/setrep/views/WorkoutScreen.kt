package com.example.setrep.views

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.setrep.R
import com.example.setrep.model.Exercise
import com.example.setrep.navigation.Screen
import com.example.setrep.ui.components.MainScaffold

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
    MainScaffold(
        navController = navController
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
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
}

@Composable
private fun WorkoutScreenContent(
    modifier: Modifier = Modifier,
    navigateToScreen: () -> Unit = {}
) {
    Column() {
        Text(text = stringResource(id = R.string.workout))

        Button(onClick = {
            navigateToScreen()
        }) {
            Text(text = stringResource(id = R.string.workout))
        }
    }


}