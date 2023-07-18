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
fun StatisticsScreenTopLevel(
    navController: NavController,
    profileViewModel: ProfileViewModel
) {
    StatisticsScreen(
        navController = navController,
        profileViewModel = profileViewModel
    )
}

@Composable
fun StatisticsScreen(
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
            StatisticsScreenContent()
        }
    }
}

@Composable
private fun StatisticsScreenContent() {
    Text(text = "Statistics Screen")
}