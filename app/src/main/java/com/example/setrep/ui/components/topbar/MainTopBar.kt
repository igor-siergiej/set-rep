package com.example.setrep.ui.components.topbar

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MainTopBar(
    heading: String,
    subHeading: String
) {
    TopBar(
        heading = heading,
        subHeading = subHeading,
        button =
        {
            IconButton(
                onClick = { /*TODO Navigate to Profile Screen*/ },
                modifier = Modifier
                    .size(80.dp)
                    .padding(0.dp, 0.dp, 20.dp, 0.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Account",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    )
}

@Preview
@Composable
fun MainTopBarPreview() {
    MainTopBar(heading = "test", subHeading = "test")
}