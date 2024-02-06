package com.binus.breakfree

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.binus.core.domain.preferences.Preferences
import com.binus.core_ui.theme.BreakFreeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val showBoarding by preferences
                .loadShowBoardingAsFlow()
                .collectAsState(initial = true)

            val isLogin by preferences
                .loadIsLoginAsFlow()
                .collectAsState(initial = true)

            BreakFreeTheme {
                BreakFree(
                    shouldShowOnBoarding = showBoarding,
                    shouldShowLogin = !isLogin
                )
            }
        }
    }
}