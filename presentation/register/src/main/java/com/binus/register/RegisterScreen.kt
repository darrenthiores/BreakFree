package com.binus.register

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.binus.register.components.RegisterPersonalSection
import com.binus.register.model.RegisterSection

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RegisterScreen(
    state: RegisterState,
    name: String,
    onEvent: (RegisterEvent) -> Unit,
    onBackClick: () -> Unit
) {
    AnimatedContent(
        targetState = state.currentSection,
        modifier = Modifier
            .fillMaxSize(),
        transitionSpec = {
            slideInHorizontally(
                initialOffsetX = {
                    it
                }
            ) with slideOutHorizontally(
                targetOffsetX = {
                    -it
                }
            )
        },
        label = stringResource(id = R.string.register_label)
    ) { page ->
        when (page) {
            RegisterSection.Personal -> {
                RegisterPersonalSection(
                    state = state,
                    name = name,
                    onEvent = onEvent,
                    onBackClick = onBackClick
                )
            }
        }
    }
}