package com.example.setrep

import android.os.Bundle
import android.util.Log
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.setrep.model.Exercise
import com.example.setrep.navigation.Screen
import com.example.setrep.ui.theme.SetRepTheme
import com.example.setrep.views.HomeScreenTopLevel
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
    // viewModels?
) {
    // The NavController is in a place where all
    // our composables can access it.
    val navController = rememberNavController()

    //testing word bank
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current
    val inputStream: InputStream = context.assets.open("exercisesDataSet.csv")
    var exercises: List<Exercise> = emptyList()

    LaunchedEffect(key1 = Unit) {
        coroutineScope.launch {
            exercises = readCsv(inputStream)
            /*for (exercise: Exercise in exercises) {
                Log.d("exercises", exercise.toString())
            }*/
        }
    }



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
        composable(Screen.Home.route) { HomeScreenTopLevel(navController) }
        composable(Screen.Workout.route) { WorkoutScreenTopLevel(navController, exercises) }

    }
}


fun readCsv(inputStream: InputStream): List<Exercise> {
    val reader = inputStream.bufferedReader()
    val header = reader.readLine()
    return reader.lineSequence()
        .filter { it.isNotBlank() }
        .map {
            val (title,description,type,bodyPartWorked,equipment,level) = it.split(',', ignoreCase = false, limit = 6)
            Exercise(title, description, type, bodyPartWorked, equipment, level)
        }.toList()
}

operator fun <T> List<T>.component6(): T = get(5)

fun main() {
    val aList = listOf("one", "two", "three", "four", "five", "six")
    val (_, _, _, _, _, s6) = aList // no compilation error
    println(s6) // prints "six"
}