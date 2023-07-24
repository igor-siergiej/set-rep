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
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.setrep.datasource.ProfileViewModel
import com.example.setrep.datasource.WorkoutViewModel
import com.example.setrep.model.Movement
import com.example.setrep.navigation.Screen
import com.example.setrep.ui.theme.SetRepTheme
import com.example.setrep.views.AddNewExerciseScreenTopLevel
import com.example.setrep.views.CalendarScreenTopLevel
import com.example.setrep.views.HomeScreenTopLevel
import com.example.setrep.views.ProfileScreenTopLevel
import com.example.setrep.views.StartScreenTopLevel
import com.example.setrep.views.StatisticsScreenTopLevel
import com.example.setrep.views.WorkoutScreenTopLevel
import kotlinx.coroutines.launch
import java.io.InputStream


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
    profileViewModel: ProfileViewModel = viewModel(),
    workoutViewModel: WorkoutViewModel = viewModel()
) {
    // The NavController is in a place where all
    // our composables can access it.
    val navController = rememberNavController()

    //testing word bank
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current
    val inputStream: InputStream = context.assets.open("exercisesDataSet.csv")
    var movements: List<Movement> = emptyList()

    LaunchedEffect(key1 = Unit) {
        coroutineScope.launch {
            movements = readCsv(inputStream)
        }
    }


    // Each NavController is associated with a NavHost.
    // This links the NavController with a navigation graph.
    // As we navigate between composables the content of
    // the NavHost is automatically recomposed.
    // Each composable destination in the graph is associated with a route.

    profileViewModel.deleteProfile()
    var startingDestination = Screen.Start.route
    if (profileViewModel.doesFileExist()) {
        startingDestination = Screen.Home.route
        profileViewModel.loadProfile()
    }

    NavHost(
        navController = navController,
        startDestination = startingDestination
    ) {
        composable(Screen.Home.route) { HomeScreenTopLevel(navController, profileViewModel) }

        composable(
            route = "${Screen.Workout.route}/{time}",
            arguments = listOf(
                navArgument("time") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            WorkoutScreenTopLevel(
                navController = navController,
                workoutViewModel = workoutViewModel,
                time = backStackEntry.arguments?.getInt("time")!!
            )
        }

        composable(
            route = "${Screen.NewExercise.route}/{time}",
            arguments = listOf(
                navArgument("time") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            AddNewExerciseScreenTopLevel(
                navController = navController,
                movements = movements,
                workoutViewModel = workoutViewModel,
                time = backStackEntry.arguments?.getInt("time")!!
            )
        }

        composable(Screen.Start.route) { StartScreenTopLevel(navController, profileViewModel) }

        composable(Screen.Calendar.route) {
            CalendarScreenTopLevel(
                navController,
                profileViewModel
            )
        }
        composable(Screen.Statistics.route) {
            StatisticsScreenTopLevel(
                navController,
                profileViewModel
            )
        }
        composable(Screen.Profile.route) { ProfileScreenTopLevel(navController, profileViewModel) }
    }
}


fun readCsv(inputStream: InputStream): List<Movement> {
    val reader = inputStream.bufferedReader()
    val header = reader.readLine()
    return reader.lineSequence()
        .filter { it.isNotBlank() }
        .map {
            val (title, description, type, bodyPartWorked, equipment, level) = it.split(
                ']',
                ignoreCase = false,
                limit = 6
            )
            Movement(title, description, type, bodyPartWorked, equipment, level)
        }.toList()
}

operator fun <T> List<T>.component6(): T = get(5)

fun main() {
    val aList = listOf("one", "two", "three", "four", "five", "six")
    val (_, _, _, _, _, s6) = aList // no compilation error
    println(s6) // prints "six"
}

fun NavigateToScreenWithParam(navController: NavController,screen: Screen, ticks: Any) {
    navController.navigate("${screen}/${ticks}") {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

