package com.example.setrep.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import com.example.setrep.R
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.setrep.navigation.Screen
import com.example.setrep.ui.theme.SetRepTheme

@OptIn(ExperimentalMaterial3Api::class)
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
                    navController.navigate(Screen.Workout.route) {
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreenScaffold(
    pageContent: @Composable (innerPadding: PaddingValues) -> Unit = {}
) {
    Scaffold(
        topBar = {
            Card(
                shape = RectangleShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(152.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(stringResource(id = R.string.welcome),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.displayMedium)
                }
            }
        },
        content = { innerPadding ->
            pageContent(innerPadding)
        }
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