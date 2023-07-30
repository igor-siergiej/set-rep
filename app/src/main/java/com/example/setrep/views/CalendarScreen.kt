package com.example.setrep.views

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.setrep.datasource.ProfileViewModel
import com.example.setrep.ui.components.button.SegmentedControl
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
            1 -> SortExercisesScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CalendarPickerScreen() {
    val datePickerState = rememberDatePickerState(System.currentTimeMillis())
    val showDialog = remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(onClick = { showDialog.value = showDialog.value.not() }) {
            Text("Select Date")
        }

        Text(
            text = "Displaying workouts for: " + getDate(
                datePickerState.selectedDateMillis!!,
                "dd/MM/yyyy"
            ),
            style = MaterialTheme.typography.labelLarge
        )
    }

    if (showDialog.value) {
        CustomDatePickerDialog(
            displayDatePickerState = datePickerState,
            closeDialog = { showDialog.value = false })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SortExercisesScreen() {
    val listItems = arrayOf("Date", "Time Taken", "Test")

    var expanded by remember { mutableStateOf(false) }

    var selectedItem by remember { mutableStateOf(listItems[0]) }

    val titles = listOf("Ascending", "Descending")

    var selectedOrder by remember { mutableStateOf(titles[0]) }

    Column(
        modifier = Modifier.padding(10.dp).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ExposedDropdownMenuBox(
            modifier = Modifier.padding(0.dp,5.dp),
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                modifier = Modifier.menuAnchor().width(175.dp),
                value = selectedItem,
                onValueChange = {},
                readOnly = true,
                label = { Text(text = "Sort by:") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                listItems.forEach { selectedOption ->
                    DropdownMenuItem(
                        text = { Text(text = selectedOption) },
                        onClick = {
                            selectedItem = selectedOption
                            expanded = false
                        }
                    )
                }
            }
        }

        SegmentedControl(
            items = titles,
            defaultSelectedItemIndex = 0
        ) {
            selectedOrder = titles[it]
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CustomDatePickerDialog(
    displayDatePickerState: DatePickerState,
    closeDialog: () -> Unit
) {
    val datePickerState = rememberDatePickerState(displayDatePickerState.selectedDateMillis)
    DatePickerDialog(
        onDismissRequest = { closeDialog() },
        confirmButton = {
            Button(
                onClick = {
                    displayDatePickerState.selectedDateMillis = datePickerState.selectedDateMillis
                    closeDialog()
                }) {
                Text(text = "Confirm Date")
            }
        }) {
        DatePicker(
            state = datePickerState,
            showModeToggle = false,
            title = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                ) {
                    Text(
                        text = "Select day to view workouts",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            },
            headline = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                ) {
                    Text(
                        text = "Currently selected day: " + getDate(
                            datePickerState.selectedDateMillis!!,
                            "dd/MM/yyyy"
                        ),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun CustomDatePickerDialogPreview() {
    val datePickerState = rememberDatePickerState(System.currentTimeMillis())
    CustomDatePickerDialog(displayDatePickerState = datePickerState, {})
}

@Preview
@Composable
private fun CalendarScreenContentPreview() {
    CalendarScreenContent()
}

@Preview
@Composable
private fun SortExercisesScreenPreview() {
    SortExercisesScreen()
}

@SuppressLint("SimpleDateFormat") //we provide format as a parameter
fun getDate(milliSeconds: Long, dateFormat: String): String {
    val formatter = SimpleDateFormat(dateFormat)
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = milliSeconds
    return formatter.format(calendar.time)
}