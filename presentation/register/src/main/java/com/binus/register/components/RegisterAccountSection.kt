package com.binus.register.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.binus.core_ui.components.buttons.ButtonShape
import com.binus.core_ui.components.buttons.DefaultButton
import com.binus.core_ui.components.drop_downs.NumberDropDown
import com.binus.core_ui.components.headers.CommonTopBar
import com.binus.core_ui.components.textfields.PasswordTextField
import com.binus.core_ui.theme.BreakFreeTheme
import com.binus.core_ui.utils.UiConstant
import com.binus.register.R
import com.binus.register.RegisterEvent
import com.binus.register.RegisterState
import com.binus.register.model.RegisterSection

@Composable
fun RegisterAccountSection(
    state: RegisterState,
    number: String,
    password: String,
    onEvent: (RegisterEvent) -> Unit
) {
    val painter = painterResource(id = R.drawable.sign_up_icon)

    Scaffold(
        topBar = {
            CommonTopBar(
                title = "",
                contentColor = MaterialTheme.colors.primary,
                backgroundColor = MaterialTheme.colors.primaryVariant,
                onBackClicked = {
                    onEvent(
                        RegisterEvent.Next(
                            section = RegisterSection.Personal
                        )
                    )
                }
            )
        },
        backgroundColor = MaterialTheme.colors.primaryVariant,
        contentColor = MaterialTheme.colors.onBackground
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(
                    horizontal = 24.dp
                ),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(id = R.string.account_title),
                style = MaterialTheme.typography.h4
            )

            Spacer(modifier = Modifier.height(32.dp))

            Image(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterHorizontally)
                    .aspectRatio(
                        ratio = painter.intrinsicSize.height / painter.intrinsicSize.width
                    ),
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(32.dp))

            NumberDropDown(
                text = number,
                onTextChange = {
                    onEvent(RegisterEvent.OnNumberChange(it))
                },
                placeholder = stringResource(id = com.binus.core_ui.R.string.phone_number) + stringResource(
                    id = com.binus.core_ui.R.string.star
                ),
                flagId = UiConstant.getFlagId(state.identifier),
                identifier = state.identifier,
                onFlagClick = {
                    onEvent(RegisterEvent.OnToggleDropDown)
                },
                isOpen = state.showDropDown,
                onSelectNumber = {
                    onEvent(RegisterEvent.OnSelectIdentifier(it))
                },
                onDismiss = {
                    onEvent(RegisterEvent.OnToggleDropDown)
                }
            )

            Spacer(
                modifier = Modifier
                    .height(8.dp)
            )

            PasswordTextField(
                text = password,
                onTextChange = {
                    onEvent(RegisterEvent.OnPasswordChange(it))
                },
                placeholder = stringResource(id = R.string.c_password),
                isVisible = state.showPassword,
                onToggle = {
                    onEvent(RegisterEvent.OnToggleShowPassword)
                }
            )
            
            Spacer(modifier = Modifier.height(64.dp))

            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth(),
                label = stringResource(id = R.string.sign_up),
                onClick = {
                    onEvent(RegisterEvent.Next(RegisterSection.Otp))
                },
                shape = ButtonShape.Pill,
                disabled = number.isEmpty() || password.isEmpty(),
                isLoading = state.isLoading
            )

            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}


@Preview
@Composable
private fun RegisterAccountSectionPreview() {
    BreakFreeTheme {
        RegisterAccountSection(
            state = RegisterState(),
            number = "",
            password = "",
            onEvent = {  }
        )
    }
}