package com.example.setrep.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.setrep.datasource.ProfileViewModel
import com.example.setrep.ui.components.scaffold.MainScaffold

@Composable
fun ProfileScreenTopLevel(
    navController: NavController,
    profileViewModel: ProfileViewModel
) {
    ProfileScreen(
        navController = navController,
        profileViewModel = profileViewModel
    )
}

@Composable
fun ProfileScreen(
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
            ProfileScreenContent()
        }
    }
}

@Composable
private fun ProfileScreenContent() {
    Text(text = "Statistics Screen")
}