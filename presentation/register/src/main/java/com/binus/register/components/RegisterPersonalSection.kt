package com.binus.register.components

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import com.binus.core.utils.DateUtils
import com.binus.core_ui.components.buttons.ButtonShape
import com.binus.core_ui.components.buttons.DefaultButton
import com.binus.core_ui.components.buttons.SelectableButton
import com.binus.core_ui.components.headers.CommonTopBar
import com.binus.core_ui.components.textfields.DefaultTextField
import com.binus.core_ui.theme.BreakFreeTheme
import com.binus.register.R
import com.binus.register.RegisterEvent
import com.binus.register.RegisterState
import com.binus.register.model.Gender
import com.binus.register.model.Gender.Companion.getImageId
import com.binus.register.model.RegisterSection
import timber.log.Timber
import java.util.Calendar
import java.util.Date

@Composable
fun RegisterPersonalSection(
    state: RegisterState,
    name: String,
    onEvent: (RegisterEvent) -> Unit,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current

    val dCalendar = Calendar.getInstance()
    val dYear = dCalendar.get(Calendar.YEAR)
    val dMonth = dCalendar.get(Calendar.MONTH)
    val dDay = dCalendar.get(Calendar.DAY_OF_MONTH)

    dCalendar.time = Date()

    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            com.binus.core_ui.R.style.Theme_DialogTheme,
            { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
                val dateString = if(mDayOfMonth <= 9) "0$mDayOfMonth" else "$mDayOfMonth"
                val month = mMonth + 1
                val monthString = if(month <= 9) "0$month" else "$month"

                onEvent(
                    RegisterEvent.OnSelectBirthday(
                        birthday = DateUtils.stringToLocalDateTime(
                            date = "$mYear-$monthString-$dateString"
                        )
                    )
                )
            },
            dYear,
            dMonth,
            dDay
        )
    }

    LaunchedEffect(key1 = state.birthday) {
        state.birthday?.let { birthday ->
            datePickerDialog.updateDate(
                birthday.year,
                birthday.monthNumber - 1,
                birthday.dayOfMonth
            )
        }

        Timber.d("here called")
    }

    Scaffold(
        topBar = {
            CommonTopBar(
                title = "",
                contentColor = MaterialTheme.colors.primary,
                backgroundColor = MaterialTheme.colors.primaryVariant,
                onBackClicked = onBackClick
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
                text = stringResource(id = R.string.register_title),
                style = MaterialTheme.typography.h4
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = stringResource(id = R.string.register_desc),
                style = MaterialTheme.typography.subtitle1
            )

            Spacer(modifier = Modifier.height(48.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Gender.entries.forEach { gender ->
                    GenderBox(
                        gender = gender,
                        isSelected = gender == state.gender,
                        onSelectGender = {
                            onEvent(RegisterEvent.OnSelectGender(gender))
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            DefaultTextField(
                text = name,
                onTextChange = {
                    onEvent(RegisterEvent.OnNameChange(it))
                },
                placeholder = stringResource(id = R.string.name_pc)
            )

            Spacer(
                modifier = Modifier
                    .height(8.dp)
            )
            
            SelectableButton(
                label = if (state.birthday == null) stringResource(id = R.string.birthday_pc)
                else DateUtils.formatDate(state.birthday),
                onClick = {
                    datePickerDialog.show()
                },
                endIcon = Icons.Rounded.CalendarMonth,
                color = MaterialTheme.colors.background,
                textColor = MaterialTheme.colors.onBackground,
                iconColor = MaterialTheme.colors.primary
            )
            
            Spacer(modifier = Modifier.height(64.dp))

            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth(),
                label = stringResource(id = R.string.next),
                onClick = {
                    onEvent(RegisterEvent.Next(RegisterSection.Account))
                },
                shape = ButtonShape.Pill,
                disabled = name.isEmpty() || state.birthday == null || state.gender == null,
                isLoading = state.isLoading
            )

            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}

@Composable
private fun GenderBox(
    modifier: Modifier = Modifier,
    gender: Gender,
    isSelected: Boolean,
    onSelectGender: () -> Unit
) {
    val config = LocalConfiguration.current
    val screenWidth = config.screenWidthDp
    val width = (screenWidth / 2) - 24 - 8
    val boxWidth = min(width.dp, 100.dp)

    val painter = painterResource(id = gender.getImageId())

    Box(
        modifier = modifier
            .size(boxWidth)
            .clip(
                shape = RoundedCornerShape(30.dp)
            )
            .background(
                color = if (isSelected) Color.LightGray else Color.Gray,
                shape = RoundedCornerShape(30.dp)
            )
            .clickable { onSelectGender() }
            .padding(
                top = 16.dp,
                bottom = 8.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = gender.name,
                style = MaterialTheme.typography.subtitle1
            )

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
        }
    }
}

@Preview
@Composable
private fun GenderBoxPreview() {
    BreakFreeTheme {
        GenderBox(
            gender = Gender.Male,
            isSelected = false,
            onSelectGender = {  }
        )
    }
}

@Preview
@Composable
private fun RegisterPersonalSectionPreview() {
    BreakFreeTheme {
        RegisterPersonalSection(
            state = RegisterState(),
            name = "",
            onEvent = {  },
            onBackClick = {  }
        )
    }
}