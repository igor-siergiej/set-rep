package com.example.setrep.views

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.widget.CalendarView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.setrep.datasource.ProfileViewModel
import com.example.setrep.ui.components.scaffold.MainScaffold
import com.example.setrep.ui.components.topbar.EmptyTopBar
import java.util.Calendar

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
    Column() {
        EmptyTopBar(title = "Calendar")

        var tabIndex by remember { mutableStateOf(0) }
        val titles = listOf("Calendar View", "Display All")
        Column {
            TabRow(selectedTabIndex = tabIndex) {
                titles.forEachIndexed { index, title ->
                    Tab(
                        selected = tabIndex == index,
                        onClick = { tabIndex = index },
                        text = { Text(text = title) }
                    )
                }
            }
        }

        when (tabIndex) {
            0 -> CalendarPickerScreen()
            1 -> Text(text = "Test $tabIndex")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CalendarPickerScreen() {
    val datePickerState = rememberDatePickerState(
        System.currentTimeMillis()
    )
    DatePicker(
        state = datePickerState,
        showModeToggle = false,
        title = { },
        headline = {
            Text(
                text = "Select Date",
                style = MaterialTheme.typography.titleLarge
            )
        },
        modifier = Modifier
            .offset(0.dp, (-80).dp)
            .padding(5.dp)
    )
    Text(text = getDate(datePickerState.selectedDateMillis!!, "dd/MM/yyyy"))
}

@Preview
@Composable
private fun CalendarScreenContentPreview() {
    CalendarScreenContent()
}

@SuppressLint("SimpleDateFormat") //we provide format as a parameter
fun getDate(milliSeconds: Long, dateFormat: String): String {
    val formatter = SimpleDateFormat(dateFormat)
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = milliSeconds
    return formatter.format(calendar.time)
}