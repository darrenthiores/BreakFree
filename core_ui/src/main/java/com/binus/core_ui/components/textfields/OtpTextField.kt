package com.binus.core_ui.components.textfields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.binus.core_ui.theme.BreakFreeTheme

@Composable
fun OtpTextField(
    modifier: Modifier = Modifier,
    otpText: String,
    otpCount: Int = 6,
    isError: Boolean = false,
    onOtpTextChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    var isTextFieldFocus by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    BasicTextField(
        modifier = modifier
            .onFocusChanged {
                isTextFieldFocus = it.isFocused
            }
            .focusRequester(focusRequester),
        value = TextFieldValue(
            text = otpText,
            selection = TextRange(otpText.length)
        ),
        onValueChange = {
            if (it.text.length <= otpCount) {
                onOtpTextChange(it.text)
            }

            if (it.text.length == otpCount) {
                focusManager.clearFocus()
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        ),
        decorationBox = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                repeat(otpCount) { index ->
                    CharView(
                        index = index,
                        text = otpText,
                        isFocus = isTextFieldFocus,
                        isError = isError
                    )
                }
            }
        }
    )
}

@Composable
private fun CharView(
    index: Int,
    text: String,
    isFocus: Boolean,
    isError: Boolean,
    darkTheme: Boolean = isSystemInDarkTheme()
) {
    val isFocused = text.length >= index && isFocus
    val char = when {
        index >= text.length -> ""
        else -> text[index].toString()
    }
    val bgColor = when {
        isError -> Color.Red
        darkTheme -> Color.Gray
        else -> Color.White
    }

    Text(
        modifier = Modifier
            .size(48.dp)
            .background(
                color = bgColor,
                shape = RoundedCornerShape(5.dp)
            )
            .border(
                width = if (isFocused || isError) 2.dp else 0.dp,
                color = if (isError) Color.Red else Color.LightGray,
                shape = RoundedCornerShape(5.dp)
            ),
        text = char,
        style = MaterialTheme.typography.h4.copy(
            color = MaterialTheme.colors.onBackground
        ),
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
private fun CharViewPreview() {
    BreakFreeTheme(darkTheme = true) {
        CharView(
            index = 0,
            text = "123",
            isFocus = false,
            isError = false,
            darkTheme = true
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun OtpTextFieldPreview() {
    BreakFreeTheme {
        OtpTextField(
            otpText = "123",
            onOtpTextChange = {  }
        )
    }
}