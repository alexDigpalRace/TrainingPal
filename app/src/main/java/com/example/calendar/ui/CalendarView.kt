package com.example.calendar.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.calendar.model.Day
import com.example.calendar.model.Exercise
import com.example.calendar.model.Week
import com.example.calendar.ui.components.ExerciseDetails
import com.example.calendar.ui.components.WeekSelectorHeader
import com.example.calendar.ui.components.WorkoutDetails
import com.example.calendar.util.DateHelper
import java.time.LocalDate
import java.util.*

@Composable
fun CalendarView(week: Week, calendar: Calendar, modifier: Modifier = Modifier) {
    val helperLib = DateHelper()

    val noExercise = Exercise(
        "Exercise not found", 0, 0, false, "", "", ""
    )

    fun getDay(): Day? {
        val currentCalendarDay = helperLib.getLocalDate(calendar)
        return week.days.find { day -> currentCalendarDay.isEqual(day.date) }
    }

    val selectedDay: MutableState<Day?> = remember {
        mutableStateOf(getDay())
    }

    fun changeDay(newDate: LocalDate) {
        // change the calendar so its points to the new date
        helperLib.setCalendarToLocalDate(calendar, newDate)
        val newDay = getDay()
        if (newDay !== null) {
            selectedDay.value = newDay

            if (newDay.workout === null) {
                return
            }
        }
    }

    val selectedExerciseState = remember {
        mutableStateOf(
            selectedDay.value?.workout?.exercises?.get(0) ?: noExercise
        )
    }

    fun getExercise(name: String): Exercise? {
        return selectedDay.value?.workout?.exercises?.find { exercise -> exercise.name == name }
    }

    fun changeExercise(name: String) {
        val exercise = getExercise(name)
        if (exercise != null) {
            selectedExerciseState.value = exercise
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WeekSelectorHeader(
            calendar = calendar,
            modifier = modifier,
            week = week,
            selectedDay = selectedDay,
            onDayChange = { newDate ->
                changeDay(newDate)
            })

        WorkoutDetails(modifier = modifier,
            selectedDay.value?.workout,
            onExerciseChange = { exerciseName: String -> changeExercise(exerciseName) })

        ExerciseDetails(modifier = modifier, exercise = selectedExerciseState)
    }
}