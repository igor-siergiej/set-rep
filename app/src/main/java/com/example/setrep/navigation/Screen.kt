package com.example.setrep.navigation

sealed class Screen(val route: String) {

    object Home : Screen("home")
    object Statistics : Screen("statistics")
    object Calendar : Screen("calendar")

    object Workout : Screen("workout")

}

val screens = listOf(
    Screen.Home,
    Screen.Statistics,
    Screen.Calendar
)