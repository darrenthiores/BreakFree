package com.binus.register.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.binus.core_ui.components.buttons.ButtonShape
import com.binus.core_ui.components.buttons.DefaultButton
import com.binus.core_ui.components.headers.CommonTopBar
import com.binus.core_ui.components.textfields.OtpTextField
import com.binus.core_ui.theme.BreakFreeTheme
import com.binus.register.R
import com.binus.register.RegisterEvent
import com.binus.register.RegisterState
import com.binus.register.model.RegisterSection

@Composable
fun RegisterOtpSection(
    state: RegisterState,
    number: String,
    otp: String,
    countDown: Int,
    onEvent: (RegisterEvent) -> Unit
) {
    val painter = painterResource(id = R.drawable.otp_icon)
    val minute = remember(countDown) {
        derivedStateOf {
            countDown / 60
        }
    }
    val second = remember(countDown) {
        derivedStateOf {
            countDown % 60
        }
    }
    val countDownText = if (countDown == 0) stringResource(id = R.string.resend)
    else if (minute.value > 0 && second.value > 0) stringResource(id = R.string.minute_second, minute.value, second.value)
    else if (minute.value > 0) stringResource(id = R.string.minute, minute.value)
    else stringResource(id = R.string.second, second.value)

    Scaffold(
        topBar = {
            CommonTopBar(
                title = "",
                contentColor = MaterialTheme.colors.primary,
                backgroundColor = MaterialTheme.colors.primaryVariant,
                onBackClicked = {
                    onEvent(
                        RegisterEvent.Next(
                            section = RegisterSection.Account
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
                text = stringResource(id = R.string.otp_title),
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

            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.otp_desc),
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                )
            )
            
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = state.identifier + " " + number,
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            OtpTextField(
                otpText = otp,
                onOtpTextChange = {
                    onEvent(RegisterEvent.OnOtpChange(it))
                },
                isError = state.error != null
            )

            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )

            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.resend_otp_desc) + " ",
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.Normal
                    )
                )

                Text(
                    modifier = Modifier
                        .clickable(
                            enabled = countDown == 0
                        ) {
                          onEvent(
                              RegisterEvent.OnResend
                          )
                        },
                    text = countDownText,
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            
            Spacer(modifier = Modifier.height(48.dp))

            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth(),
                label = stringResource(id = R.string.otp_button),
                onClick = {
                    onEvent(RegisterEvent.OnVerify)
                },
                shape = ButtonShape.Pill,
                disabled = otp.isEmpty(),
                isLoading = state.isLoading
            )

            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}


@Preview
@Composable
private fun RegisterOtpSectionPreview() {
    BreakFreeTheme {
        RegisterOtpSection(
            state = RegisterState(),
            number = "",
            otp = "",
            countDown = 0,
            onEvent = {  }
        )
    }
}