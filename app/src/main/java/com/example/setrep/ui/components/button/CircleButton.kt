package com.example.setrep.ui.components.button

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.setrep.R
import com.example.setrep.ui.theme.md_theme_light_errorContainer
import com.example.setrep.ui.theme.md_theme_light_onErrorContainer
import com.example.setrep.ui.theme.md_theme_light_onTertiaryContainer
import com.example.setrep.ui.theme.md_theme_light_tertiaryContainer

val ButtonSize = 65.dp

@Composable
fun CircleButton(active: Boolean, onClick: () -> Unit, icon: @Composable () -> Unit) {
    Button(
        modifier = Modifier.size(ButtonSize),
        enabled = active,
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(containerColor = md_theme_light_tertiaryContainer)

    ) {
        icon()
    }
}

@Composable
fun MinusCircleButton(active: Boolean, onClick: () -> Unit) {
    Button(
        modifier = Modifier.size(ButtonSize),
        enabled = active,
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(containerColor = md_theme_light_errorContainer)

    ) {
        Icon(
            imageVector = Icons.Filled.Remove,
            contentDescription = stringResource(id = R.string.remove),
            tint = md_theme_light_onErrorContainer,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun PlusCircleButton(active: Boolean, onClick: () -> Unit) {
    CircleButton(active,onClick,
        icon = {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(id = R.string.plus),
                tint = md_theme_light_onTertiaryContainer,
                modifier = Modifier.fillMaxSize()
            )
        }
    )
}

@Preview
@Composable
fun PlusCircleButtonPreview() {
    PlusCircleButton(active = true) {
    }
}

@Preview
@Composable
fun MinusCircleButtonPreview() {
    MinusCircleButton(active = true) {
    }
}