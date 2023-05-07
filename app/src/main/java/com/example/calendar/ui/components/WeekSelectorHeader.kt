package com.example.calendar.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calendar.model.Day
import com.example.calendar.model.Week
import com.example.workoutdigpal.R
import java.time.LocalDate
import java.util.*

@Composable
fun WeekSelectorHeader(
    calendar: Calendar,
    modifier: Modifier,
    week: Week,
    selectedDay: MutableState<Day?>,
    onDayChange: (LocalDate) -> Unit
) {
    HomeHeader(calendar = calendar, modifier = modifier, selectedDay = selectedDay)
    DayTabs(
        week = week,
        modifier = modifier,
        onDayChange = { newDate -> onDayChange(newDate) })
}

@Composable
fun HomeHeader(calendar: Calendar, modifier: Modifier, selectedDay: MutableState<Day?>) {
    val weekNumber = calendar.get(Calendar.WEEK_OF_YEAR)
    val month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG_FORMAT, Locale.ENGLISH)
    val fontSize = 20.sp
    Text(text = stringResource(R.string.weekTitle) + " $weekNumber", fontSize = fontSize)
    Text(text = stringResource(R.string.monthTitle) + " $month", fontSize = fontSize)
    Text(
        text = stringResource(R.string.selectedDay) + " ${selectedDay.value?.dayName ?: "something went wrong"}",
        fontSize = fontSize
    )
}

@Composable
fun DayTabs(week: Week, modifier: Modifier, onDayChange: (LocalDate) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        week.days.forEach { day ->
            Button(
                onClick = { onDayChange(day.date) },
                modifier = modifier
                    .padding(2.dp)
                    .width(55.dp)
                    .height(55.dp)
            ) {
                Column() {
                    Text(
                        text = day.dayName.substring(0, 2),
                        fontSize = 14.sp
                    )
                    Text(
                        text = day.date.dayOfMonth.toString().padStart(2, '0'),
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}
