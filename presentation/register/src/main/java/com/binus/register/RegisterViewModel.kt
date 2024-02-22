package com.binus.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binus.core.domain.preferences.Preferences
import com.binus.core_ui.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val preferences: Preferences
): ViewModel() {
    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var name by mutableStateOf("")
        private set
    var number by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var otp by mutableStateOf("")
        private set
    var countDown by mutableStateOf(0)
        private set

    private var countDownJob: Job? = null

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.Next -> {
                _state.update {
                    it.copy(
                        currentSection = event.section
                    )
                }
            }
            is RegisterEvent.OnNameChange -> {
                name = event.name
            }
            is RegisterEvent.OnSelectBirthday -> {
                _state.update {
                    it.copy(
                        birthday = event.birthday
                    )
                }
            }
            is RegisterEvent.OnSelectGender -> {
                _state.update {
                    it.copy(
                        gender = event.gender
                    )
                }
            }
            is RegisterEvent.OnSelectIdentifier -> {
                _state.update {
                    it.copy(
                        identifier = event.identifier,
                        showDropDown = false
                    )
                }
            }
            is RegisterEvent.OnNumberChange -> {
                number = event.number
            }
            is RegisterEvent.OnPasswordChange -> {
                password = event.password
            }
            RegisterEvent.OnToggleDropDown -> {
                _state.update {
                    it.copy(
                        showDropDown = !it.showDropDown
                    )
                }
            }
            RegisterEvent.OnToggleShowPassword -> {
                _state.update {
                    it.copy(
                        showPassword = !it.showPassword
                    )
                }
            }
            is RegisterEvent.OnOtpChange -> {
                if (!event.otp.last().isDigit()) {
                    return
                }

                otp = event.otp

                _state.update {
                    it.copy(
                        error = null
                    )
                }

                if (otp.length == 6 && !state.value.isLoading) {
                    // should verify
                }
            }
            RegisterEvent.OnResend -> {
                // resend

                countDown()
            }
            RegisterEvent.OnVerify -> {
                // should verify

                viewModelScope.launch {
                    preferences
                        .saveLogin(
                            isLogin = true
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
        }
    }

    private fun countDown() {
        countDownJob?.cancel()
        countDownJob = viewModelScope.launch {
            (180 downTo 0).asFlow()
                .onEach {
                    delay(1000)
                }
                .conflate()
                .collect {
                    countDown = it
                }
        }
    }
}