package com.example.setrep.navigation

sealed class Screen(val route: String) {

    object Home : Screen("home")

}