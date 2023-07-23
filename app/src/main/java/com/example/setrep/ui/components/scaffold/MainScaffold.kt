package com.example.setrep.ui.components.scaffold

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.setrep.R
import com.example.setrep.navigation.Screen
import com.example.setrep.ui.components.navbar.NavigationBar
import com.example.setrep.ui.theme.SetRepTheme

@Composable
fun MainScaffold(
    navController: NavController,
    pageContent: @Composable (innerPadding: PaddingValues) -> Unit = {}
) {
    Scaffold(
        bottomBar = {
            NavigationBar(navController)
        },
        content = { innerPadding ->
            pageContent(innerPadding)
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    navController.navigate("${Screen.Workout.route}/0") {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(id = R.string.new_workout)
                )
                Text(text = stringResource(id = R.string.new_workout))
            }
        },
    )
}

@Preview
@Composable
private fun MainScaffoldPreview() {
    SetRepTheme() {
        val navController = rememberNavController()
        MainScaffold(navController = navController, pageContent = {})
    }
}