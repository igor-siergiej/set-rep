package com.example.setrep.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    heading: String,
    subHeading: String,
    button: @Composable ()-> Unit
) {
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
                modifier = Modifier.padding(20.dp, 0.dp)
            ) {
                Text(
                    text = heading,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(0.dp, 2.dp)
                )
                Text(
                    text = subHeading,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            button()
        }
    }
}

@Composable
fun EmptyTopBar(title: String) {
    Card(
        shape = RectangleShape,
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

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
fun TopBarPreview() {
    TopBar(heading = "test", subHeading = "test") {

    }
}

@Preview
@Composable
fun MainTopBarPreview() {
    MainTopBar(heading = "test", subHeading = "test")
}