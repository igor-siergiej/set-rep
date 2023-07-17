package com.example.setrep.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.setrep.R
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
        navController = navController,
        titleText = stringResource(id = R.string.app_name)
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            WorkoutScreenContent(
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
private fun WorkoutScreenContent(
    modifier: Modifier = Modifier
) {
    Text(text = stringResource(id = R.string.workout))
}