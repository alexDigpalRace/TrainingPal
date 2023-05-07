package com.example.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calendar.util.DateHelper
import com.example.calendar.data.Datasource
import com.example.calendar.model.Day
import com.example.calendar.model.Exercise
import com.example.calendar.model.Week
import com.example.calendar.model.Workout
import com.example.workoutdigpal.R
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class MainActivity {
    @Composable
    fun CalendarApp() {
        val context = LocalContext
//        CalendarsTheme {
//        }
    }

    @Preview
    @Composable
    fun CalendarViewPreview() {
        val mockDatasource = Datasource()
        val calendar = Calendar.getInstance()
//        val currentWeek = mockDatasource.getCurrentWeek(calendar)
        val currentWeek = mockDatasource.mockWeek()

        CalendarView(week = currentWeek, calendar = calendar)
    }

    @Composable
    fun CalendarView(week: Week, calendar: Calendar, modifier: Modifier = Modifier) {
        val helperLib = DateHelper()

        val noExercise = Exercise(
            "Exercise not found",
            0,
            0,
            false,
            "",
            "",
            ""
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

        val selectedExerciseState =
            remember {
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
            HomeHeader(calendar = calendar, modifier = modifier, selectedDay = selectedDay)
//            todo: show workout on click of day
            DayTabs(
                week = week,
                modifier = modifier,
                onDayChange = { newDate -> changeDay(newDate) })
            WorkoutDetails(
                modifier = modifier,
                selectedDay.value?.workout,
                onExerciseChange = { exerciseName: String -> changeExercise(exerciseName) })
            ExerciseDetails(modifier = modifier, exercise = selectedExerciseState)
        }
    }

    //    todo: handle no workout better
    @Composable
    fun WorkoutDetails(
        modifier: Modifier,
        workout: Workout?,
        onExerciseChange: (String) -> Unit
    ) {
        Column(
            modifier = modifier
                .background(color = Color.Green)
                .height(350.dp)
                .fillMaxSize()
        ) {
            WorkoutTimeInput(
                label = stringResource(R.string.startTimeLabel),
                workout?.startTime ?: "No workout"
            )
            WorkoutTimeInput(
                label = stringResource(R.string.endTimeLabel),
                workout?.endTime ?: "No workout"
            )
            ExercisesList(
                modifier = modifier,
                exercises = workout?.exercises ?: ArrayList(
                    listOf(
                        Exercise(
                            "No workout",
                            0,
                            0,
                            false,
                            "",
                            "",
                            ""
                        )
                    )
                ),
                onExerciseChange = onExerciseChange
            )
        }
    }

    // todo: big text boxes for details and comments, check box for isTime,
    @Composable
    fun ExerciseDetails(modifier: Modifier, exercise: MutableState<Exercise>) {
        LazyColumn(
            modifier = modifier
                .background(color = Color.Magenta)
                .height(300.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(text = "Name: ${exercise.value.name}", fontSize = 16.sp)
                Text(
                    text = "SxR: ${exercise.value.sets.toString()} x ${exercise.value.reps.toString()}",
                    fontSize = 16.sp
                )
                Text(text = "Weight: ${exercise.value.weight}", fontSize = 16.sp)
                Text(text = "Extra details: ", fontSize = 16.sp)
                Text(text = exercise.value.details, fontSize = 16.sp)
                Text(text = "Comments: ", fontSize = 16.sp)
                Text(text = exercise.value.comments, fontSize = 16.sp)
            }
        }
    }

    @Composable
    fun ExercisesList(
        modifier: Modifier,
        exercises: ArrayList<Exercise>,
        onExerciseChange: (String) -> Unit
    ) {
        LazyColumn() {
            items(exercises) { exercise ->
                Button(onClick = { onExerciseChange(exercise.name) }) {
                    Text(text = exercise.name)
                }
            }
        }
    }

    @Composable
    fun WorkoutTimeInput(label: String, value: String) {
        val valueTextField = TextFieldValue(value)
        Row {
            TextField(value = valueTextField, onValueChange = {/*todo*/ }, label = { Text(label) })
            Button(onClick = { /*TODO*/ }) {
                Text(text = if ("start" in label.lowercase()) "Start now" else "End now")
            }
        }
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


}