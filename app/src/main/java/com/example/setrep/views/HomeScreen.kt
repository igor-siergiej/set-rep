package com.example.setrep.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.setrep.datasource.ProfileViewModel
import com.example.setrep.ui.components.MainScaffold
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
        Card(
            shape = RectangleShape,
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.padding(20.dp,0.dp)
                ) {
                    Text(
                        text = "Welcome $profileName!",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(0.dp,2.dp)
                    )
                    Text(
                        text = "Ready to workout?",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Account",
                    modifier = Modifier.padding(0.dp,0.dp,20.dp,0.dp).size(90.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenContentPreview() {
    SetRepTheme() {
        HomeScreenContent("user")
    }
}

