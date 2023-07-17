package com.example.setrep.navigation

sealed class Screen(val route: String) {

    object Home : Screen("home")
    object Statistics : Screen("statistics")
    object Calendar : Screen("calendar")

    object NewWorkout : Screen("newWorkout")

}

val screens = listOf(
    Screen.Home,
    Screen.Statistics,
    Screen.Calendar
)