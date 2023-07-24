package com.example.setrep.ui.components.navbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AreaChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AreaChart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.setrep.R
import com.example.setrep.navigation.Screen
import com.example.setrep.navigation.screens
import com.example.setrep.ui.components.IconGroup

@Composable
fun NavigationBar(
    navController: NavController
) {
    val icons = mapOf(
        Screen.Home to IconGroup(
            filledIcon = Icons.Filled.Home,
            outlineIcon = Icons.Outlined.Home,
            label = stringResource(id = R.string.home)
        ),
        Screen.Statistics to IconGroup(
            filledIcon = Icons.Filled.AreaChart,
            outlineIcon = Icons.Outlined.AreaChart,
            label = stringResource(id = R.string.statistics)
        ),
        Screen.Calendar to IconGroup(
            filledIcon = ImageVector.vectorResource(id = R.drawable.baseline_calendar_month_24),
            outlineIcon = ImageVector.vectorResource(id = R.drawable.outline_calendar_month_24),
            label = stringResource(id = R.string.calendar)
        )
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        screens.forEach { screen ->
            val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
            val labelText = icons[screen]!!.label
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = (if (isSelected)
                            icons[screen]!!.filledIcon
                        else
                            icons[screen]!!.outlineIcon),
                        contentDescription = labelText
                    )
                },
                label = { Text(labelText) },
                selected = isSelected,
                onClick = {
                    navigateToNavScreen(navController,screen)
                }
            )
        }
    }
}

fun navigateToNavScreen(navController: NavController, screen: Screen) {
    navController.navigate(screen.route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(Screen.Home.route)
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }
}

@Preview
@Composable
fun NavigationBarPreview() {
    val navController = rememberNavController()
    NavigationBar(navController)
}