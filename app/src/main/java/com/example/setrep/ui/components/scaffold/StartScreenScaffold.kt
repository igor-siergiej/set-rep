package com.example.setrep.ui.components.scaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.setrep.R

@Composable
fun StartScreenScaffold(
    pageContent: @Composable (innerPadding: PaddingValues) -> Unit = {}
) {
    Scaffold(
        topBar = {
            Card(
                shape = RectangleShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(152.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        stringResource(id = R.string.welcome),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.displayMedium)
                }
            }
        },
        content = { innerPadding ->
            pageContent(innerPadding)
        }
    )
}

@Preview
@Composable
fun StartScreenScaffoldPreview() {
    StartScreenScaffold()
}