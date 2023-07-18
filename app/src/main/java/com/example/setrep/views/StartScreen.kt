package com.example.setrep.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.setrep.R
import com.example.setrep.datasource.ProfileViewModel
import com.example.setrep.navigation.Screen
import com.example.setrep.ui.components.MainScaffold
import com.example.setrep.ui.components.StartScreenScaffold

@Composable
fun StartScreenTopLevel(
    navController: NavController,
    profileViewModel: ProfileViewModel
) {
    StartScreen(
        navController = navController,
        profileViewModel = profileViewModel
    )
}

@Composable
fun StartScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel
) {
    StartScreenScaffold { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            StartScreenContent(
                modifier = Modifier.padding(8.dp),
                navController = navController,
                profileViewModel = profileViewModel
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StartScreenContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    profileViewModel: ProfileViewModel
) {
    val state = remember { mutableStateOf(TextFieldValue("test")) }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = state.value,
            onValueChange = { value ->
                state.value = value
            },
            label = {Text(stringResource(id = R.string.enter_profile_name))},
            singleLine = true
        )
        Button(
            onClick = {
            profileViewModel.profile.setName(state.value.text)
            profileViewModel.saveProfile()
            navController.navigate(Screen.Home.route) {
                // this should be navigating without being able to go back
                popUpTo(0) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }
        }) {
            Text(text = stringResource(id = R.string.create_profile))
        }
    }
}

