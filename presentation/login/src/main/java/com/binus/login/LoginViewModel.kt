package com.binus.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binus.core.domain.preferences.Preferences
import com.binus.core_ui.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val preferences: Preferences
): ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var number by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    fun onEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.Login -> {
                // login

                viewModelScope.launch {
                    preferences
                        .saveLogin(
                            isLogin = true
                        )
                }

                _state.update {
                    it.copy(
                        isLoading = true
                    )
                }

                viewModelScope.launch {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = null
                        )
                    }

                    _uiEvent.send(
                        UiEvent.Success()
                    )
                }
            }
            is LoginEvent.OnNumberChange -> {
                number = event.number
            }
            is LoginEvent.OnPasswordChange -> {
                password = event.password
            }
            is LoginEvent.OnSelectIdentifier -> {
                _state.update {
                    it.copy(
                        identifier = event.identifier,
                        showDropDown = false
                    )
                }
            }
            LoginEvent.ToggleDropDown -> {
                _state.update {
                    it.copy(
                        showDropDown = !it.showDropDown
                    )
                }
            }
            LoginEvent.TogglePassword -> {
                _state.update {
                    it.copy(
                        showPassword = !it.showPassword
                    )
                }
            }
        }
    }
}