package com.binus.login

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.binus.core_ui.components.buttons.ButtonShape
import com.binus.core_ui.components.buttons.DefaultButton
import com.binus.core_ui.components.drop_downs.NumberDropDown
import com.binus.core_ui.components.headers.CommonTopBar
import com.binus.core_ui.components.textfields.PasswordTextField
import com.binus.core_ui.utils.UiConstant

@Composable
fun LoginScreen(
    state: LoginState,
    number: String,
    password: String,
    onEvent: (LoginEvent) -> Unit,
    onBackClick: () -> Unit
) {
    val painter = painterResource(id = R.drawable.dummy)

    Scaffold(
        topBar = {
            CommonTopBar(
                title = "",
                contentColor = MaterialTheme.colors.primary,
                backgroundColor = MaterialTheme.colors.primaryVariant,
                onBackClicked = onBackClick
            )
        },
        backgroundColor = MaterialTheme.colors.primaryVariant
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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(48.dp))

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

            Spacer(
                modifier = Modifier
                    .height(48.dp)
            )

            Text(
                text = stringResource(id = R.string.welcome_back),
                style = MaterialTheme.typography.h5.copy(
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.height(48.dp))

            NumberDropDown(
                text = number,
                onTextChange = {
                    onEvent(LoginEvent.OnNumberChange(it))
                },
                placeholder = stringResource(id = com.binus.core_ui.R.string.phone_number) + stringResource(
                    id = com.binus.core_ui.R.string.star
                ),
                flagId = UiConstant.getFlagId(state.identifier),
                identifier = state.identifier,
                onFlagClick = {
                    onEvent(LoginEvent.ToggleDropDown)
                },
                isOpen = state.showDropDown,
                onSelectNumber = {
                    onEvent(LoginEvent.OnSelectIdentifier(it))
                },
                onDismiss = {
                    onEvent(LoginEvent.ToggleDropDown)
                }
            )

            Spacer(
                modifier = Modifier
                    .height(8.dp)
            )

            PasswordTextField(
                text = password,
                onTextChange = {
                    onEvent(LoginEvent.OnPasswordChange(it))
                },
                placeholder = stringResource(id = R.string.password),
                isVisible = state.showPassword,
                onToggle = {
                    onEvent(LoginEvent.TogglePassword)
                }
            )

            Spacer(
                modifier = Modifier
                    .height(24.dp)
            )

            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth(),
                label = stringResource(id = R.string.login),
                onClick = {
                    onEvent(LoginEvent.Login)
                },
                shape = ButtonShape.Pill,
                disabled = number.isEmpty() || password.isEmpty(),
                isLoading = state.isLoading
            )

            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}