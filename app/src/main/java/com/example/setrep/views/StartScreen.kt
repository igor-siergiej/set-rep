package com.example.setrep.views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.setrep.R

@Composable
fun StartScreen(
    navController: NavController
) {
    Text(text = stringResource(id = R.string.app_name))
}