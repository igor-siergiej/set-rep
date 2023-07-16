package com.example.setrep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.setrep.navigation.Screen
import com.example.setrep.ui.theme.SetRepTheme
import com.example.setrep.views.StartScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SetRepTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BuildNavigationGraph()
                }
            }
        }
    }
}

@Composable
private fun BuildNavigationGraph(
    // viewModels?
) {
    // The NavController is in a place where all
    // our composables can access it.
    val navController = rememberNavController()

    //testing word bank
    val coroutineScope = rememberCoroutineScope()

    // initial setting of test data goes here

    // Each NavController is associated with a NavHost.
    // This links the NavController with a navigation graph.
    // As we navigate between composables the content of
    // the NavHost is automatically recomposed.
    // Each composable destination in the graph is associated with a route.

    var startingDestination = Screen.Home.route

    NavHost(
        navController = navController,
        startDestination = startingDestination
    ) {
        composable(Screen.Home.route) { StartScreen(navController) }
    }
}