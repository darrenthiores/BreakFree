package com.binus.core_ui.components.drop_downs

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.toSize
import com.binus.core.utils.Constant
import com.binus.core_ui.R
import com.binus.core_ui.components.textfields.NumberTextField
import com.binus.core_ui.components.textfields.TextFieldSize
import com.binus.core_ui.theme.BreakFreeTheme

@Composable
fun NumberDropDown(
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
    onSubmit: () -> Unit = {  },
    isOpen: Boolean,
    onSelectNumber: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    var rowSize by remember { mutableStateOf(Size.Zero) }

    Box(
        modifier = modifier
            .onGloballyPositioned { layoutCoordinates ->
                rowSize = layoutCoordinates.size.toSize()
            }
    ) {
        DropdownMenu(
            modifier = Modifier,
            expanded = isOpen,
            onDismissRequest = onDismiss
        ) {
            Constant.numbers.forEachIndexed { index, number ->
                DropdownMenuItem(
                    onClick = { onSelectNumber(number) },
                    modifier = Modifier
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = number,
                        style = MaterialTheme.typography.subtitle1
                    )
                }

                if (index != Constant.numbers.lastIndex) {
                    Divider()
                }
            }
        }

        NumberTextField(
            text = text,
            onTextChange = onTextChange,
            placeholder = placeholder,
            flagId = flagId,
            identifier = identifier,
            size = size,
            disabled = disabled,
            isError = isError,
            keyboardOptions = keyboardOptions,
            onFocusChange = onFocusChange,
            onFlagClick = onFlagClick,
            onSubmit = onSubmit
        )
    }
}

@Preview(
    name = "Number Drop Down",
    showBackground = true
)
@Composable
private fun BigTextFieldPreview() {
    BreakFreeTheme {
        NumberDropDown(
            modifier = Modifier,
            text = "Test Field",
            onTextChange = {  },
            placeholder = "Input Field",
            isOpen = false,
            onSelectNumber = {  },
            onDismiss = {  }
        )
    }
}
