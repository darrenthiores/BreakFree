package com.binus.core_ui.components.textfields

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.binus.core_ui.R
import com.binus.core_ui.theme.BreakFreeTheme

@Composable
fun NumberTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    placeholder: String,
    @DrawableRes flagId: Int = R.drawable.flag,
    identifier: String = "+62",
    size: TextFieldSize = TextFieldSize.Large,
    disabled: Boolean = false,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Done,
        keyboardType = KeyboardType.NumberPassword
    ),
    onFocusChange: (Boolean) -> Unit = {  },
    onFlagClick: () -> Unit = {  },
    onSubmit: () -> Unit = {  }
) {
    val focusManager = LocalFocusManager.current
    var isFocused by remember {
        mutableStateOf(false)
    }

    val textStyle = when {
        text.isEmpty() -> {
            when(size) {
                TextFieldSize.Large -> MaterialTheme.typography.subtitle1
                TextFieldSize.Medium -> MaterialTheme.typography.subtitle2
                TextFieldSize.Small -> MaterialTheme.typography.body1
            }
        }
        else -> {
            when(size) {
                TextFieldSize.Large -> MaterialTheme.typography.body1
                TextFieldSize.Medium -> MaterialTheme.typography.body1
                TextFieldSize.Small -> MaterialTheme.typography.caption
            }
        }
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

    val textSpacing = when (size) {
        TextFieldSize.Large -> 3.dp
        TextFieldSize.Medium -> 2.dp
        TextFieldSize.Small -> 0.dp
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
        maxLines = 1,
        cursorBrush = SolidColor(MaterialTheme.colors.secondary),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(12.dp)
                    )
                    .background(
                        color = if (disabled) Color.LightGray else MaterialTheme.colors.background,
                        shape = RoundedCornerShape(12.dp)
                    )
//                    .border(
//                        width = 1.dp,
//                        color = if (isError) Color.Red else {
//                            if (isFocused) MaterialTheme.colors.primaryVariant else Color.Gray
//                        },
//                        shape = RoundedCornerShape(12.dp)
//                    )
                    .padding(
                        end = 16.dp
                    )
                    .height(textFieldHeight),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .clickable(
                            enabled = !disabled
                        ) {
                            onFlagClick()
                        }
                        .height(textFieldHeight)
                        .background(
                            Color.LightGray,
                            shape = RoundedCornerShape(
                                topStart = 12.dp,
                                bottomStart = 12.dp
                            )
                        )
                        .padding(
                            start = 12.dp,
                            end = 8.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .size(24.dp),
                        painter = painterResource(id = flagId),
                        contentDescription = null,
                        contentScale = ContentScale.Fit
                    )

                    Icon(
                        imageVector = Icons.Rounded.KeyboardArrowDown,
                        contentDescription = null
                    )
                }

                Spacer(
                    modifier = Modifier
                        .width(8.dp)
                )

//                if (text.isEmpty() || isFocused) {
//                    Text(
//                        text = identifier,
//                        color = MaterialTheme.colors.onBackground,
//                        style = textStyle,
//                        maxLines = 1
//                    )
//
//                    Spacer(
//                        modifier = Modifier
//                            .width(8.dp)
//                    )
//                }

                Text(
                    text = identifier,
                    color = MaterialTheme.colors.onBackground,
                    style = textStyle,
                    maxLines = 1
                )

                Spacer(
                    modifier = Modifier
                        .width(8.dp)
                )

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

//                if (text.isEmpty() || isFocused) {
//                    Box(
//                        modifier = Modifier
//                            .weight(1f)
//                    ) {
//                        if (text.isEmpty()) {
//                            Text(
//                                text = placeholder,
//                                color = Color.Gray,
//                                style = textStyle,
//                                maxLines = 1,
//                                overflow = TextOverflow.Ellipsis
//                            )
//                        }
//
//                        innerTextField()
//                    }
//                } else {
//                    Column(
//                        modifier = Modifier
//                            .weight(1f),
//                        horizontalAlignment = Alignment.Start,
//                        verticalArrangement = Arrangement.spacedBy(textSpacing)
//                    ) {
//                        Row(
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            Text(
//                                text = stringResource(id = R.string.phone_number),
//                                color = Color.DarkGray,
//                                style = MaterialTheme.typography.caption,
//                                maxLines = 1,
//                                overflow = TextOverflow.Ellipsis
//                            )
//
//                            Text(
//                                text = stringResource(id = R.string.star),
//                                color = Color.Red,
//                                style = MaterialTheme.typography.caption,
//                                maxLines = 1,
//                                overflow = TextOverflow.Ellipsis
//                            )
//                        }
//
//                        Row(
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            Text(
//                                text = "$identifier ",
//                                color = Color.DarkGray,
//                                style = textStyle,
//                                maxLines = 1,
//                                overflow = TextOverflow.Ellipsis
//                            )
//
//                            innerTextField()
//                        }
//                    }
//                }

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
                        contentDescription = stringResource(id = R.string.delete_text_cd),
                        tint = Color.Gray
                    )
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
        NumberTextField(
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
        NumberTextField(
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
        NumberTextField(
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
private fun EmptyTextFieldPreview() {
    BreakFreeTheme {
        NumberTextField(
            modifier = Modifier,
            text = "",
            onTextChange = {  },
            placeholder = "Phone Number*"
        )
    }
}

@Preview(
    name = "Disabled Number Text Field",
    showBackground = true
)
@Composable
private fun DisabledNumberTextFieldPreview() {
    BreakFreeTheme {
        NumberTextField(
            modifier = Modifier,
            text = "Test",
            onTextChange = {  },
            placeholder = "Input Field",
            disabled = true
        )
    }
}