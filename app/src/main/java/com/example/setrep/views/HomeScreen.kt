package com.example.setrep.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.setrep.datasource.ProfileViewModel
import com.example.setrep.ui.components.scaffold.MainScaffold
import com.example.setrep.ui.components.topbar.MainTopBar
import com.example.setrep.ui.theme.SetRepTheme

@Composable
fun HomeScreenTopLevel(
    navController: NavController,
    profileViewModel: ProfileViewModel
) {
    HomeScreen(
        navController = navController,
        profileViewModel = profileViewModel
    )
}

@Composable
fun HomeScreen(
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
            HomeScreenContent(
                profileName = profileViewModel.profile.getName()
            )
        }
    }
}

@Composable
private fun HomeScreenContent(
    profileName: String
) {
    Column() {
        MainTopBar(heading = "Welcome $profileName!", subHeading = "Ready to Workout?")
    }
}

@Preview
@Composable
private fun HomeScreenContentPreview() {
    SetRepTheme() {
        HomeScreenContent("user")
    }
}

