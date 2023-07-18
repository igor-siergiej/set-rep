package com.example.setrep.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.setrep.datasource.ProfileViewModel
import com.example.setrep.ui.components.MainScaffold
import com.example.setrep.ui.components.StartScreenScaffold

@Composable
fun CalendarScreenTopLevel(
    navController: NavController,
    profileViewModel: ProfileViewModel
) {
    CalendarScreen(
        navController = navController,
        profileViewModel = profileViewModel
    )
}

@Composable
fun CalendarScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel
) {
    MainScaffold(
        navController = navController
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            CalendarScreenContent()
        }
    }
}

@Composable
private fun CalendarScreenContent() {
    Text(text = "Calendar Screen")
}