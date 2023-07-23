package com.example.setrep.ui.components.scaffold

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.setrep.ui.theme.SetRepTheme

@Composable
fun EmptyScaffold(
    pageContent: @Composable (innerPadding: PaddingValues) -> Unit = {}
) {
    Scaffold(
        content = { innerPadding ->
            pageContent(innerPadding)
        }
    )
}

@Preview
@Composable
private fun EmptyScaffoldPreview() {
    SetRepTheme() {
        EmptyScaffold( pageContent = {})
    }
}