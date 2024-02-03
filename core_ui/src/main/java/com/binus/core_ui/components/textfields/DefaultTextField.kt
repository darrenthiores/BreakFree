package com.binus.core_ui.components.textfields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.binus.core_ui.theme.BreakFreeTheme

@Composable
fun DefaultTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    placeholder: String,
    size: TextFieldSize = TextFieldSize.Large,
    disabled: Boolean = false,
    isError: Boolean = false,
    startIcon: ImageVector? = null,
    endIcon: ImageVector? = null,
    lineLimit: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Done
    ),
    onEndIconClick: () -> Unit = {  },
    onFocusChange: (Boolean) -> Unit = {  },
    onSubmit: () -> Unit = {  }
) {
    val focusManager = LocalFocusManager.current
    var isFocused by remember {
        mutableStateOf(false)
    }

    val textStyle = when(size) {
        TextFieldSize.Large -> MaterialTheme.typography.subtitle1
        TextFieldSize.Medium -> MaterialTheme.typography.subtitle2
        TextFieldSize.Small -> MaterialTheme.typography.body1
    }

    val iconSize = when(size) {
        TextFieldSize.Large -> 24.dp
        TextFieldSize.Medium -> 20.dp
        TextFieldSize.Small -> 16.dp
    }

    val textFieldHeight = when(size) {
        TextFieldSize.Large -> 56.dp
        TextFieldSize.Medium -> 48.dp
        TextFieldSize.Small -> 36.dp
    }

    BasicTextField(
        value = text,
        onValueChange = {
            onTextChange(it)
        },
        modifier = modifier
            .onFocusChanged {
                isFocused = it.isFocused
                onFocusChange(it.isFocused)
            },
        textStyle = textStyle.copy(
            color = if (disabled) Color.Gray else MaterialTheme.colors.onBackground
        ),
        maxLines = lineLimit,
        cursorBrush = SolidColor(MaterialTheme.colors.secondary),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .background(
                        color = if (disabled) Color.LightGray else MaterialTheme.colors.background,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = if (isError) Color.Red else {
                            if (isFocused) MaterialTheme.colors.primaryVariant else Color.Gray
                        },
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 16.dp)
                    .height(
                        (textFieldHeight.times(lineLimit))
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                startIcon?.let {
                    Icon(
                        modifier = Modifier
                            .size(iconSize),
                        imageVector = it,
                        contentDescription = null,
                        tint = if (disabled) Color.Gray else MaterialTheme.colors.onBackground
                    )

                    Spacer(
                        modifier = Modifier
                            .width(8.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    if (text.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = Color.Gray,
                            style = textStyle,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    innerTextField()
                }

                if (text.isNotEmpty()) {
                    Spacer(
                        modifier = Modifier
                            .width(8.dp)
                    )

                    val clickableModifier = Modifier
                        .size(iconSize)
                        .clickable {
                            onTextChange("")
                        }
                    val disabledModifier = Modifier
                        .size(iconSize)
                    val endIconModifier = if (disabled) disabledModifier
                    else clickableModifier

                    Icon(
                        modifier = endIconModifier,
                        imageVector = Icons.Outlined.Cancel,
                        contentDescription = "Delete Text",
                        tint = Color.Gray
                    )
                } else {
                    endIcon?.let {
                        Spacer(
                            modifier = Modifier
                                .width(8.dp)
                        )

                        IconButton(
                            modifier = Modifier
                                .then(
                                    Modifier
                                        .size(iconSize)
                                ),
                            onClick = onEndIconClick
                        ) {
                            Icon(
                                modifier = Modifier,
                                imageVector = it,
                                contentDescription = null,
                                tint = if (disabled) Color.Gray else MaterialTheme.colors.onBackground
                            )
                        }
                    }
                }
            }
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
                onSubmit()
            }
        ),
        enabled = !disabled
    )
}

@Preview(
    name = "Big Text Field",
    showBackground = true
)
@Composable
private fun BigTextFieldPreview() {
    BreakFreeTheme {
        DefaultTextField(
            modifier = Modifier,
            text = "Test Field",
            onTextChange = {  },
            placeholder = "Input Field"
        )
    }
}

@Preview(
    name = "Medium Text Field",
    showBackground = true
)
@Composable
private fun MediumTextFieldPreview() {
    BreakFreeTheme {
        DefaultTextField(
            modifier = Modifier,
            text = "Test Field",
            onTextChange = {  },
            placeholder = "Input Field",
            size = TextFieldSize.Large
        )
    }
}

@Preview(
    name = "Small Text Field",
    showBackground = true
)
@Composable
private fun SmallTextFieldPreview() {
    BreakFreeTheme {
        DefaultTextField(
            modifier = Modifier,
            text = "Test Field",
            onTextChange = {  },
            placeholder = "Input Field",
            size = TextFieldSize.Small
        )
    }
}

@Preview(
    name = "Icon Text Field",
    showBackground = true
)
@Composable
private fun IconTextFieldPreview() {
    BreakFreeTheme {
        DefaultTextField(
            modifier = Modifier,
            text = "Test Field",
            onTextChange = {  },
            placeholder = "Input Field",
            startIcon = Icons.Rounded.Search
        )
    }
}

@Preview(
    name = "Disabled Default Text Field",
    showBackground = true
)
@Composable
private fun DisabledDefaultTextFieldPreview() {
    BreakFreeTheme {
        DefaultTextField(
            modifier = Modifier,
            text = "Test",
            onTextChange = {  },
            placeholder = "Input Field",
            startIcon = Icons.Rounded.Search,
            disabled = true
        )
    }
}