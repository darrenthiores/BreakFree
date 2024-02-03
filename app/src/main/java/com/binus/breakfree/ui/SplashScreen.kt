package com.binus.breakfree.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.binus.core_ui.theme.BreakFreeTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onFinish: () -> Unit
) {
    val currentOnFinish by rememberUpdatedState(onFinish)

    LaunchedEffect(true) {
        delay(2000)
        currentOnFinish()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primaryVariant),
        contentAlignment = Alignment.Center
    ) {
        Text("LOGO")
//        Image(
//            painter = painterResource(id = R.drawable.),
//            contentDescription = stringResource(id = R.string.),
//            modifier = Modifier
//                .size(200.dp),
//            contentScale = ContentScale.FillHeight
//        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    BreakFreeTheme {
        SplashScreen {

        }
    }
}