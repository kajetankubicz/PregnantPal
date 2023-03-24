package com.example.pregnantpal.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun textInput(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {},
    trailingIcon: () -> Unit = {},
    keyboard: KeyboardOptions,
    textColor: Color? = null,
    labelColor: Color? = null,
){

    val keyboardController = LocalSoftwareKeyboardController.current


    TextField(
        value = text,
        onValueChange = onTextChange,
        colors = TextFieldDefaults.textFieldColors(
            textColor = textColor?: androidx.compose.material3.MaterialTheme.colorScheme.onPrimary,
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer,
            cursorColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer),
        maxLines = maxLine,
        label = { Text(
                text = label,
                color = labelColor?: androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
            ) },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    onImeAction()
                    keyboardController?.hide()
                 }),
                modifier = modifier,
    )
}

@Composable
fun addButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiary,
        contentColor = androidx.compose.material3.MaterialTheme.colorScheme.onTertiaryContainer
    )
) {
    Button(
        onClick = { onClick.invoke() },
        shape = CircleShape,
        enabled = enabled,
        colors = colors,
        modifier = Modifier) {
        Text(text = text)
        }
}



