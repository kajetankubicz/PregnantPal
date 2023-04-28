package com.example.pregnantpal.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


//File that contains our own composable functions

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
    unfocusedIndicator: Color? = null,
){

    val keyboardController = LocalSoftwareKeyboardController.current


    TextField(
        value = text,
        onValueChange = onTextChange,
        colors = TextFieldDefaults.textFieldColors(
            textColor = textColor?: androidx.compose.material3.MaterialTheme.colorScheme.onPrimaryContainer,
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = androidx.compose.material3.MaterialTheme.colorScheme.onPrimaryContainer,
            cursorColor = androidx.compose.material3.MaterialTheme.colorScheme.onPrimaryContainer,
            unfocusedIndicatorColor =  unfocusedIndicator?: androidx.compose.material3.MaterialTheme.colorScheme.onPrimaryContainer),
        maxLines = maxLine,
        label = { Text(
                text = label,
                color = labelColor?: androidx.compose.material3.MaterialTheme.colorScheme.onPrimaryContainer
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
        backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.primaryContainer,
        contentColor = androidx.compose.material3.MaterialTheme.colorScheme.onPrimaryContainer
    )
) {
    Button(
        onClick = { onClick.invoke() },
        shape = RoundedCornerShape(20.dp),
        enabled = enabled,
        colors = colors,
        modifier = Modifier) {
        Text(text = text, fontSize = 22.sp)
        }
}



